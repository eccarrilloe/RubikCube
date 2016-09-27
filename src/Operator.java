import java.util.concurrent.ThreadLocalRandom;
import java.util.Stack;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Comparator;
import java.util.PriorityQueue;

class Operator implements Constants {

  public Cube cube;
  public int maxDepth;
  public int maxNodesExpanded;
  public ArrayList<Cube> visited;

  public Operator() {
    this.maxDepth = 0;
    this.maxNodesExpanded = 0;
    this.visited = new ArrayList<>();
    this.cube = new Cube();
  }

  public void disarm(int operations) {
    this.maxDepth = operations < 15 ? operations : 15;
    for (int i = 0; i < operations; i++) {
      int operation = ThreadLocalRandom.current().nextInt(1, OPERATIONS + 1);
      this.cube = Cube.operate(this.cube, operation);
    }
  }

  public void assemble(int searchType) {
    switch (searchType) {
      case SEARCH_DFS: this.assembleDFS(); break;
      case SEARCH_BFS: this.assembleBFS(); break;
      case SEARCH_IDS: this.assembleIDS(); break;
      case SEARCH_AST: this.assembleAST(); break;
      default: break;
    }
  }

  private void assembleDFS() {
    ArrayList<Cube> states = new ArrayList<>((int) Math.pow(6, this.maxDepth));
    Stack<Cube> stack = new Stack<>();
    states.add(this.cube);
    stack.push(this.cube);
    this.cube.depth = 0;

    while(! stack.empty()) {
        Cube current = stack.pop();

        if (current.depth > this.maxDepth)
          continue;

        if (states.size() % 10000 == 0)
          System.out.println("Nodos Expandidos: " + states.size());

        Iterator<Cube> childrens = this.getChildrens(current, false).iterator();

        while(childrens.hasNext()) {
          Cube child = childrens.next();
          child.depth = current.depth + 1;

          if (this.validate(child)) {
              this.cube = child;
              this.maxNodesExpanded = states.size();
              return;
          }

          if (! states.contains(child)) {
            states.add(child);
            stack.push(child);
          }
        }
    }
    this.maxNodesExpanded = states.size();
  }

  private void assembleBFS() {
    this.cube.depth = 0;
    Cube current = new Cube();
    ArrayList<Cube> states = new ArrayList<>();
    ArrayDeque<Cube> queue = new ArrayDeque<>();
    queue.add(this.cube);

    while(! queue.isEmpty()) {
      current = queue.remove();
      states.add(current);

      if(this.validate(current)) {
        this.cube = current;
        this.maxNodesExpanded = states.size();
        return;
      }

      Iterator<Cube> childs = this.getChildrens(current, false).iterator();
      while (childs.hasNext()) {
        Cube child = childs.next();
        child.depth = current.depth + 1;

        if(this.validate(child)) {
          this.cube = child;
          this.maxNodesExpanded = states.size();
          return;
        }

        if(! states.contains(child)) {
          queue.add(child);
        }
      }
    }

    this.cube = current;
    this.maxNodesExpanded = states.size();
  }

  public boolean compare(Cube one, Cube two){
    for (int i = 0; i < one.cube.length; i++ )
      if (one.cube[i] != two.cube[i])
        return false;

    return true;
  }

  public ArrayList<Cube> IterativeDFS(Cube the_Cube, int currentMaxDepth) {
     Stack<Cube> stack = new Stack<>();
     ArrayList<Cube> leaves = new ArrayList<>();
     this.visited.add(the_Cube);
     stack.push(the_Cube);

     while(! stack.isEmpty()) {
       Cube currentCube = stack.remove(0);

       Iterator<Cube> children = getChildrens(currentCube, false).iterator();

        while (children.hasNext()) {
          Cube currentChild = children.next();
          currentChild.depth = currentCube.depth + 1;

          if (this.validate(currentChild)) {
              leaves = new ArrayList<>();
              leaves.add(currentChild);
              return leaves;
          }

          if(! this.visited.contains(currentChild) && currentChild.depth < currentMaxDepth) {
            stack.push(currentChild);
            this.visited.add(currentChild);
          } else if (currentChild.depth == currentMaxDepth) {
            leaves.add(currentChild);
          }
        }
      }
      return leaves;
  }

  public void assembleIDS() {
    this.cube.depth = 0;
    int depthIteration = 5;
    ArrayDeque<Cube> leaf = new ArrayDeque<>();
    Cube currentCube = new Cube();
    this.visited = new ArrayList<>();
    leaf.push(this.cube);

    while (! leaf.isEmpty()) {
      currentCube = leaf.pop();

      if (this.validate(currentCube)) {
        this.cube = currentCube;
        this.maxNodesExpanded = this.visited.size();

        return;
      }

      int nextDepth = currentCube.depth + depthIteration > this.maxDepth ? this.maxDepth : currentCube.depth + depthIteration;
      ArrayList<Cube> leaves = IterativeDFS(currentCube, nextDepth);

      if (leaves.size() == 1 && this.validate(leaves.get(0))) {
        this.cube = leaves.get(0);
        this.maxNodesExpanded = this.visited.size();
        return;
      }

      Iterator<Cube> it = leaves.iterator();
      while (it.hasNext()) {
        Cube leave = it.next();
        leaf.push(leave);
      }
    }
    this.cube = currentCube;
    this.maxNodesExpanded = this.visited.size();
  }

  private void assembleAST() {
    Comparator<Cube> comparator = new Cube();
    PriorityQueue<Cube> priorityQueue = new PriorityQueue<>(10, comparator);
    ArrayList<Cube> visited = new ArrayList<>();
    this.cube.setHeuristic();
    priorityQueue.add(this.cube);

    while(!priorityQueue.isEmpty()){
      Cube currentCube = priorityQueue.remove();
      visited.add(currentCube);
      if(this.validate(currentCube)){
        this.cube = currentCube;
        return;
      }
      ArrayList<Cube> children = getChildrens(this.cube, true);
      for (Cube c : children ) {
        if(this.validate(c)){
          this.cube = c;
          return;
        }
        for(Cube visit : visited){
          if(! compare(visit, c)) {
            //Cube.printCube(c);
            //System.out.println("Heuristic: " + c.heuristic);
            priorityQueue.add(c);
          }break;
        }
      }
    }
  }

  public boolean validate(Cube current){
    if (! this.validateSide(FRONT, WHITE, current))   return false;
    if (! this.validateSide(BACK, YELLOW, current))   return false;
    if (! this.validateSide(LEFT, BLUE, current))     return false;
    if (! this.validateSide(RIGHT, GREEN, current))   return false;
    if (! this.validateSide(TOP, RED, current))       return false;
    if (! this.validateSide(BOTTOM, ORANGE, current)) return false;

    return true;
  }

  private boolean validateSide(int side, int color, Cube current) {
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++)
        if (current.cube[((side * 9) + (i * 3) + j)] != color)
          return false;
    return true;
  }

  private ArrayList<Cube> getChildrens(Cube currentCube, boolean setHeuristic){
    ArrayList<Cube> tmp = new ArrayList<>();
    tmp.add(Cube.operate(currentCube, ROTATE_FRONT_IZQ));
    tmp.add(Cube.operate(currentCube, ROTATE_BACK_IZQ));
    tmp.add(Cube.operate(currentCube, ROW_TOP_IZQ));
    tmp.add(Cube.operate(currentCube, ROW_BOTTOM_IZQ));
    tmp.add(Cube.operate(currentCube, COL_LEFT_DOWN));
    tmp.add(Cube.operate(currentCube, COL_RIGHT_DOWN));

    if(setHeuristic){
      for (Cube c : tmp ) {
          c.setHeuristic();
      }
    }
    return tmp;
  }

}
