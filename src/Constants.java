/**
 * Constants,java
 * Interface for constants in Rubik's Cube
 * @author eccarrilloe
 */

public interface Constants {

	// Color of Tokens
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

	// Name of Sides
	public static final String C_FRONT  = "FRONT";
	public static final String C_BACK   = "BACk";
	public static final String C_LEFT   = "LEFT";
	public static final String C_RIGHT  = "RIGHT";
	public static final String C_TOP    = "TOP";
	public static final String C_BOTTOM = "BOTTOM";

	// Directions
	public static final int DIR_UP    = 0;
	public static final int DIR_LEFT  = 1;
	public static final int DIR_RIGHT = 2;
	public static final int DIR_DOWN  = 3;

	// Operations
	public static final int ROTATE_FRONT = 3;
	public static final int ROTATE_BACK = 4;
	public static final int ROW_TOP = 0;
	public static final int ROW_BOTTOM = 2;
	public static final int COL_LEFT = 0;
	public static final int COL_RIGHT = 2;



}
