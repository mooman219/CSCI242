import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * Prompts the user for a numeric input.
 *
 * @author Joseph Cumbo (mooman219)
 */
public class ScramblePad {

    /**
     * Main method.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new ScramblePad().show();
    }

    private JFrame window;

    /**
     * Initializes a new AddressScreen.
     */
    public ScramblePad() {
        this.window = new JFrame("DCS Scramble Pad");

        JPanel area = new JPanel();
        area.setLayout(new BorderLayout());

        area.add(new JLabel("LOCKED", SwingConstants.CENTER), BorderLayout.PAGE_START);

        JPanel input = new JPanel(new GridLayout(4, 3));
        input.add(new JButton("1"));
        input.add(new JButton("2"));
        input.add(new JButton("3"));
        input.add(new JButton("4"));
        input.add(new JButton("5"));
        input.add(new JButton("6"));
        input.add(new JButton("7"));
        input.add(new JButton("8"));
        input.add(new JButton("9"));
        input.add(new JButton(""));
        input.add(new JButton("0"));
        input.add(new JButton(""));
        area.add(input, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttons.add(new JButton("Start"));
        buttons.add(new JButton("Okay"));
        area.add(buttons, BorderLayout.PAGE_END);

        this.window.add(area);
        this.window.setPreferredSize(new Dimension(200, 300));
        this.window.pack();
        this.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private JButton createButton(String text) {
        JButton button = new JButton("1");
        button.setForeground(Color.YELLOW);
        button.setBackground(Color.BLACK);
        return button;
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
