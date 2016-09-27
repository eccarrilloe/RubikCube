/**
* Constants.java
* Constants for Rubik Cube
*/

interface Constants {

  //bash colors for cube
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";

  // Colors
  public static final byte WHITE  = 0x1;
  public static final byte GREEN  = 0x2;
  public static final byte BLUE   = 0x3;
  public static final byte RED    = 0x4;
  public static final byte ORANGE = 0x5;
  public static final byte YELLOW = 0x6;

  // Sides
  public static final int SIDES = 6;
  public static final int LEFT   = 0;
  public static final int FRONT  = 1;
  public static final int RIGHT  = 2;
  public static final int BACK   = 3;
  public static final int TOP    = 4;
  public static final int BOTTOM = 5;

  // Position of level in array
  public static final int LEVEL = 54;

  // Number of operations
  public static final int OPERATIONS = 6;

  // Operations
  public static final int ROTATE_FRONT_DER = 1;
  public static final int ROTATE_BACK_DER  = 2;
  public static final int ROW_TOP_DER      = 3;
  public static final int ROW_BOTTOM_DER   = 4;
  public static final int COL_LEFT_UP      = 5;
  public static final int COL_RIGHT_UP     = 6;

  // Inverse operations (7-12)
  public static final int ROTATE_FRONT_IZQ  = 7;
  public static final int ROTATE_BACK_IZQ   = 8;
  public static final int ROW_TOP_IZQ       = 9;
  public static final int ROW_BOTTOM_IZQ    = 10;
  public static final int COL_LEFT_DOWN     = 11;
  public static final int COL_RIGHT_DOWN    = 12;

  // Rotate
  public static final int ROTATE_DER = 1;
  public static final int ROTATE_IZQ = 2;

  // Search
  public static final int SEARCH_DFS = 0;
  public static final int SEARCH_BFS = 1;
  public static final int SEARCH_IDS = 2;
  public static final int SEARCH_AST = 3;
}
