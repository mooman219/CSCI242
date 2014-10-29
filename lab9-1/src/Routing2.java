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
            System.out.print("Please enter a filename: ");
            input = scanner.nextLine();
            if (input.length() == 0) {
                System.out.println("Invalid file name.");
            } else {
                break;
            }
        }
        System.out.println("");
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
        System.out.println("The links for this graph are:");
        System.out.println(graph.toString() + "\n");

        //
        // Ask the user for the type of search
        //
        while (true) {
            System.out.print("What type of search of search do you want? 'B'"
                    + " for BFS, 'D' for DFS, or other to quit: ");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("b") || input.equalsIgnoreCase("d")) {
                break;
            } else {
                return;
            }
        }
        //
        // Process the user's input for a start and end node.
        //
        String startName = null;
        String finishName = null;
        while (true) {
            System.out.print("Please enter a start node: ");
            startName = scanner.nextLine();
            System.out.print("Please enter a end node: ");
            finishName = scanner.nextLine();
            if (!graph.isInGraph(startName)) {
                System.out.println(startName + " is not in the Graph.");
            } else if (!graph.isInGraph(finishName)) {
                System.out.println(finishName + " is not in the Graph.");
            } else {
                break;
            }
        }
        //
        // Run the search based on input.
        //
        if (input.equalsIgnoreCase("b")) {
            if (graph.canReachBFS(startName, finishName)) {
                System.out.println("It is possible to get from " + startName
                        + " to " + finishName + ":");
                graph.printPathBFS(startName, finishName);
            } else {
                System.out.println("There is no connection between " + startName
                        + " and " + finishName + ".");
            }
        } else {
            if (graph.canReachDFS(startName, finishName)) {
                System.out.println("It is possible to get from " + startName
                        + " to " + finishName + ":");
                graph.printPathDFS(startName, finishName);
            } else {
                System.out.println("There is no connection between " + startName
                        + " and " + finishName + ".");
            }
        }
    }
}
