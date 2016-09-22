class Operator implements Constants {

  private byte[] cube;

  public Operator() {
    this.initCube();
  }

  public void initCube() {
    this.cube = new byte[54];

    // Init sides of cube
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) this.cube[((FRONT * 9) + (i * 3) + j)]  = WHITE;
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) this.cube[((BACK * 9) + (i * 3) + j)]   = GREEN;
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) this.cube[((LEFT * 9) + (i * 3) + j)]   = BLUE;
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) this.cube[((RIGHT * 9) + (i * 3) + j)]  = RED;
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) this.cube[((TOP * 9) + (i * 3) + j)]    = ORANGE;
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) this.cube[((BOTTOM * 9) + (i * 3) + j)] = YELLOW;
  }

  public void disarm() {

  }

  public void assemble() {

  }

  public byte[] getRow(byte[] cube, int side, int row) {
    byte[] rowReturned = new byte[3];

    rowReturned[0] = cube[(side * 9) + (row * 3) + 0];
    rowReturned[1] = cube[(side * 9) + (row * 3) + 1];
    rowReturned[2] = cube[(side * 9) + (row * 3) + 2];
    System.out.println("JUST TESTING");
  
    return rowReturned;
  }

  public byte[] getColumn(byte[] cube, int side, int col) {
    byte[] colReturned = new byte[3];

    colReturned[0] = cube[(side * 9) + 0 + col];
    colReturned[1] = cube[(side * 9) + 3 + col];
    colReturned[2] = cube[(side * 9) + 6 + col];

    return colReturned;
  }

  public void setRow(byte[] the_Row, int side, int row){
      for (int y = 0 ; y < 3 ; y++){
          this.cube[(side * 9) + (row*3) + y] = the_Row[y];
      }
  }

  public void setColumn(byte[] the_Column, int side, int column){ //column must be 0 or 2.
    for (int y  = 0 ; y < 3; y++){
      this.cube[(side*9) + (3*y) + column ] = the_Column[y];
    }
  }

  public void setColumn(int column){}

  public void rotate(byte[] cube, int side, int direction){




  }

  public void operate(byte[] oldCube, int operation, int direction) {
    byte[] cube = oldCube.clone();
    byte[] tmp = new byte[3];
    /*
    switch (operation) {
      case OP_ROTATE_FRONT:
        if (direction == DIR_RIGHT) {
          cube.frontSide.rotate(DIR_RIGHT);
          tmp = cube.topSide.getRow(ROW_BOTTOM);
          cube.topSide.setRow(ROW_BOTTOM, cube.leftSide.getColumn(COL_RIGHT), true);
          cube.leftSide.setColumn(COL_RIGHT, cube.bottomSide.getRow(ROW_TOP), false);
          cube.bottomSide.setRow(ROW_TOP, cube.rightSide.getColumn(COL_LEFT), true);
          cube.rightSide.setColumn(COL_LEFT, tmp, false);
        } else {
          cube.frontSide.rotate(DIR_LEFT);
          tmp = cube.topSide.getRow(ROW_BOTTOM);
          cube.topSide.setRow(ROW_BOTTOM, cube.rightSide.getColumn(COL_LEFT), false);
          cube.rightSide.setColumn(COL_LEFT, cube.bottomSide.getRow(ROW_TOP), true);
          cube.bottomSide.setRow(ROW_TOP, cube.leftSide.getColumn(COL_RIGHT), false);
          cube.leftSide.setColumn(COL_RIGHT, tmp, true);
        }
        break;
      case OP_ROTATE_BACK:
        if (direction == DIR_RIGHT) {
          cube.backSide.rotate(DIR_RIGHT);
          tmp = cube.topSide.getRow(ROW_TOP);
          cube.topSide.setRow(ROW_TOP, cube.leftSide.getColumn(COL_LEFT), true);
          cube.leftSide.setColumn(COL_LEFT, cube.bottomSide.getRow(ROW_BOTTOM), false);
          cube.bottomSide.setRow(ROW_BOTTOM, cube.rightSide.getColumn(COL_RIGHT), true);
          cube.rightSide.setColumn(COL_RIGHT, tmp, false);
          } else {
          cube.backSide.rotate(DIR_LEFT);
          tmp = cube.topSide.getRow(ROW_TOP);
          cube.topSide.setRow(ROW_TOP, cube.rightSide.getColumn(COL_RIGHT), false);
          cube.rightSide.setColumn(COL_RIGHT, cube.bottomSide.getRow(ROW_BOTTOM), true);
          cube.bottomSide.setRow(ROW_BOTTOM, cube.leftSide.getColumn(COL_LEFT), false);
          cube.leftSide.setColumn(COL_LEFT, tmp, true);
        }
        break;
      case OP_ROW_TOP:
        if (direction == DIR_RIGHT) {
          cube.topSide.rotate(DIR_RIGHT);
          tmp = cube.frontSide.getRow(ROW_TOP);
          cube.frontSide.setRow(ROW_TOP, cube.leftSide.getRow(ROW_TOP), false);
          cube.leftSide.setRow(ROW_TOP, cube.backSide.getRow(ROW_TOP), false);
          cube.backSide.setRow(ROW_TOP, cube.rightSide.getRow(ROW_TOP), false);
          cube.rightSide.setRow(ROW_TOP, tmp, false);
        } else {
          cube.topSide.rotate(DIR_LEFT);
          tmp = cube.frontSide.getRow(ROW_TOP);
          cube.frontSide.setRow(ROW_TOP, cube.rightSide.getRow(ROW_TOP), false);
          cube.rightSide.setRow(ROW_TOP, cube.backSide.getRow(ROW_TOP), false);
          cube.backSide.setRow(ROW_TOP, cube.leftSide.getRow(ROW_TOP), false);
          cube.leftSide.setRow(ROW_TOP, tmp, false);
        }
        break;
      case OP_ROW_BOTTOM:
        if (direction == DIR_RIGHT) {
          cube.bottomSide.rotate(DIR_RIGHT);
          tmp = cube.frontSide.getRow(ROW_BOTTOM);
          cube.frontSide.setRow(ROW_BOTTOM, cube.leftSide.getRow(ROW_BOTTOM), false);
          cube.leftSide.setRow(ROW_BOTTOM, cube.backSide.getRow(ROW_BOTTOM), false);
          cube.backSide.setRow(ROW_BOTTOM,cube.rightSide.getRow(ROW_BOTTOM), false);
          cube.rightSide.setRow(ROW_BOTTOM, tmp, false);
        } else {
          cube.bottomSide.rotate(DIR_LEFT);
          tmp = cube.frontSide.getRow(ROW_BOTTOM);
          cube.frontSide.setRow(ROW_BOTTOM, cube.rightSide.getRow(ROW_BOTTOM), false);
          cube.rightSide.setRow(ROW_BOTTOM, cube.backSide.getRow(ROW_BOTTOM), false);
          cube.backSide.setRow(ROW_BOTTOM, cube.leftSide.getRow(ROW_BOTTOM), false);
          cube.leftSide.setRow(ROW_BOTTOM, tmp, false);
        }
        break;
      case OP_COL_LEFT:
        if (direction == DIR_UP) {
          cube.leftSide.rotate(DIR_LEFT);
          tmp = cube.frontSide.getColumn(COL_LEFT);
          cube.frontSide.setColumn(COL_LEFT, cube.bottomSide.getColumn(COL_LEFT), false);
          cube.bottomSide.setColumn(COL_LEFT, cube.backSide.getColumn(COL_RIGHT), true);
          cube.backSide.setColumn(COL_RIGHT, cube.topSide.getColumn(COL_LEFT), true);
          cube.topSide.setColumn(COL_LEFT, tmp, false);
        } else {
          cube.leftSide.rotate(DIR_RIGHT);
          tmp = cube.frontSide.getColumn(COL_LEFT);
          cube.frontSide.setColumn(COL_LEFT, cube.topSide.getColumn(COL_LEFT), false);
          cube.topSide.setColumn(COL_LEFT, cube.backSide.getColumn(COL_RIGHT), true);
          cube.backSide.setColumn(COL_RIGHT, cube.bottomSide.getColumn(COL_LEFT), true);
          cube.bottomSide.setColumn(COL_LEFT, tmp, false);
        }
        break;
      case OP_COL_RIGHT:
        if (direction == DIR_UP) {
          cube.rightSide.rotate(DIR_RIGHT);
          tmp = cube.frontSide.getColumn(COL_RIGHT);
          cube.frontSide.setColumn(COL_RIGHT, cube.bottomSide.getColumn(COL_RIGHT), false);
          cube.bottomSide.setColumn(COL_RIGHT, cube.backSide.getColumn(COL_LEFT), true);
          cube.backSide.setColumn(COL_LEFT, cube.topSide.getColumn(COL_RIGHT), true);
          cube.topSide.setColumn(COL_RIGHT, tmp, false);
        } else{
          cube.rightSide.rotate(DIR_LEFT);
          tmp = cube.frontSide.getColumn(COL_RIGHT);
          cube.frontSide.setColumn(COL_RIGHT, cube.topSide.getColumn(COL_RIGHT), false);
          cube.topSide.setColumn(COL_RIGHT, cube.backSide.getColumn(COL_LEFT), true);
          cube.backSide.setColumn(COL_LEFT, cube.bottomSide.getColumn(COL_RIGHT), true);
          cube.bottomSide.setColumn(COL_RIGHT, tmp, false);
        }
        break;
      default: break;
    }
    cube.operations[cube.level] = operation * 10 + direction;
    return cube;
    */
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
