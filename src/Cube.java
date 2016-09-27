
class Cube implements Constants {
  public byte[] cube;
  public int depth;

  public Cube() {
    this.cube = new byte[54];
    this.depth = 0;

    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) this.cube[((FRONT * 9) + (i * 3) + j)]  = WHITE;
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) this.cube[((BACK * 9) + (i * 3) + j)]   = YELLOW;
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) this.cube[((LEFT * 9) + (i * 3) + j)]   = BLUE;
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) this.cube[((RIGHT * 9) + (i * 3) + j)]  = GREEN;
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) this.cube[((TOP * 9) + (i * 3) + j)]    = RED;
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) this.cube[((BOTTOM * 9) + (i * 3) + j)] = ORANGE;

  }

  public Cube(Cube other) {
    this.cube = new byte[54];
    this.depth = other.depth + 1;

    for (int i = 0; i < 54; i++)
      this.cube[i] = other.cube[i];
  }

  public static void rotate(Cube current, int side, int direction){
    byte[] temp1 = Cube.getRow(current, side, 0);
    byte[] temp2 = Cube.getColumn(current, side, 0);
    byte[] temp3 = Cube.getRow(current, side, 2);
    byte[] temp4 = Cube.getColumn(current, side, 2);
    Cube.setColumn(current, temp1, side, 2, false);
    Cube.setRow(current, temp2, side, 0, true);
    Cube.setColumn(current, temp3, side, 0, false);
    Cube.setRow(current, temp4, side, 2, true);
  }

  public static byte[] getRow(Cube current, int side, int row) {
    byte[] rowReturned = new byte[3];
    rowReturned[0] = current.cube[(side * 9) + (row * 3) + 0];
    rowReturned[1] = current.cube[(side * 9) + (row * 3) + 1];
    rowReturned[2] = current.cube[(side * 9) + (row * 3) + 2];
    return rowReturned;
  }

  public static byte[] getColumn(Cube current, int side, int col) {
    byte[] colReturned = new byte[3];
    colReturned[0] = current.cube[(side * 9) + 0 + col];
    colReturned[1] = current.cube[(side * 9) + 3 + col];
    colReturned[2] = current.cube[(side * 9) + 6 + col];

    return colReturned;
  }

  public static void setRow(Cube current, byte[] newColors, int side, int row, boolean reverse){
    if(reverse){
      current.cube[(side * 9) + (row*3) + 0] = newColors[2];
      current.cube[(side * 9) + (row*3) + 1] = newColors[1];
      current.cube[(side * 9) + (row*3) + 2] = newColors[0];
    }
    else{
      current.cube[(side * 9) + (row*3) + 0] = newColors[0];
      current.cube[(side * 9) + (row*3) + 1] = newColors[1];
      current.cube[(side * 9) + (row*3) + 2] = newColors[2];
    }
  }

  public static void setColumn(Cube current, byte[] positions, int side, int column, boolean reverse) { //column must be 0 or 2.
    if(reverse){
      current.cube[(side*9) + (3*0) + column ] = positions[2];
      current.cube[(side*9) + (3*1) + column ] = positions[1];
      current.cube[(side*9) + (3*2) + column ] = positions[0];
    }
    else{
      current.cube[(side*9) + (3*0) + column ] = positions[0];
      current.cube[(side*9) + (3*1) + column ] = positions[1];
      current.cube[(side*9) + (3*2) + column ] = positions[2];
    }
  }

  public static Cube operate(Cube oldCube, int operation) {
    Cube currentCube = new Cube(oldCube);
    byte[] tmp = new byte[3];
    switch(operation){
        case ROTATE_FRONT_DER:
        tmp = Cube.getRow(currentCube, TOP, 2);
        Cube.setRow(currentCube, Cube.getColumn(currentCube, LEFT, 2), TOP, 2, true);
        Cube.setColumn(currentCube, Cube.getRow(currentCube, BOTTOM, 0), LEFT, 2, false);
        Cube.setRow(currentCube, Cube.getColumn(currentCube, RIGHT, 0), BOTTOM, 0, true);
        Cube.setColumn(currentCube, tmp, RIGHT, 0, false);
        Cube.rotate(currentCube, FRONT, 1); //Direction not implemented yet
        break;
      case ROTATE_BACK_DER:
        tmp = Cube.getRow(currentCube, TOP, 0);
        Cube.setRow(currentCube, Cube.getColumn(currentCube, LEFT, 0), TOP, 0, true);
        Cube.setColumn(currentCube, Cube.getRow(currentCube, BOTTOM, 2), LEFT, 0,false);
        Cube.setRow(currentCube, Cube.getColumn(currentCube, RIGHT, 2), BOTTOM, 2, true);
        Cube.setColumn(currentCube, tmp, RIGHT, 2, false);
        Cube.rotate(currentCube, BACK, 1); //Direction not implemented yet
        break;
      case ROW_TOP_DER:
        tmp = Cube.getRow(currentCube, FRONT, 0);
        Cube.setRow(currentCube, Cube.getRow(currentCube, LEFT, 0), FRONT, 0, false);
        Cube.setRow(currentCube, Cube.getRow(currentCube, BACK, 0), LEFT, 0, false);
        Cube.setRow(currentCube, Cube.getRow(currentCube, RIGHT, 0), BACK, 0, false);
        Cube.setRow(currentCube, tmp, RIGHT, 0, false);
        Cube.rotate(currentCube, TOP, 1);
        break;
      case ROW_BOTTOM_DER:
        tmp = Cube.getRow(currentCube, FRONT, 2);
        Cube.setRow(currentCube, Cube.getRow(currentCube, LEFT, 2), FRONT, 2, false);
        Cube.setRow(currentCube, Cube.getRow(currentCube, BACK, 2), LEFT, 2, false);
        Cube.setRow(currentCube, Cube.getRow(currentCube, RIGHT, 2), BACK, 2, false);
        Cube.setRow(currentCube, tmp, RIGHT, 2, false);
        Cube.rotate(currentCube, BOTTOM, 1);
        break;
      case COL_LEFT_UP:
        tmp = Cube.getColumn(currentCube, FRONT, 0);
        Cube.setColumn(currentCube, Cube.getColumn(currentCube, BOTTOM, 0), FRONT, 0,false);
        Cube.setColumn(currentCube, Cube.getColumn(currentCube, BACK, 2), BOTTOM, 0, true);
        Cube.setColumn(currentCube, Cube.getColumn(currentCube, TOP, 0), BACK, 2, true);
        Cube.setColumn(currentCube, tmp, TOP, 0,false);
        Cube.rotate(currentCube, LEFT, 1);
        break;
      case COL_RIGHT_UP:
        tmp = Cube.getColumn(currentCube, FRONT, 2);
        Cube.setColumn(currentCube, Cube.getColumn(currentCube, BOTTOM, 2), FRONT, 2, false);
        Cube.setColumn(currentCube, Cube.getColumn(currentCube, BACK, 0), BOTTOM, 2, true);
        Cube.setColumn(currentCube, Cube.getColumn(currentCube, TOP, 2), BACK, 0, true);
        Cube.setColumn(currentCube, tmp, TOP, 2, false);
        Cube.rotate(currentCube, RIGHT, 1);
        break;
      default:
        break;
    }
    return currentCube;
  }

  public static String getColorName(byte color) {
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

  public static void printCube(Cube current) {
    String strCube = "";
    String offset = "        ";

    // Print Top
    for (int i = 0; i < 9; i++) {
      if (i % 3 == 0) strCube += "\n" + offset;
      strCube += Cube.getColorName(current.cube[((TOP * 9) + i)]) + " ";
    }

    strCube += "\n" + offset + "-----\n";
    // Print Left-Front-Right-Back
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        strCube += Cube.getColorName(current.cube[((j * 9) + (i * 3) + 0)]) + " ";
        strCube += Cube.getColorName(current.cube[((j * 9) + (i * 3) + 1)]) + " ";
        strCube += Cube.getColorName(current.cube[((j * 9) + (i * 3) + 2)]) + " | ";
      }
      strCube += "\n";
    }

    strCube += offset + "-----\n";

    // Print Bottom
    for (int i = 0; i < 9; i++) {
      if (i % 3 == 0 && i != 0) strCube += "\n";
      if (i % 3 == 0) strCube += offset;
      strCube += Cube.getColorName(current.cube[((BOTTOM * 9) + i)]) + " ";
    }
System.out.println(strCube);
  /*  for (int w = 0; w < strCube.length(); w++){
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
    System.out.println("\n");
  }
}
