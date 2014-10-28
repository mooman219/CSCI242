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
        // Process the user's input for a start and end node.
        //
        while (true) {
            System.out.print("Please enter a start and end node "
                    + "seperated by a space (Example: \"start end\"): ");
            input = scanner.nextLine();
            String[] info = input.split(" ");
            if (input.length() == 0 || info.length < 2) {
                System.out.println("Invalid entry.");
            } else if (!graph.isInGraph(info[0])) {
                System.out.println(info[0] + " is not in the Graph.");
            } else if (!graph.isInGraph(info[1])) {
                System.out.println(info[1] + " is not in the Graph.");
            } else {
                graph.printPathBFS(info[0], info[1]);
                break;
            }
        }
    }
}
