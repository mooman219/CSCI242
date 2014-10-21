import java.io.File;
import java.util.Scanner;

/**
 * @author Joseph Cumbo
 */
public class Routing {

    // Scanner used for taking user input.
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //
        // Process the user's input
        //
        String input = "";
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
        System.out.println(graph.toString());
    }
}
