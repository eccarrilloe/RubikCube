import java.util.concurrent.ThreadLocalRandom;
import java.util.Stack;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Iterator;

class Operator implements Constants {

  public byte[] cube;

  public Operator() {
    this.initCube();
  }

  public void initCube() {
    this.cube = new byte[54];

    // Init sides of cube
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) this.cube[((FRONT * 9) + (i * 3) + j)]  = WHITE;
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) this.cube[((BACK * 9) + (i * 3) + j)]   = YELLOW;
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) this.cube[((LEFT * 9) + (i * 3) + j)]   = BLUE;
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) this.cube[((RIGHT * 9) + (i * 3) + j)]  = GREEN;
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) this.cube[((TOP * 9) + (i * 3) + j)]    = RED;
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) this.cube[((BOTTOM * 9) + (i * 3) + j)] = ORANGE;

  }

  public void disarm(int operations) {
    for (int i = 0; i < operations; i++) {
      int operation = ThreadLocalRandom.current().nextInt(1, OPERATIONS + 1);
      this.cube = this.operate(this.cube, operation);
    }
    System.out.println("The cube after " + operations + " operations \n");
    printCube(this.cube);
  }

  public void assemble(int searchType) {
    switch (searchType) {
      case SEARCH_DFS: this.assembleDFS(); break;
      case SEARCH_BFS: this.cube = this.assembleBFS(); break;
      case SEARCH_IDS: this.assembleIDS(); break;
      case SEARCH_AST: this.assembleAST(); break;
      default: break;
    }
  }

  private void assembleDFS() {
    ArrayList<byte[]> states = new ArrayList<>(1000000);
    Stack<byte[]> stack = new Stack<>();
    states.add(this.cube);
    stack.push(this.cube);

    while(! stack.empty()) {
        byte[] current = stack.pop();
        //this.printCube(current);
        Iterator<byte[]> childrens = this.getChildrens(current).iterator();
        while(childrens.hasNext()) {
          byte[] child = childrens.next();
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

  private byte[] assembleBFS() {
    byte[] currentCube = this.cube;
    ArrayDeque<byte[]> queue = new ArrayDeque<>();
    Stack<byte[]> visited = new Stack<>();
    int aux = 6, level = 0, elementsInLevel = 0;
    queue.add(currentCube);
    byte[] current = new byte[54];
    while(!queue.isEmpty()){
      current = queue.remove();
      try{
          visited.add(current);
      }catch(Exception e){
        System.out.println("Memory out!");
      }
      if(this.validate(current))
      return current;
      else{
        ArrayList<byte[]> childs = this.getChildrens(current);
        elementsInLevel+=6;
        for (byte[] c : childs ) {
          if(this.validate(c))
          return c;
          else{
          for (byte[] cubeInList : visited){
            if(!compare(cubeInList, c)){
                queue.add(c);
                if(elementsInLevel==aux){
                  aux*=6;
                  System.out.println("Level: " + level + " Queue size: " + queue.size() + " elementsInLevel: " + aux + " visited size: " + visited.size());
                  level++;
                }
              }
            }
          }
      //    if(queue.size()%1000==0) System.out.println("Queue size: " + queue.size() +" visited size: " + visited.size());

        }
      }
    }
    return currentCube;
  }

  public boolean compare(byte[] one, byte[] two){
    for (int i=0; i<one.length; i++ ) {
      if(!(one[i]==two[i])) return false;
    }
    return true;
  }

  //public boolean natural_failure = false;

  public void MY_DFS(byte[] the_Cube, int my_depth){
     int the_limit  = (int) Math.pow(6, my_depth);
     System.out.println(" possible states:" + the_limit);
     byte[] current_Cube = the_Cube;
     Stack<byte[]> visited = new Stack<byte[]>();
     Stack<byte[]> the_Q = new Stack<byte[]>();

     if(validate(the_Cube) && my_depth == 0){
       System.out.println(" valid cube and depth = 0");
     }

    the_Q.push(current_Cube);

    while(!the_Q.isEmpty() && my_depth > -1){

      current_Cube = the_Q.remove(0);

      if(validate(current_Cube)){

        System.out.println("ANSWER FOUNDED \n");
        printCube(current_Cube);
        break;
      }
      else{
        ArrayList<byte[]> children = getChildrens(current_Cube);
        for(int x = 0; x < children.size(); x++){
            byte[] current_Child = children.get(x);
            if(!visited.contains(current_Child)){
              the_Q.push(current_Child);
            }
        }
        visited.push(current_Cube);
        the_limit--;
        my_depth--;
        System.out.println("\n  Depth" + my_depth);
        System.out.println("Current_cube: \n");
        printCube(current_Cube);
        System.out.println("Queue size: " + the_Q.size());
        System.out.println("visited size: " + visited.size());

      }
    }
    }

  public void assembleIDS(){

      MY_DFS(this.cube, 15);
      
  }

  private void assembleAST() {

  }

  public boolean validate(byte[] current){
    if (! this.validateSide(FRONT, WHITE, current))   return false;
    if (! this.validateSide(BACK, YELLOW, current))   return false;
    if (! this.validateSide(LEFT, BLUE, current))     return false;
    if (! this.validateSide(RIGHT, GREEN, current))   return false;
    if (! this.validateSide(TOP, RED, current))       return false;
    if (! this.validateSide(BOTTOM, ORANGE, current)) return false;

    return true;
  }

  private boolean validateSide(int side, int color, byte[] current) {
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++)
        if (current[((side * 9) + (i * 3) + j)] != color)
          return false;
    return true;
  }

  private byte[] deepCopy(byte[] old){
    byte[] copy = new byte[old.length];
    for (int i=0; i<old.length; i++)
      copy[i] = old[i];

    return copy;
  }

  private ArrayList<byte[]> getChildrens(byte[] currentCube){
    ArrayList<byte[]> tmp = new ArrayList<>();
    tmp.add(this.operate(currentCube, ROTATE_FRONT_DER));
    tmp.add(this.operate(currentCube, ROTATE_BACK_DER));
    tmp.add(this.operate(currentCube, ROW_TOP_DER));
    tmp.add(this.operate(currentCube, ROW_BOTTOM_DER));
    tmp.add(this.operate(currentCube, COL_LEFT_UP));
    tmp.add(this.operate(currentCube, COL_RIGHT_UP));

    return tmp;
  }

  public byte[] getRow(byte[] current, int side, int row) {
    byte[] rowReturned = new byte[3];
    rowReturned[0] = current[(side * 9) + (row * 3) + 0];
    rowReturned[1] = current[(side * 9) + (row * 3) + 1];
    rowReturned[2] = current[(side * 9) + (row * 3) + 2];
    return rowReturned;
  }

  public byte[] getColumn(byte[] current, int side, int col) {
    byte[] colReturned = new byte[3];
    colReturned[0] = current[(side * 9) + 0 + col];
    colReturned[1] = current[(side * 9) + 3 + col];
    colReturned[2] = current[(side * 9) + 6 + col];

    return colReturned;
  }

  public void setRow(byte[] current, byte[] newColors, int side, int row, boolean reverse){
    if(reverse){
      current[(side * 9) + (row*3) + 0] = newColors[2];
      current[(side * 9) + (row*3) + 1] = newColors[1];
      current[(side * 9) + (row*3) + 2] = newColors[0];
    }
    else{
      current[(side * 9) + (row*3) + 0] = newColors[0];
      current[(side * 9) + (row*3) + 1] = newColors[1];
      current[(side * 9) + (row*3) + 2] = newColors[2];
    }
  }

  public void setColumn(byte[] current, byte[] positions, int side, int column, boolean reverse){ //column must be 0 or 2.
    if(reverse){
      current[(side*9) + (3*0) + column ] = positions[2];
      current[(side*9) + (3*1) + column ] = positions[1];
      current[(side*9) + (3*2) + column ] = positions[0];
    }
    else{
      current[(side*9) + (3*0) + column ] = positions[0];
      current[(side*9) + (3*1) + column ] = positions[1];
      current[(side*9) + (3*2) + column ] = positions[2];
    }
  }

  public void rotate(byte[] currentCube, int side, int direction){
    byte[] temp1 = this.getRow(currentCube, side, 0);
    byte[] temp2 = this.getColumn(currentCube, side, 0);
    byte[] temp3 = this.getRow(currentCube, side, 2);
    byte[] temp4 = this.getColumn(currentCube, side, 2);
    this.setColumn(currentCube, temp1, side, 2, false);
    this.setRow(currentCube, temp2, side, 0, true);
    this.setColumn(currentCube, temp3, side, 0, false);
    this.setRow(currentCube, temp4, side, 2, true);
  }

  public byte[] operate(byte[] oldCube, int operation) {
    byte[] currentCube = deepCopy(oldCube);
    byte[] tmp = new byte[3];
    switch(operation){
        case ROTATE_FRONT_DER:
        tmp = this.getRow(currentCube, TOP, 2);
        this.setRow(currentCube, this.getColumn(currentCube, LEFT, 2), TOP, 2, true);
        this.setColumn(currentCube, this.getRow(currentCube, BOTTOM, 0), LEFT, 2, false);
        this.setRow(currentCube, this.getColumn(currentCube, RIGHT, 0), BOTTOM, 0, true);
        this.setColumn(currentCube, tmp, RIGHT, 0, false);
        this.rotate(currentCube, FRONT, 1); //Direction not implemented yet
        break;
      case ROTATE_BACK_DER:
        tmp = this.getRow(currentCube, TOP, 0);
        this.setRow(currentCube, this.getColumn(currentCube, LEFT, 0), TOP, 0, true);
        this.setColumn(currentCube, this.getRow(currentCube, BOTTOM, 2), LEFT, 0,false);
        this.setRow(currentCube, this.getColumn(currentCube, RIGHT, 2), BOTTOM, 2, true);
        this.setColumn(currentCube, tmp, RIGHT, 2, false);
        this.rotate(currentCube, BACK, 1); //Direction not implemented yet
        break;
      case ROW_TOP_DER:
        tmp = this.getRow(currentCube, FRONT, 0);
        this.setRow(currentCube, this.getRow(currentCube, LEFT, 0), FRONT, 0, false);
        this.setRow(currentCube, this.getRow(currentCube, BACK, 0), LEFT, 0, false);
        this.setRow(currentCube, this.getRow(currentCube, RIGHT, 0), BACK, 0, false);
        this.setRow(currentCube, tmp, RIGHT, 0, false);
        this.rotate(currentCube, TOP, 1);
        break;
      case ROW_BOTTOM_DER:
        tmp = this.getRow(currentCube, FRONT, 2);
        this.setRow(currentCube, this.getRow(currentCube, LEFT, 2), FRONT, 2, false);
        this.setRow(currentCube, this.getRow(currentCube, BACK, 2), LEFT, 2, false);
        this.setRow(currentCube, this.getRow(currentCube, RIGHT, 2), BACK, 2, false);
        this.setRow(currentCube, tmp, RIGHT, 2, false);
        this.rotate(currentCube, BOTTOM, 1);
        break;
      case COL_LEFT_UP:
        tmp = this.getColumn(currentCube, FRONT, 0);
        this.setColumn(currentCube, this.getColumn(currentCube, BOTTOM, 0), FRONT, 0,false);
        this.setColumn(currentCube, this.getColumn(currentCube, BACK, 2), BOTTOM, 0, true);
        this.setColumn(currentCube, this.getColumn(currentCube, TOP, 0), BACK, 2, true);
        this.setColumn(currentCube, tmp, TOP, 0,false);
        this.rotate(currentCube, LEFT, 1);
        break;
      case COL_RIGHT_UP:
        tmp = this.getColumn(currentCube, FRONT, 2);
        this.setColumn(currentCube, this.getColumn(currentCube, BOTTOM, 2), FRONT, 2, false);
        this.setColumn(currentCube, this.getColumn(currentCube, BACK, 0), BOTTOM, 2, true);
        this.setColumn(currentCube, this.getColumn(currentCube, TOP, 2), BACK, 0, true);
        this.setColumn(currentCube, tmp, TOP, 2, false);
        this.rotate(currentCube, RIGHT, 1);
        break;
      default:
        break;
    }
    return currentCube;
  }

  public String getColorName(byte color) {
    String name = "";
    switch (color) {
      case 0x1: name = "W"; break;
      case 0x2: name = "G"; break;
      case 0x3: name = "B"; break;
      case 0x4: name = "R"; break;
      case 0x5: name = "O"; break;
      case 0x6: name = "Y"; break;
    }

    return name;
  }

  public void printCube(byte[] current) {
    String strCube = "";
    String offset = "        ";

    // Print Top
    for (int i = 0; i < 9; i++) {
      if (i % 3 == 0) strCube += "\n" + offset;
      strCube += this.getColorName(current[((TOP * 9) + i)]) + " ";
    }

    strCube += "\n" + offset + "-----\n";
    // Print Left-Front-Right-Back
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        strCube += this.getColorName(current[((j * 9) + (i * 3) + 0)]) + " ";
        strCube += this.getColorName(current[((j * 9) + (i * 3) + 1)]) + " ";
        strCube += this.getColorName(current[((j * 9) + (i * 3) + 2)]) + " | ";
      }
      strCube += "\n";
    }

    strCube += offset + "-----\n";

    // Print Bottom
    for (int i = 0; i < 9; i++) {
      if (i % 3 == 0 && i != 0) strCube += "\n";
      if (i % 3 == 0) strCube += offset;
      strCube += this.getColorName(current[((BOTTOM * 9) + i)]) + " ";
    }

    System.out.println(strCube);
/*
    for (int w = 0; w < strCube.length(); w++){
      char the_char = strCube.charAt(w);
      switch(the_char){
        case 'G':
        System.out.print(ANSI_GREEN + the_char + ANSI_RESET);
        break;
        case 'R':
        System.out.print(ANSI_RED + the_char + ANSI_RESET);
        break;
        case 'Y':
        System.out.print(ANSI_YELLOW + the_char + ANSI_RESET);
        break;
        case 'B':
        System.out.print(ANSI_BLUE + the_char + ANSI_RESET);
        break;
        case 'W':
        System.out.print(ANSI_WHITE + the_char + ANSI_RESET);
        break;
        case 'O':
        System.out.print(ANSI_CYAN + the_char + ANSI_RESET);
        break;
        default:
        System.out.print(the_char);
      }

    }*/

  }

}
