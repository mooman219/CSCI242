import java.io.File;
import java.util.Scanner;

/**
 * Driver class.
 *
 * @author Joseph Cumbo (mooman219)
 */
public class Routing2 {

    // Scanner used for taking user input.
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //
        // Process the user's input for a filename.
        //
        String input = null;
        while (true) {
            System.out.print("Enter graph data filename: ");
            input = scanner.nextLine();
            if (input.length() == 0) {
                System.out.println("Invalid file name.");
            } else {
                break;
            }
        }
        //
        // Check if the file exists
        //
        if (!new File(input).exists()) {
            System.out.println("File '" + input + "' does not exist.");
            return;
        }
        //
        // Create the Graph
        //
        Graph graph = new Graph(input);
        System.out.println(graph.toString());
        //
        // Ask the user for the type of search
        //
        while (true) {
            System.out.print("Enter 'D' for DFS, 'B' for BFS, or other to quit: ");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("b") || input.equalsIgnoreCase("d")) {
                //
                // Process the user's input for a start and end node.
                //
                String startName = null;
                String finishName = null;
                while (true) {
                    System.out.print("Enter starting node name: ");
                    startName = scanner.nextLine();
                    System.out.print("Enter finishing node name: ");
                    finishName = scanner.nextLine();
                    if (!graph.isInGraph(startName)) {
                        System.out.println(startName + " is not in the Graph.");
                    } else if (!graph.isInGraph(finishName)) {
                        System.out.println(finishName + " is not in the Graph.");
                    } else {;
                        break;
                    }
                }
                //
                // Run the search based on input.
                //
                System.out.println("Checking for path existence...");
                if (input.equalsIgnoreCase("b")) {
                    if (graph.canReachBFS(startName, finishName)) {
                        System.out.println("It is possible to get from " + startName
                                + " to " + finishName + ":");
                        graph.printPathBFS(startName, finishName);
                        System.out.println("Done!");
                    } else {
                        System.out.println("There is no connection between " + startName
                                + " and " + finishName + ".");
                    }
                } else {
                    if (graph.canReachDFS(startName, finishName)) {
                        System.out.println("It is possible to get from " + startName
                                + " to " + finishName + ":");
                        graph.printPathDFS(startName, finishName);
                        System.out.println("Done!");
                    } else {
                        System.out.println("There is no connection between " + startName
                                + " and " + finishName + ".");
                    }
                }
            } else {
                return;
            }
            System.out.println("");
        }
    }
}
