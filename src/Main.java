
class Main implements Constants {
  public static void main(String[] args) {


    int searchType = SEARCH_DFS;
    try {
      switch (args[0]) {
        case "DFS": searchType = SEARCH_DFS; break;
        case "BFS": searchType = SEARCH_BFS; break;
        case "IDS": searchType = SEARCH_IDS; break;
        case "AST": searchType = SEARCH_AST; break;
        default: break;
      }
    } catch (Exception e) {
    }

    Operator operator = new Operator();

    System.out.println("----------- CUBE INIT -----------");
    Cube.printCube(operator.cube);

    System.out.println("--------- CUBE DISARMED ---------");
    int operations = 1;
    operator.disarm(operations);
    System.out.println("-> Cube after " + operations + " operations:\n");
    Cube.printCube(operator.cube);

    System.out.println("--------- CUBE SOLVED -----------");
    long time_start;
    time_start = System.currentTimeMillis();
    operator.assemble(SEARCH_AST);
    long total_time = System.currentTimeMillis() - time_start;
    Cube.printCube(operator.cube);
    System.out.println("Task has taken "+ total_time + " milliseconds");
  }
}
