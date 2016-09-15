/**
 * Constants,java
 * Interface for constants in Rubik's Cube
 * @author eccarrilloe
 */

public interface Constants {

	// Token's Color Code
	public static final int WHITE  = 1;
	public static final int GREEN  = 2;
	public static final int BLUE   = 3;
	public static final int RED    = 4;
	public static final int ORANGE = 5;
	public static final int YELLOW = 6;

	// Token's Colors
	public static final char C_WHITE  = 'W';
	public static final char C_GREEN  = 'G';
	public static final char C_BLUE   = 'B';
	public static final char C_RED    = 'R';
	public static final char C_ORANGE = 'O';
	public static final char C_YELLOW = 'Y';

	// Sides of Cube
	public static final int FRONT  = 1;
	public static final int BACK   = 2;
	public static final int LEFT   = 3;
	public static final int RIGHT  = 4;
	public static final int TOP    = 5;
	public static final int BOTTOM = 6;

	// Directions
	public static final int DIR_UP    = 2;
	public static final int DIR_DOWN  = 1;
	public static final int DIR_RIGHT = 2;
	public static final int DIR_LEFT  = 1;

	// Rows & Columns
	public static final int ROW_TOP    = 0;
	public static final int ROW_MIDDLE = 1;
	public static final int ROW_BOTTOM = 2;
	public static final int COL_LEFT   = 0;
	public static final int COL_MIDDLE = 1;
	public static final int COL_RIGHT  = 2;

	// Operations
	public static final int OPERATIONS = 6;
	public static final int OP_ROTATE_FRONT = 1;
	public static final int OP_ROTATE_BACK  = 2;
	public static final int OP_ROW_TOP      = 3;
	public static final int OP_ROW_BOTTOM   = 4;
	public static final int OP_COL_LEFT     = 5;
	public static final int OP_COL_RIGHT    = 6;

	// Search Types
	public static final int SEARCH_DFS = 0;
	public static final int SEARCH_BFS = 1;
	public static final int SEARCH_IDS = 2;
	public static final int SEARCH_AST = 3;
}
