/**
 * Operator.java
 * Class for Rubik Cube's Operator
 * @author eccarrilloe
 */
import java.util.concurrent.ThreadLocalRandom;

public class Operator implements Constants {
  public Cube cube;
  public int maxDepth;

  public Operator() {
    this.maxDepth = 0;
    this.cube = new Cube();
  }

  public Operator(Cube cube) {
    this.maxDepth = 1000;
    this.cube = cube;
  }

  public void assemble(int searchType) {
    switch (searchType) {
      case SEARCH_DFS: this.assembleDFS(); break;
      case SEARCH_BFS: this.assembleBFS(); break;
      case SEARCH_IDS: this.assembleIDS(); break;
      case SEARCH_AST: this.assembleAST(); break;
    }
  }

  private void assembleDFS() {
    while(! this.exceedMaxDepth()) {

    }
  }

  private void assembleBFS() {
    while(! this.exceedMaxDepth()) {

    }
  }

  private void assembleIDS() {
    while(! this.exceedMaxDepth()) {

    }
  }

  private void assembleAST() {
    while(! this.exceedMaxDepth()) {

    }
  }

  private boolean exceedMaxDepth() {
    return this.cube.level > this.maxDepth;
  }

  public void disarm(int operations) {
    this.maxDepth = this.maxDepth + operations;
    for (int i = 0; i < operations; i++) {
      int operation = ThreadLocalRandom.current().nextInt(1, 6 + 1);
      int direction = ThreadLocalRandom.current().nextInt(1, 2 + 1);
      this.operate(operation, direction);
    }
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
    boolean valid = this.validateSide(this.cube.frontSide);
    valid = this.validateSide(this.cube.topSide);
    valid = this.validateSide(this.cube.bottomSide);
    valid = this.validateSide(this.cube.leftSide);
    valid = this.validateSide(this.cube.rightSide);
    valid = this.validateSide(this.cube.backSide);
    return valid;
  }

  private boolean validateSide(Side sd) {
    boolean valid = true;
    int color = sd.tokens[0][0];
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++)
        valid = color == sd.tokens[i][j];
    return valid;
  }

  public static void main(String[] args) {
    Operator op = new Operator();
    System.out.println(op.cube);
    op.disarm(15);
    System.out.println(op.cube);
  }
}
