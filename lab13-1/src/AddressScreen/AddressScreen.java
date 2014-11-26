package AddressScreen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * @author Joseph Cumbo (mooman219)
 */
public class AddressScreen {

    /**
     * Main method.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new AddressScreen().show();
    }

    private JFrame window;

    /**
     * Initializes a new AddressScreen.
     */
    public AddressScreen() {
        this.window = new JFrame("Address Information");

        JPanel area = new JPanel();
        area.setLayout(new BorderLayout());

        JPanel input = new JPanel(new GridLayout(6, 2)); // Extra row added for padding
        input.add(new JLabel("Name"));
        input.add(new JTextField(""));
        input.add(new JLabel("Address"));
        input.add(new JTextField(""));
        input.add(new JLabel("City"));
        input.add(new JTextField(""));
        input.add(new JLabel("State"));
        input.add(new JTextField(""));
        input.add(new JLabel("Zip"));
        input.add(new JTextField(""));
        area.add(input, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttons.add(new JButton("Add"));
        buttons.add(new JButton("Modify"));
        buttons.add(new JButton("Delete"));
        area.add(buttons, BorderLayout.PAGE_END);

        window.add(area);
        window.setPreferredSize(new Dimension(400, 200));
        window.setResizable(false);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Displays the input window.
     */
    public void show() {
        window.setVisible(true);
    }

    /**
     * Hides the input window.
     */
    public void hide() {
        window.setVisible(false);
    }
}
