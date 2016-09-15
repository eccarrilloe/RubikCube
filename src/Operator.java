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
    int[] tmp = new int[3];
    switch (operation) {
      case OP_ROTATE_FRONT:
        if (direction == DIR_RIGHT) {
          this.cube.frontSide.rotate(DIR_RIGHT);
          tmp = this.cube.topSide.getRow(ROW_BOTTOM);
          this.cube.topSide.setRow(ROW_BOTTOM, this.cube.leftSide.getColumn(COL_RIGHT), true);
          this.cube.leftSide.setColumn(COL_RIGHT, this.cube.bottomSide.getRow(ROW_TOP), false);
          this.cube.bottomSide.setRow(ROW_TOP, this.cube.rightSide.getColumn(COL_LEFT), true);
          this.cube.rightSide.setColumn(COL_LEFT, tmp, false);
        } else {
          this.cube.frontSide.rotate(DIR_LEFT);
          tmp = this.cube.topSide.getRow(ROW_BOTTOM);
          this.cube.topSide.setRow(ROW_BOTTOM, this.cube.rightSide.getColumn(COL_LEFT), false);
          this.cube.rightSide.setRow(COL_LEFT, this.cube.bottomSide.getColumn(ROW_TOP), true);
          this.cube.bottomSide.setRow(ROW_TOP, this.cube.leftSide.getColumn(COL_RIGHT), false);
          this.cube.leftSide.setColumn(COL_RIGHT, tmp, true);
        }
        break;
      case OP_ROTATE_BACK:
        if (direction == DIR_RIGHT) {
          this.cube.frontSide.rotate(DIR_RIGHT);
          tmp = this.cube.topSide.getRow(ROW_BOTTOM);
          this.cube.topSide.setRow(ROW_BOTTOM, this.cube.leftSide.getColumn(COL_RIGHT), true);
          this.cube.leftSide.setColumn(COL_RIGHT, this.cube.bottomSide.getRow(ROW_TOP), false);
          this.cube.bottomSide.setRow(ROW_TOP, this.cube.rightSide.getColumn(COL_LEFT), true);
          this.cube.rightSide.setColumn(COL_LEFT, tmp, false);
          } else {
          this.cube.frontSide.rotate(DIR_LEFT);
          tmp = this.cube.topSide.getRow(ROW_BOTTOM);
          this.cube.topSide.setRow(ROW_BOTTOM, this.cube.rightSide.getColumn(COL_LEFT), false);
          this.cube.rightSide.setRow(COL_LEFT, this.cube.bottomSide.getColumn(ROW_TOP), true);
          this.cube.bottomSide.setRow(ROW_TOP, this.cube.leftSide.getColumn(COL_RIGHT), false);
          this.cube.leftSide.setColumn(COL_RIGHT, tmp, true);
        }
        break;
      case OP_ROW_TOP:
        if (direction == DIR_RIGHT) {
          this.cube.topSide.rotate(DIR_RIGHT);
          tmp = this.cube.frontSide.getRow(ROW_TOP);
          this.cube.frontSide.setRow(ROW_TOP, this.cube.leftSide.getRow(ROW_TOP), false);
          this.cube.leftSide.setRow(ROW_TOP, this.cube.backSide.getRow(ROW_TOP), false);
          this.cube.backSide.setRow(ROW_TOP, this.cube.rightSide.getRow(ROW_TOP), false);
          this.cube.rightSide.setRow(ROW_TOP, tmp, false);
        } else {
          this.cube.topSide.rotate(DIR_LEFT);
          tmp = this.cube.frontSide.getRow(ROW_TOP);
          this.cube.frontSide.setRow(ROW_TOP, this.cube.rightSide.getRow(ROW_TOP), false);
          this.cube.rightSide.setRow(ROW_TOP, this.cube.backSide.getRow(ROW_TOP), false);
          this.cube.backSide.setRow(ROW_TOP, this.cube.leftSide.getRow(ROW_TOP), false);
          this.cube.leftSide.setRow(ROW_TOP, tmp, false);
        }
        break;
      case OP_ROW_BOTTOM:
        if (direction == DIR_RIGHT) {
          this.cube.bottomSide.rotate(DIR_RIGHT);
          tmp = this.cube.frontSide.getRow(ROW_BOTTOM);
          this.cube.frontSide.setRow(ROW_BOTTOM, this.cube.leftSide.getRow(ROW_BOTTOM), false);
          this.cube.leftSide.setRow(ROW_BOTTOM, this.cube.backSide.getRow(ROW_BOTTOM), false);
          this.cube.backSide.setRow(ROW_BOTTOM,this.cube.rightSide.getRow(ROW_BOTTOM), false);
          this.cube.rightSide.setRow(ROW_BOTTOM, tmp, false);
        } else {
          this.cube.bottomSide.rotate(DIR_LEFT);
          tmp = this.cube.frontSide.getRow(ROW_BOTTOM);
          this.cube.frontSide.setRow(ROW_BOTTOM, this.cube.rightSide.getRow(ROW_BOTTOM), false);
          this.cube.rightSide.setRow(ROW_BOTTOM, this.cube.backSide.getRow(ROW_BOTTOM), false);
          this.cube.backSide.setRow(ROW_BOTTOM, this.cube.leftSide.getRow(ROW_BOTTOM), false);
          this.cube.leftSide.setRow(ROW_BOTTOM, tmp, false);
        }
        break;
      case OP_COL_LEFT:
        if (direction == DIR_UP) {
          this.cube.leftSide.rotate(DIR_LEFT);
          tmp = this.cube.frontSide.getRow(COL_LEFT);
          this.cube.frontSide.setRow(COL_LEFT, this.cube.bottomSide.getColumn(COL_LEFT), false);
          this.cube.bottomSide.setRow(COL_LEFT, this.cube.backSide.getColumn(COL_RIGHT), true);
          this.cube.backSide.setRow(COL_RIGHT, this.cube.topSide.getColumn(COL_LEFT), true);
          this.cube.topSide.setRow(COL_LEFT, tmp, false);
        } else {
          this.cube.leftSide.rotate(DIR_RIGHT);
          tmp = this.cube.frontSide.getRow(COL_LEFT);
          this.cube.frontSide.setRow(COL_LEFT, this.cube.topSide.getColumn(COL_LEFT), false);
          this.cube.topSide.setRow(COL_LEFT, this.cube.backSide.getColumn(COL_RIGHT), true);
          this.cube.backSide.setRow(COL_RIGHT, this.cube.bottomSide.getColumn(COL_LEFT), true);
          this.cube.bottomSide.setRow(COL_LEFT, tmp, false);
        }
        break;
      case OP_COL_RIGHT:
        if (direction == DIR_UP) {
          this.cube.rightSide.rotate(DIR_RIGHT);
          tmp = this.cube.frontSide.getRow(COL_RIGHT);
          this.cube.frontSide.setRow(COL_RIGHT, this.cube.bottomSide.getColumn(COL_RIGHT), false);
          this.cube.bottomSide.setRow(COL_RIGHT, this.cube.backSide.getColumn(COL_LEFT), true);
          this.cube.backSide.setRow(COL_LEFT, this.cube.topSide.getColumn(COL_RIGHT), true);
          this.cube.topSide.setRow(COL_LEFT, tmp, false);
        } else {
          this.cube.rightSide.rotate(DIR_LEFT);
          tmp = this.cube.frontSide.getRow(COL_RIGHT);
          this.cube.frontSide.setRow(COL_RIGHT, this.cube.topSide.getColumn(COL_RIGHT), false);
          this.cube.topSide.setRow(COL_RIGHT, this.cube.backSide.getColumn(COL_LEFT), true);
          this.cube.backSide.setRow(COL_LEFT, this.cube.bottomSide.getColumn(COL_RIGHT), true);
          this.cube.bottomSide.setRow(COL_RIGHT, tmp, false);
        }
        break;
      default: break;
    }
  }

  public boolean validate() {
    return true;
  }

  public static void main(String[] args) {
    Cube cb = new Cube();
    Operator op = new Operator(cb);
    op.operate(1,1);
    System.out.println(cb);
  }
}
