import java.util.concurrent.ThreadLocalRandom;
import java.util.Stack;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Arrays;

class Operator implements Constants {

  private byte[] cube;

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
    System.out.println("Cube Disarmed");
    printCube(this.cube);
  }

  public void assemble() {
      this.cube = assembleBFS();
      System.out.println("************************* SOLVED CUBE *************************");
      printCube(this.cube);

  }

  public boolean validate(byte[] current){

    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) if(!(current[((FRONT * 9) + (i * 3) + j)]   == WHITE))  return false;
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) if(!(current[((BACK * 9) + (i * 3) + j)]    == YELLOW)) return false;
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) if(!(current[((LEFT * 9) + (i * 3) + j)]    == BLUE))   return false;
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) if(!(current[((RIGHT * 9) + (i * 3) + j)]   == GREEN))  return false;
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) if(!(current[((TOP * 9) + (i * 3) + j)]     == RED))    return false;
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) if (!(current[((BOTTOM * 9) + (i * 3) + j)] == ORANGE)) return false;

    return true;
  }

  private byte[] deepCopy(byte[] old){
    byte[] copy = new byte[old.length];
    for (int i=0; i<old.length; i++) {
       copy[i] = old[i];
    }
    return copy;
  }

  public ArrayList<byte[]> getChildrens(byte[] currentCube){
    ArrayList<byte[]> tmp = new ArrayList<>();
    tmp.add(this.operate(currentCube, COL_RIGHT_UP));
    tmp.add(this.operate(currentCube, ROTATE_FRONT_DER));
    tmp.add(this.operate(currentCube, ROTATE_BACK_DER));
    tmp.add(this.operate(currentCube, ROW_TOP_DER));
    tmp.add(this.operate(currentCube, ROW_BOTTOM_DER));
    tmp.add(this.operate(currentCube, COL_LEFT_UP));


    return tmp;
  }
  private byte[] assembleBFS() {
    byte[] currentCube = this.cube;
    ArrayDeque<byte[]> queue = new ArrayDeque<>();
    ArrayList<byte[]> inList = new ArrayList<>();
    int aux = 1, level = 0, elementsInLevel = 1, paux=1;
    queue.add(currentCube);
    byte[] current = new byte[54];
    while(!queue.isEmpty()){
      current = queue.remove();
      aux++;
      inList.add(current);
      if(this.validate(current))
      return current;
      else{
        ArrayList<byte[]> childs = this.getChildrens(current);
        for (byte[] c : childs ) {
          for (byte[] cubeInList : inList) {
            if(!cubeInList.equals(c)){
              if(this.validate(c))
              return c;
              else{
                try{
                  queue.add(c);
                }catch(Exception e){
                  System.out.println("Memory Out!");
                }
                if(queue.size()%aux==0){
                  level++;
                  aux *=6;
                }
              }
              break;
            }
          }
        }
      }
    }
    return currentCube;
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
      tmp = this.getRow(cube, FRONT, 0);
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

  public void printCube(byte[] ncube) {
    byte[] current = deepCopy(ncube);
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
  }
}
