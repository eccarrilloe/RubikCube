
class Main implements Constants {
  public static void main(String[] args) {
    Operator op = new Operator();
    op.printCube(op.cube);
    op.disarm(2);
    System.out.println("Disarmed Cube");
    op.printCube(op.cube);
    System.out.println("Solved Cube");
    long time_start, time_end;
    time_start = System.currentTimeMillis();
    op.assemble(SEARCH_BFS);
    op.printCube(op.cube);
    time_end = System.currentTimeMillis();
    System.out.println("the task has taken "+ ( time_end - time_start ) +" milliseconds");
  }
}
