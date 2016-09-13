/**
 * Side.java
 * Class for Rubik Cube's Sides
 * @author eccarrilloe
 */

public class Side implements Constants {
  public int color;
  public int name;
  Token[][] tokens;

  public Side(int name, int color) {
    this.name = name;
    this.color = color;
    tokens = new Token[3][3];
    this.initTokens();
  }

  public void initTokens() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        tokens[i][j] = new Token(this.color);
      }
    }
  }

  public void setToken(int x, int y, int color) {
    tokens[x][y].color = color;
  }

  public Token[] getRow(int ) {

  }

  public void setRow(Token[] other) {

  }

  public Token[] getColumn() {

  }

  public void setColumn(Token[] other) {

  }

  public void rotate(int direction) {
    if (direction == DIR_LEFT) {

      Token tmp = new Token(tokens[0][0]);

      // Corners
      tokens[0][0] = tokens[0][2];
      tokens[0][2] = tokens[2][2];
      tokens[2][2] = tokens[2][0];
      tokens[2][0] = tmp;

      // Middles
      tmp = new Token(tokens[0][1]);
      tokens[0][1] = tokens[1][2];
      tokens[1][2] = tokens[2][1];
      tokens[2][1] = tokens[1][0];
      tokens[1][0] = tmp;

    } else if (direction == DIR_RIGHT) {

      Token tmp = new Token(tokens[0][0]);

      // Corners
      tokens[0][0] = tokens[2][0];
      tokens[2][0] = tokens[2][2];
      tokens[2][2] = tokens[0][2];
      tokens[0][2] = tmp;

      // Middles
      tmp = new Token(tokens[0][1]);
      tokens[0][1] = tokens[1][0];
      tokens[1][0] = tokens[2][1];
      tokens[2][1] = tokens[1][2];
      tokens[1][2] = tmp;

    }
  }

  public void printSide() {
    System.out.println("------------------------");
    System.out.println("     " + this);
    System.out.println("------------------------");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        System.out.print(tokens[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println("------------------------");
  }

  @Override
  public String toString() {
    String sideName = new String();
    switch (this.name) {
      case FRONT:  sideName = C_FRONT;  break;
      case BACK:   sideName = C_BACK;   break;
      case LEFT:   sideName = C_LEFT;   break;
      case RIGHT:  sideName = C_RIGHT;  break;
      case TOP:    sideName = C_TOP;    break;
      case BOTTOM: sideName = C_BOTTOM; break;
      default: break;
    }
    return sideName;
  }
}
