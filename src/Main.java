
class Main implements Constants {
  public static void main(String[] args) {
    Operator op = new Operator();
    System.out.println("--- CUBO INICIAL ---");
    op.printCube(op.cube);
    op.disarm(1);
    System.out.println("\n--- CUBO DESARMADO ---");
    op.printCube(op.cube);
    op.assemble(SEARCH_DFS);
    System.out.println("\n--- CUBO ARMADO ---");
    op.printCube(op.cube);
  }
}
