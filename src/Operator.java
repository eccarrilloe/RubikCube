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
      case ROW_BOTTOM:
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
    /*  case COL_LEFT:
        break;
      case COL_RIGHT:
        break;*/
    }
  }

  public boolean validate() {
    return true;
  }

  public static void main(String[] args) {
    Cube cb = new Cube();
    Operator op = new Operator(cb);

    System.out.println(cb);
    op.operate(ROW_BOTTOM, DIR_RIGHT);
    System.out.println("************************");
    System.out.println(cb);
  }
}
