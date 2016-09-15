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

  public char getColorName(int color){
    switch(color){
      case 1:
      return C_WHITE;
      case 2:
      return C_GREEN;
      case 3:
      return C_BLUE;
      case 4:
      return C_RED;
      case 5:
      return C_ORANGE;
      case 6:
      return C_YELLOW;
    }
    return 'a';
  }

  public String getRowString(int[] row){
    String s = getColorName(row[0]) + " " +
               getColorName(row[1]) + " " +
               getColorName(row[2]);
    return s;
  }

  @Override
  public String toString() {
    String s = "";
    String offSet = "        ";
    //TopSide
    s = s + offSet + getRowString(this.topSide.getRow(0)) + "\n";
    s = s + offSet + getRowString(this.topSide.getRow(1)) + "\n";
    s = s + offSet + getRowString(this.topSide.getRow(2)) + "\n";
    s = s + offSet + "* * *" + "\n";
    s = s + getRowString(this.leftSide.getRow(0)) + " * ";
    s = s + getRowString(this.frontSide.getRow(0)) + " * ";
    s = s + getRowString(this.rightSide.getRow(0)) + " * ";
    s = s + getRowString(this.backSide.getRow(0)) + "\n";

    s = s + getRowString(this.leftSide.getRow(1)) + " * ";
    s = s + getRowString(this.frontSide.getRow(1)) + " * ";
    s = s + getRowString(this.rightSide.getRow(1)) + " * ";
    s = s + getRowString(this.backSide.getRow(1)) + "\n";

    s = s + getRowString(this.leftSide.getRow(2)) + " * ";
    s = s + getRowString(this.frontSide.getRow(2)) + " * ";
    s = s + getRowString(this.rightSide.getRow(2)) + " * ";
    s = s + getRowString(this.backSide.getRow(2)) + "\n";
    s = s + offSet + "* * *" + "\n";
    s = s + offSet + getRowString(this.bottomSide.getRow(0)) + "\n";
    s = s + offSet + getRowString(this.bottomSide.getRow(1)) + "\n";
    s = s + offSet + getRowString(this.bottomSide.getRow(2)) + "\n";

    return s;
  }
}
