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

  public void disarm() {

  }

  public void rotate(Side side) {

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
