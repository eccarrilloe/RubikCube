/**
* Constants.java
* Constants for Rubik Cube
*/

interface Constants {

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
  public static final int COL_LEFT_UP     = 5;
  public static final int COL_RIGHT_UP    = 6;

  // Search
  public static final int SEARCH_DFS = 0;
  public static final int SEARCH_BFS = 1;
  public static final int SEARCH_IDS = 2;
  public static final int SEARCH_AST = 3;
}
