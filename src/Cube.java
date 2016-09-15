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

  public Cube(Cube cb) {
    this(cb.frontSide, cb.rightSide, cb.leftSide, cb.topSide, cb.bottomSide, cb.backSide);
  }

  public Cube(Side frontSide, Side rightSide, Side leftSide, Side topSide, Side bottomSide, Side backSide) {
    this.frontSide  = new Side(frontSide);
    this.rightSide  = new Side(rightSide);
    this.leftSide   = new Side(leftSide);
    this.topSide    = new Side(topSide);
    this.bottomSide = new Side(bottomSide);
    this.backSide   = new Side(backSide);
  }

  @Override
  public String toString() {
    String s = "";
    String offSet = "      ";
    int[] firstRow = this.topSide.getRow(ROW_TOP);



    return "";
  }

}
