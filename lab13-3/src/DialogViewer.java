import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
                result = JOptionPane.showConfirmDialog(null, "Are you sure?");
                switch (result) {
                    case JOptionPane.CANCEL_OPTION:
                        System.out.println("Cancel was pressed.");
                        break;
                    case JOptionPane.YES_OPTION:
                        System.out.println("Yes was pressed.");
                        break;
                    case JOptionPane.NO_OPTION:
                        System.out.println("No was pressed.");
                        break;
                    default:
                        System.out.println("The window was closed without input.");
                }
                break;
            case "m":
                break;
            default:
                System.out.println("Usage: 'f', 'c', or 'm'.");
                return;
        }
        System.exit(0);
    }
}
