/**
 * Side.java
 * Class for Rubik Cube's Sides
 * @author eccarrilloe
 */

public class Side implements Constants {
  int[][] tokens;

  public Side(int color) {
    tokens = new int[3][3];
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++)
        tokens[i][j] = color;
  }

  public void setToken(int x, int y, int color) {
    tokens[x][y] = color;
  }

  public int[] getRow(int row) {
    int[] tokens = new int[3];
    tokens[0] = this.tokens[row][0];
    tokens[1] = this.tokens[row][1];
    tokens[2] = this.tokens[row][2];
    return tokens;
  }

  public void setRow(int row, int[] tokens, boolean reverse) {
    if (reverse == true) {
      int tmp = tokens[0];
      tokens[0] = tokens[2];
      tokens[2] = tmp;
    }

    this.tokens[row][0] = tokens[0];
    this.tokens[row][1] = tokens[1];
    this.tokens[row][2] = tokens[2];
  }

  public int[] getColumn(int column) {
    int[] tokens = new int[3];
    tokens[0] = this.tokens[0][column];
    tokens[1] = this.tokens[1][column];
    tokens[2] = this.tokens[2][column];
    return tokens;
  }

  public void setColumn(int column, int[] tokens, boolean reverse) {
    if (reverse == true) {
      int tmp = tokens[0];
      tokens[0] = tokens[2];
      tokens[2] = tmp;
    }

    this.tokens[0][column] = tokens[0];
    this.tokens[1][column] = tokens[1];
    this.tokens[2][column] = tokens[2];
  }

  public void rotate(int direction) {
    if (direction == DIR_LEFT) {

      int tmp = tokens[0][0];

      // Corners
      tokens[0][0] = tokens[0][2];
      tokens[0][2] = tokens[2][2];
      tokens[2][2] = tokens[2][0];
      tokens[2][0] = tmp;

      // Middles
      tmp = tokens[0][1];
      tokens[0][1] = tokens[1][2];
      tokens[1][2] = tokens[2][1];
      tokens[2][1] = tokens[1][0];
      tokens[1][0] = tmp;

    } else if (direction == DIR_RIGHT) {

      int tmp = tokens[0][0];

      // Corners
      tokens[0][0] = tokens[2][0];
      tokens[2][0] = tokens[2][2];
      tokens[2][2] = tokens[0][2];
      tokens[0][2] = tmp;

      // Middles
      tmp = tokens[0][1];
      tokens[0][1] = tokens[1][0];
      tokens[1][0] = tokens[2][1];
      tokens[2][1] = tokens[1][2];
      tokens[1][2] = tmp;

    }
  }
}
