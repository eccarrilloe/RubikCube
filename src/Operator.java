import java.util.concurrent.ThreadLocalRandom;
import java.util.Stack;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Comparator;
import java.util.PriorityQueue;

class Operator implements Constants {

  public Cube cube;
  public byte maxDepth;

  public Operator() {
    this.maxDepth = 0;
    this.cube = new Cube();
  }

  public void disarm(int operations) {
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
    ArrayList<Cube> states = new ArrayList<>(10000000);
    Stack<Cube> stack = new Stack<>();
    states.add(this.cube);
    stack.push(this.cube);

    while(! stack.empty()) {
        Cube current = stack.pop();
        Iterator<Cube> childrens = this.getChildrens(current, false).iterator();

        while(childrens.hasNext()) {
          Cube child = childrens.next();

          if (this.validate(child)) {
              this.cube = child;
              return;
          }

          if (! states.contains(child)) {
            states.add(child);
            stack.push(child);
          }
        }
    }
  }

  private void assembleBFS() {
    Cube currentCube = this.cube;
    ArrayDeque<Cube> queue = new ArrayDeque<>();
    Stack<Cube> visited = new Stack<>();
    int aux = 6, level = 0, elementsInLevel = 0;
    queue.add(currentCube);
    Cube current = new Cube();
    while(!queue.isEmpty()){
      current = queue.remove();
      visited.add(current);
      if(this.validate(current)){
        this.cube = current;
        return;
      }
      else{
        ArrayList<Cube> childs = this.getChildrens(current, false);
        for (Cube c : childs ) {
          elementsInLevel++;
          if(this.validate(c)){
            this.cube = c;
            return;
          }
          else{
      //    for (byte[] cubeInList : visited){
            if(!visited.contains(c)){
                queue.add(c);
                if(elementsInLevel==aux){
                  aux*=6;
                  level++;
              }
            }
          }
        }
      }
    }
    this.cube = currentCube;
  }

  public boolean compare(Cube one, Cube two){
    for (int i = 0; i < one.cube.length; i++ )
      if (one.cube[i] != two.cube[i])
        return false;

    return true;
  }

  //public boolean natural_failure = false;

  public void MY_DFS(Cube the_Cube, int my_depth){
     int the_limit  = (int) Math.pow(6, my_depth);
     System.out.println(" possible states:" + the_limit);
     Cube current_Cube = the_Cube;
     Stack<Cube> visited = new Stack<>();
     Stack<Cube> the_Q = new Stack<>();

     if(this.validate(the_Cube) && my_depth == 0){
       System.out.println(" valid cube and depth = 0");
     }

    the_Q.push(current_Cube);

    while(!the_Q.isEmpty() && my_depth > -1){

      current_Cube = the_Q.remove(0);

      if(this.validate(current_Cube)){

        System.out.println("ANSWER FOUNDED \n");
        Cube.printCube(current_Cube);
        break;
      }
      else{
        ArrayList<Cube> children = getChildrens(current_Cube, false);
        for(int x = 0; x < children.size(); x++){
            Cube current_Child = children.get(x);
            if(!visited.contains(current_Child)){
              the_Q.push(current_Child);
            }
        }
        visited.push(current_Cube);
        the_limit--;
        my_depth--;
        System.out.println("\n  Depth" + my_depth);
        System.out.println("Current_cube: \n");
        Cube.printCube(current_Cube);
        System.out.println("Queue size: " + the_Q.size());
        System.out.println("visited size: " + visited.size());

      }
    }
    }

  public void assembleIDS(){
      MY_DFS(this.cube, 15);

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
          if(!compare(visit, c)){
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
    tmp.add(Cube.operate(currentCube, ROTATE_FRONT_DER));
    tmp.add(Cube.operate(currentCube, ROTATE_BACK_DER));
    tmp.add(Cube.operate(currentCube, ROW_TOP_DER));
    tmp.add(Cube.operate(currentCube, ROW_BOTTOM_DER));
    tmp.add(Cube.operate(currentCube, COL_LEFT_UP));
    tmp.add(Cube.operate(currentCube, COL_RIGHT_UP));

    if(setHeuristic){
      for (Cube c : tmp ) {
          c.setHeuristic();
      }
    }
    return tmp;
  }

}
