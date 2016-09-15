/**
 * Operator.java
 * Class for Rubik Cube's Operator
 * @author eccarrilloe
 */
import java.util.concurrent.ThreadLocalRandom;
import java.util.Stack;

public class Operator implements Constants {
  public int maxDepth;

  public Operator() {
    this.maxDepth = 0;
  }

  public Cube disarm(Cube cube, int operations) {
    this.maxDepth = operations;
    for (int i = 0; i < operations; i++) {
      int operation = ThreadLocalRandom.current().nextInt(1, OPERATIONS + 1);
      int direction = ThreadLocalRandom.current().nextInt(1, 2 + 1);
      cube = this.operate(cube, operation, direction);
    }
    return cube;
  }

  public Cube assemble(Cube cube, int searchType) {
    Cube res = new Cube();
    switch (searchType) {
      case SEARCH_DFS: res = this.assembleDFS(cube); break;
      case SEARCH_BFS: res = this.assembleBFS(cube); break;
      case SEARCH_IDS: res = this.assembleIDS(cube); break;
      case SEARCH_AST: res = this.assembleAST(cube); break;
    }
    return res;
  }

  private Cube assembleDFS(Cube cube) {
    Stack<Cube> tree = new Stack<>();
    tree.push(cube);
    Cube currentCube = new Cube();
    Cube children = new Cube();
    boolean found = false;

    while (! tree.empty()) {
      currentCube = tree.pop();

      if (this.exceedMaxDepth(currentCube))
        continue;

      if (this.validate(currentCube))
        break;

      for (int op = 1; op <= OPERATIONS; op++) {
        // Operation 1
        children = this.operate(currentCube, op, 1);
        children.level = currentCube.level + 1;
        if (this.validate(children)) {
          currentCube = children;
          found = true;
          break;
        }
        if (tree.search(children) == -1) {
          tree.push(children);
          System.out.print("NF ");
        } else {
          System.out.print("F ");
        }

        // Operation 2
        children = this.operate(currentCube, op, 2);
        children.level = currentCube.level + 1;
        if (this.validate(children)) {
          currentCube = children;
          found = true;
          break;
        }
        if (tree.search(children) == -1) {
          tree.push(children);
          System.out.print("NF ");
        } else {
          System.out.print("F ");
        }
      }

      if (found == true)
        break;
    }

    return currentCube;
  }

  private Cube assembleBFS(Cube cube) {
    Cube res = new Cube();
    while(! this.exceedMaxDepth(cube)) {

    }
    return res;
  }

  private Cube assembleIDS(Cube cube) {
    Cube res = new Cube();
    while(! this.exceedMaxDepth(cube)) {

    }
    return res;
  }

  private Cube assembleAST(Cube cube) {
    Cube res = new Cube();
    while(! this.exceedMaxDepth(cube)) {

    }
    return res;
  }

  private boolean exceedMaxDepth(Cube cube) {
    return cube.level > this.maxDepth;
  }

  public Cube operate(Cube cb, int operation, int direction) {
    Cube cube = new Cube(cb);
    int[] tmp = new int[3];
    switch (operation) {
      case OP_ROTATE_FRONT:
        if (direction == DIR_RIGHT) {
          cube.frontSide.rotate(DIR_RIGHT);
          tmp = cube.topSide.getRow(ROW_BOTTOM);
          cube.topSide.setRow(ROW_BOTTOM, cube.leftSide.getColumn(COL_RIGHT), true);
          cube.leftSide.setColumn(COL_RIGHT, cube.bottomSide.getRow(ROW_TOP), false);
          cube.bottomSide.setRow(ROW_TOP, cube.rightSide.getColumn(COL_LEFT), true);
          cube.rightSide.setColumn(COL_LEFT, tmp, false);
        } else {
          cube.frontSide.rotate(DIR_LEFT);
          tmp = cube.topSide.getRow(ROW_BOTTOM);
          cube.topSide.setRow(ROW_BOTTOM, cube.rightSide.getColumn(COL_LEFT), false);
          cube.rightSide.setColumn(COL_LEFT, cube.bottomSide.getRow(ROW_TOP), true);
          cube.bottomSide.setRow(ROW_TOP, cube.leftSide.getColumn(COL_RIGHT), false);
          cube.leftSide.setColumn(COL_RIGHT, tmp, true);
        }
        break;
      case OP_ROTATE_BACK:
        if (direction == DIR_RIGHT) {
          cube.backSide.rotate(DIR_RIGHT);
          tmp = cube.topSide.getRow(ROW_TOP);
          cube.topSide.setRow(ROW_TOP, cube.leftSide.getColumn(COL_LEFT), true);
          cube.leftSide.setColumn(COL_LEFT, cube.bottomSide.getRow(ROW_BOTTOM), false);
          cube.bottomSide.setRow(ROW_BOTTOM, cube.rightSide.getColumn(COL_RIGHT), true);
          cube.rightSide.setColumn(COL_RIGHT, tmp, false);
          } else {
          cube.backSide.rotate(DIR_LEFT);
          tmp = cube.topSide.getRow(ROW_TOP);
          cube.topSide.setRow(ROW_TOP, cube.rightSide.getColumn(COL_RIGHT), false);
          cube.rightSide.setColumn(COL_RIGHT, cube.bottomSide.getRow(ROW_BOTTOM), true);
          cube.bottomSide.setRow(ROW_BOTTOM, cube.leftSide.getColumn(COL_LEFT), false);
          cube.leftSide.setColumn(COL_LEFT, tmp, true);
        }
        break;
      case OP_ROW_TOP:
        if (direction == DIR_RIGHT) {
          cube.topSide.rotate(DIR_RIGHT);
          tmp = cube.frontSide.getRow(ROW_TOP);
          cube.frontSide.setRow(ROW_TOP, cube.leftSide.getRow(ROW_TOP), false);
          cube.leftSide.setRow(ROW_TOP, cube.backSide.getRow(ROW_TOP), false);
          cube.backSide.setRow(ROW_TOP, cube.rightSide.getRow(ROW_TOP), false);
          cube.rightSide.setRow(ROW_TOP, tmp, false);
        } else {
          cube.topSide.rotate(DIR_LEFT);
          tmp = cube.frontSide.getRow(ROW_TOP);
          cube.frontSide.setRow(ROW_TOP, cube.rightSide.getRow(ROW_TOP), false);
          cube.rightSide.setRow(ROW_TOP, cube.backSide.getRow(ROW_TOP), false);
          cube.backSide.setRow(ROW_TOP, cube.leftSide.getRow(ROW_TOP), false);
          cube.leftSide.setRow(ROW_TOP, tmp, false);
        }
        break;
      case OP_ROW_BOTTOM:
        if (direction == DIR_RIGHT) {
          cube.bottomSide.rotate(DIR_RIGHT);
          tmp = cube.frontSide.getRow(ROW_BOTTOM);
          cube.frontSide.setRow(ROW_BOTTOM, cube.leftSide.getRow(ROW_BOTTOM), false);
          cube.leftSide.setRow(ROW_BOTTOM, cube.backSide.getRow(ROW_BOTTOM), false);
          cube.backSide.setRow(ROW_BOTTOM,cube.rightSide.getRow(ROW_BOTTOM), false);
          cube.rightSide.setRow(ROW_BOTTOM, tmp, false);
        } else {
          cube.bottomSide.rotate(DIR_LEFT);
          tmp = cube.frontSide.getRow(ROW_BOTTOM);
          cube.frontSide.setRow(ROW_BOTTOM, cube.rightSide.getRow(ROW_BOTTOM), false);
          cube.rightSide.setRow(ROW_BOTTOM, cube.backSide.getRow(ROW_BOTTOM), false);
          cube.backSide.setRow(ROW_BOTTOM, cube.leftSide.getRow(ROW_BOTTOM), false);
          cube.leftSide.setRow(ROW_BOTTOM, tmp, false);
        }
        break;
      case OP_COL_LEFT:
        if (direction == DIR_UP) {
          cube.leftSide.rotate(DIR_LEFT);
          tmp = cube.frontSide.getColumn(COL_LEFT);
          cube.frontSide.setColumn(COL_LEFT, cube.bottomSide.getColumn(COL_LEFT), false);
          cube.bottomSide.setColumn(COL_LEFT, cube.backSide.getColumn(COL_RIGHT), true);
          cube.backSide.setColumn(COL_RIGHT, cube.topSide.getColumn(COL_LEFT), true);
          cube.topSide.setColumn(COL_LEFT, tmp, false);
        } else {
          cube.leftSide.rotate(DIR_RIGHT);
          tmp = cube.frontSide.getColumn(COL_LEFT);
          cube.frontSide.setColumn(COL_LEFT, cube.topSide.getColumn(COL_LEFT), false);
          cube.topSide.setColumn(COL_LEFT, cube.backSide.getColumn(COL_RIGHT), true);
          cube.backSide.setColumn(COL_RIGHT, cube.bottomSide.getColumn(COL_LEFT), true);
          cube.bottomSide.setColumn(COL_LEFT, tmp, false);
        }
        break;
      case OP_COL_RIGHT:
        if (direction == DIR_UP) {
          cube.rightSide.rotate(DIR_RIGHT);
          tmp = cube.frontSide.getColumn(COL_RIGHT);
          cube.frontSide.setColumn(COL_RIGHT, cube.bottomSide.getColumn(COL_RIGHT), false);
          cube.bottomSide.setColumn(COL_RIGHT, cube.backSide.getColumn(COL_LEFT), true);
          cube.backSide.setColumn(COL_LEFT, cube.topSide.getColumn(COL_RIGHT), true);
          cube.topSide.setColumn(COL_RIGHT, tmp, false);
        } else{
          cube.rightSide.rotate(DIR_LEFT);
          tmp = cube.frontSide.getColumn(COL_RIGHT);
          cube.frontSide.setColumn(COL_RIGHT, cube.topSide.getColumn(COL_RIGHT), false);
          cube.topSide.setColumn(COL_RIGHT, cube.backSide.getColumn(COL_LEFT), true);
          cube.backSide.setColumn(COL_LEFT, cube.bottomSide.getColumn(COL_RIGHT), true);
          cube.bottomSide.setColumn(COL_RIGHT, tmp, false);
        }
        break;
      default: break;
    }
    return cube;
  }

  public boolean validate(Cube cube) {
    boolean valid = this.validateSide(cube.frontSide);
    valid = valid && this.validateSide(cube.topSide);
    valid = valid && this.validateSide(cube.leftSide);
    valid = valid && this.validateSide(cube.rightSide);
    valid = valid && this.validateSide(cube.bottomSide);
    valid = valid && this.validateSide(cube.backSide);

    return valid;
  }

  private boolean validateSide(Side sd) {
    boolean valid = true;
    int color = sd.tokens[0][0];
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++)
        valid = valid && (color == sd.tokens[i][j]);
    return valid;
  }


  public static void main(String[] args) {
    Cube cube = new Cube();
    Operator operator = new Operator();

    System.out.println(cube);
    cube = operator.disarm(cube, 8);
    System.out.println(cube);
    cube = operator.assemble(cube, SEARCH_DFS);
    System.out.println();
    System.out.println(cube);
    System.out.println("OPERATIONS: " + cube.level);
  }
}
