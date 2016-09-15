/**
 * Cube.java
 * Class for Rubik Cube
 * @author eccarrilloe
 */

public class Cube implements Constants {

  public int level;
  public Side frontSide;
  public Side rightSide;
  public Side leftSide;
  public Side topSide;
  public Side bottomSide;
  public Side backSide;

  public Cube() {
    this.frontSide  = new Side(WHITE);
    this.rightSide  = new Side(GREEN);
    this.leftSide   = new Side(BLUE);
    this.topSide    = new Side(RED);
    this.bottomSide = new Side(ORANGE);
    this.backSide   = new Side(YELLOW);
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
    String s = "";
    String offSet = "      ";
    int[] firstRow = this.topSide.getRow(ROW_TOP);



    return "";
  }

}
