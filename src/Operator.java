/**
 * Operator.java
 * Class for Rubik Cube's Operator
 * @author eccarrilloe
 */

public class Operator implements Constants {
  public Cube cube;

  public Operator() {
    this.cube = new Cube();
  }

  public Operator(Cube cube) {
    this.cube = cube;
  }

  public void assemble() {

  }

  public void disarm(int operations) {

  }

  public void operate(int operation, int direction) {
    Token[] tmp = new Token[3];
    switch (operation) {
      case ROTATE_FRONT:
        if (direction == DIR_RIGHT) {
          this.cube.frontSide.rotate(DIR_RIGHT);
          tmp = this.cube.topSide.getRow(ROW_BOTTOM);
          this.cube.topSide.setRow(ROW_BOTTOM, this.cube.leftSide.getColumn(COL_RIGHT), true);
          this.cube.leftSide.setColumn(COL_RIGHT, this.cube.bottomSide.getRow(ROW_TOP), false);
          this.cube.bottomSide.setRow(ROW_TOP, this.cube.rightSide.getColumn(COL_LEFT), true);
          this.cube.rightSide.setRow(COL_LEFT, tmp, false);
        } else {
          this.cube.frontSide.rotate(DIR_LEFT);
          tmp = this.cube.topSide.getRow(ROW_BOTTOM);
          this.cube.topSide.setRow(ROW_BOTTOM, this.cube.rightSide.getColumn(COL_LEFT), false);
          this.cube.rightSide.setRow(COL_LEFT, this.cube.bottomSide.getColumn(ROW_TOP), true);
          this.cube.bottomSide.setRow(ROW_TOP, this.cube.leftSide.getColumn(COL_RIGHT), false);
          this.cube.leftSide.setColumn(COL_RIGHT, tmp, true);
        }
        break;
      case ROTATE_BACK:
        if (direction == DIR_RIGHT) {
          this.cube.frontSide.rotate(DIR_RIGHT);
          tmp = this.cube.topSide.getRow(ROW_BOTTOM);
          this.cube.topSide.setRow(ROW_BOTTOM, this.cube.leftSide.getColumn(COL_RIGHT), true);
          this.cube.leftSide.setColumn(COL_RIGHT, this.cube.bottomSide.getRow(ROW_TOP), false);
          this.cube.bottomSide.setRow(ROW_TOP, this.cube.rightSide.getColumn(COL_LEFT), true);
          this.cube.rightSide.setRow(COL_LEFT, tmp, false);
          } else {
          this.cube.frontSide.rotate(DIR_LEFT);
          tmp = this.cube.topSide.getRow(ROW_BOTTOM);
          this.cube.topSide.setRow(ROW_BOTTOM, this.cube.rightSide.getColumn(COL_LEFT), false);
          this.cube.rightSide.setRow(COL_LEFT, this.cube.bottomSide.getColumn(ROW_TOP), true);
          this.cube.bottomSide.setRow(ROW_TOP, this.cube.leftSide.getColumn(COL_RIGHT), false);
          this.cube.leftSide.setColumn(COL_RIGHT, tmp, true);
      }
        break;
      case ROW_TOP:
        break;
      case ROW_BOTTOM:
        break;
      case COL_LEFT:
        break;
      case COL_RIGHT:
        break;
    }
  }

  public boolean validate() {
    return true;
  }

  public static void main(String[] args) {
    Cube cb = new Cube();
    Operator op = new Operator(cb);

    cb.frontSide.setToken(0, 0, BLUE);
    cb.frontSide.setToken(2, 2, RED);
    cb.frontSide.setToken(0, 1, GREEN);
    cb.frontSide.setToken(2, 1, YELLOW);

    cb.frontSide.printSide();
    cb.frontSide.rotate(DIR_RIGHT);
    cb.frontSide.printSide();
  }
}
