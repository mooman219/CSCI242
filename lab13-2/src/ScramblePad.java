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
        this.window.setPreferredSize(new Dimension(200, 300));

        JPanel area = new JPanel();
        area.setBackground(Color.BLACK);
        area.setLayout(new BorderLayout());

        // "LOCKED" lable
        JLabel label = new JLabel("LOCKED", SwingConstants.CENTER);
        label.setForeground(Color.RED);
        area.add(label, BorderLayout.PAGE_START);

        // Number pad input
        JPanel input = new JPanel(new GridLayout(4, 3));
        input.add(createButton("1"));
        input.add(createButton("2"));
        input.add(createButton("3"));
        input.add(createButton("4"));
        input.add(createButton("5"));
        input.add(createButton("6"));
        input.add(createButton("7"));
        input.add(createButton("8"));
        input.add(createButton("9"));
        input.add(createButton(""));
        input.add(createButton("0"));
        input.add(createButton(""));
        area.add(input, BorderLayout.CENTER);

        // Bottom Button
        JPanel buttons = new JPanel();
        buttons.add(new JButton("Start"));
        buttons.add(new JButton("Okay"));
        buttons.setBackground(Color.BLACK);
        area.add(buttons, BorderLayout.PAGE_END);

        this.window.add(area);
        this.window.pack();
        this.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Creates a button with yellow lettering and a black background. If the
     * text is empty, then the button is disabled.
     *
     * @param text the button text
     * @return a new button
     */
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.YELLOW);
        button.setBackground(Color.BLACK);
        if (text.length() == 0) {
            button.setEnabled(false);
        }
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
