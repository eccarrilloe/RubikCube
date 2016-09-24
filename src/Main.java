
class Main implements Constants {
  public static void main(String[] args) {
    Operator op = new Operator();
    op.printCube(op.cube);
    op.disarm(4);
    System.out.println("Disarmed Cube");
    op.printCube(op.cube);
    System.out.println("Solved Cube");
    op.assemble(SEARCH_BFS);
    op.printCube(op.cube);
  }
}
