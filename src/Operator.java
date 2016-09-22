import java.util.concurrent.ThreadLocalRandom;
import java.util.Stack;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Queue;

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
      this.operate(cube, operation);
    }
  }

  public void assemble() {
    assembleBFS(cube);
    printCube();
  }

  public boolean validate(byte[] current){
    for (int i=0; i<6; i++) {
      for (int j=0; j<9; j++) {
        if(current[i + j] != (i+1))
        return false;
      }
    }
    return true;
  }
  public ArrayDeque<byte[]> getChildrens(byte[] currentCube){
    ArrayDeque<byte[]> tmp = new ArrayDeque<>();
    tmp.add(this.operate(currentCube, ROTATE_FRONT_DER));
    tmp.add(this.operate(currentCube, ROTATE_BACK_DER));
    tmp.add(this.operate(currentCube, ROW_TOP_DER));
    tmp.add(this.operate(currentCube, ROW_BOTTOM_DER));
    tmp.add(this.operate(currentCube, COL_LEFT_UP));
    tmp.add(this.operate(currentCube, COL_RIGHT_UP));
    return tmp;
  }
  private byte[] assembleBFS(byte[] cube) {
    byte[] currentCube = cube.clone();
    ArrayDeque<byte[]> queue = new ArrayDeque<>();
    ArrayList<byte[]> inList = new ArrayList<>();
    int aux = 1, level = 0, elementsInLevel = 1;
    queue.add(currentCube);
    byte[] current = new byte[54];
    while(!queue.isEmpty()){
      current = queue.remove();
      aux++;
      inList.add(current);
      if(this.validate(current))
      return current;
      else{
        ArrayDeque<byte[]> childs = this.getChildrens(current);
        byte[] tmp = childs.remove();
        for (byte[] cubeInList : inList) {
          if(!cubeInList.equals(tmp)){
            if(this.validate(tmp))
            return tmp;
            else{
              try{
                queue.add(tmp);
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
    return currentCube;
  }

  public byte[] getRow(byte[] cube, int side, int row) {
    byte[] rowReturned = new byte[3];
    rowReturned[0] = this.cube[(side * 9) + (row * 3) + 0];
    rowReturned[1] = this.cube[(side * 9) + (row * 3) + 1];
    rowReturned[2] = this.cube[(side * 9) + (row * 3) + 2];
    return rowReturned;
  }

  public byte[] getColumn(byte[] cube, int side, int col) {
    byte[] colReturned = new byte[3];

    colReturned[0] = this.cube[(side * 9) + 0 + col];
    colReturned[1] = this.cube[(side * 9) + 3 + col];
    colReturned[2] = this.cube[(side * 9) + 6 + col];

    return colReturned;
  }

  public void setRow(byte[] cube, byte[] newColors, int side, int row, boolean reverse){
    if(reverse){
      this.cube[(side * 9) + (row*3) + 0] = newColors[2];
      this.cube[(side * 9) + (row*3) + 1] = newColors[1];
      this.cube[(side * 9) + (row*3) + 2] = newColors[0];
    }
    else{
      this.cube[(side * 9) + (row*3) + 0] = newColors[0];
      this.cube[(side * 9) + (row*3) + 1] = newColors[1];
      this.cube[(side * 9) + (row*3) + 2] = newColors[2];
    }
  }

  public void setColumn(byte[] cube, byte[] positions, int side, int column, boolean reverse){ //column must be 0 or 2.
    if(reverse){
      this.cube[(side*9) + (3*0) + column ] = positions[2];
      this.cube[(side*9) + (3*1) + column ] = positions[1];
      this.cube[(side*9) + (3*2) + column ] = positions[0];
    }
    else{
      this.cube[(side*9) + (3*0) + column ] = positions[0];
      this.cube[(side*9) + (3*1) + column ] = positions[1];
      this.cube[(side*9) + (3*2) + column ] = positions[2];
    }
  }

  public void rotate(byte[] cube, int side, int direction){
    byte[] temp1 = this.getRow(cube, side, 0);
    byte[] temp2 = this.getColumn(cube, side, 0);
    byte[] temp3 = this.getRow(cube, side, 2);
    byte[] temp4 = this.getColumn(cube, side, 2);
    this.setColumn(cube, temp1, side, 2, false);
    this.setRow(cube, temp2, side, 0, true);
    this.setColumn(cube, temp3, side, 0, false);
    this.setRow(cube, temp4, side, 2, true);
  }

  public byte[] operate(byte[] oldCube, int operation) {
    byte[] cube = oldCube.clone();
    byte[] tmp = new byte[3];
    switch(operation){
      case ROTATE_FRONT_DER:
      tmp = this.getRow(cube, TOP, 2);
      this.setRow(cube, this.getColumn(cube, LEFT, 2), TOP, 2, true);
      this.setColumn(cube, this.getRow(cube, BOTTOM, 0), LEFT, 2, false);
      this.setRow(cube, this.getColumn(cube, RIGHT, 0), BOTTOM, 0, true);
      this.setColumn(cube, tmp, RIGHT, 0, false);
      this.rotate(cube, FRONT, 1); //Direction not implemented yet
      break;
      case ROTATE_BACK_DER:
      tmp = this.getRow(cube, TOP, 0);
      this.setRow(cube, this.getColumn(cube, LEFT, 0), TOP, 0, true);
      this.setColumn(cube, this.getRow(cube, BOTTOM, 2), LEFT, 0,false);
      this.setRow(cube, this.getColumn(cube, RIGHT, 2), BOTTOM, 2, true);
      this.setColumn(cube, tmp, RIGHT, 2, false);
      this.rotate(cube, BACK, 1); //Direction not implemented yet
      break;
      case ROW_TOP_DER:
      tmp = this.getRow(cube, FRONT, 0);
      this.setRow(cube, this.getRow(cube, LEFT, 0), FRONT, 0, false);
      this.setRow(cube, this.getRow(cube, BACK, 0), LEFT, 0, false);
      this.setRow(cube, this.getRow(cube, RIGHT, 0), BACK, 0, false);
      this.setRow(cube, tmp, RIGHT, 0, false);
      this.rotate(cube, TOP, 1);
      break;
      case ROW_BOTTOM_DER:
      tmp = this.getRow(cube, FRONT, 2);
      this.setRow(cube, this.getRow(cube, LEFT, 2), FRONT, 2, false);
      this.setRow(cube, this.getRow(cube, BACK, 2), LEFT, 2, false);
      this.setRow(cube, this.getRow(cube, RIGHT, 2), BACK, 2, false);
      this.setRow(cube, tmp, RIGHT, 2, false);
      this.rotate(cube, BOTTOM, 1);
      break;
      case COL_LEFT_UP:
      tmp = this.getColumn(cube, FRONT, 0);
      this.setColumn(cube, this.getColumn(cube, BOTTOM, 0), FRONT, 0,false);
      this.setColumn(cube, this.getColumn(cube, BACK, 2), BOTTOM, 0, true);
      this.setColumn(cube, this.getColumn(cube, TOP, 0), BACK, 2, true);
      this.setColumn(cube, tmp, TOP, 0,false);
      this.rotate(cube, LEFT, 1);
      break;
      case COL_RIGHT_UP:
      tmp = this.getColumn(cube, FRONT, 2);
      this.setColumn(cube, this.getColumn(cube, BOTTOM, 2), FRONT, 2, false);
      this.setColumn(cube, this.getColumn(cube, BACK, 0), BOTTOM, 2, true);
      this.setColumn(cube, this.getColumn(cube, TOP, 2), BACK, 0, true);
      this.setColumn(cube, tmp, TOP, 2, false);
      this.rotate(cube, RIGHT, 1);
      break;
    }
    return cube;
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

  public void printCube() {
    String strCube = "";
    String offset = "        ";

    // Print Top
    for (int i = 0; i < 9; i++) {
      if (i % 3 == 0) strCube += "\n" + offset;
      strCube += this.getColorName(this.cube[((TOP * 9) + i)]) + " ";
    }

    strCube += "\n" + offset + "-----\n";
    // Print Left-Front-Right-Back
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        strCube += this.getColorName(this.cube[((j * 9) + (i * 3) + 0)]) + " ";
        strCube += this.getColorName(this.cube[((j * 9) + (i * 3) + 1)]) + " ";
        strCube += this.getColorName(this.cube[((j * 9) + (i * 3) + 2)]) + " | ";
      }
      strCube += "\n";
    }

    strCube += offset + "-----\n";

    // Print Bottom
    for (int i = 0; i < 9; i++) {
      if (i % 3 == 0 && i != 0) strCube += "\n";
      if (i % 3 == 0) strCube += offset;
      strCube += this.getColorName(this.cube[((BOTTOM * 9) + i)]) + " ";
    }

    System.out.println(strCube);
  }
}
