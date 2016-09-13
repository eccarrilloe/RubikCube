/**
 * Cube.java
 * Class for Rubik Cube
 * @author eccarrilloe
 */

public class Cube implements Constants {

  public Side frontSide;
  public Side rightSide;
  public Side leftSide;
  public Side topSide;
  public Side bottomSide;
  public Side backSide;

  public Cube() {
    this.frontSide  = new Side(FRONT, WHITE);
    this.rightSide  = new Side(RIGHT, GREEN);
    this.leftSide   = new Side(LEFT, BLUE);
    this.topSide    = new Side(TOP, RED);
    this.bottomSide = new Side(BOTTOM, ORANGE);
    this.backSide   = new Side(BACK, YELLOW);
  }

  public Cube(Side frontSide, Side rightSide, Side leftSide, Side topSide, Side bottomSide, Side backSide) {
    this.frontSide  = frontSide;
    this.rightSide  = rightSide;
    this.leftSide   = leftSide;
    this.topSide    = topSide;
    this.bottomSide = bottomSide;
    this.backSide   = backSide;
  }

  @Override
  public String toString() {
    return "";
  }

}
