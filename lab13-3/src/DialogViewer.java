import javax.swing.JFileChooser;

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
        int result;
        switch (args[0]) {
            case "f":
                JFileChooser chooser = new JFileChooser();
                result = chooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    System.out.println("File: " + chooser.getSelectedFile().getName());
                } else {
                    System.out.println("No file chosen.");
                }
                break;
            case "c":
            case "m":
                break;
            default:
                System.out.println("Usage: 'f', 'c', or 'm'.");
                return;
        }
        System.exit(0);
    }
}
