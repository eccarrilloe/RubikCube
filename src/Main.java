import java.util.Scanner;

class Main implements Constants {
  public static void main(String[] args) {

    Operator operator = new Operator();

    System.out.println("----------- CUBE INIT -----------");
    Cube.printCube(operator.cube);

    Scanner in = new Scanner(System.in);
    System.out.print("Operations?: ");
    int operations = in.nextInt();
    operator.disarm(operations);
    System.out.println("--------- CUBE DISARMED ---------");
    Cube.printCube(operator.cube);

    System.out.println("Search methods: (0 - DFS, 1 - BFS, 2 - IDS, 3 - A*)");
    System.out.print("Search method?: ");
    int method = in.nextInt();
    while (method < 0 || method > 3) {
      System.out.print("Search method?: ");
      method = in.nextInt();
    }

    long time_start = System.currentTimeMillis();
    operator.assemble(method);
    long total_time = System.currentTimeMillis() - time_start;

    System.out.println("--------- CUBE SOLVED -----------");
    Cube.printCube(operator.cube);
    if (operator.validate(operator.cube)) {
      System.out.println("SOLVED in: " + total_time + " miliseconds." );
      System.out.println("SOLUTION IN DEPTH: " + operator.cube.depth);
    } else {
      System.out.println("SEARCH TIME: " + total_time + " miliseconds." );
      System.out.println("MAX SEARCH DEPTH: " + operator.maxDepth);
    }
    System.out.println("NODES EXPANDED: " + operator.maxNodesExpanded);
  }
}
