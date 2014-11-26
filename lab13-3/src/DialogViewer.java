/**
 * Prompts the user for some type of input based on the command line arguments.
 *
 * @author Joseph Cumbo (mooman219)
 */
public class DialogViewer {

    /**
     * Main method.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Insufficient arguments.");
            return;
        }
        switch (args[0]) {
            case "f":
                break;
            case "c":
                break;
            case "m":
                break;
            default:
                System.out.println("Usage: 'f', 'c', or 'm'.");
                return;
        }
    }
}
