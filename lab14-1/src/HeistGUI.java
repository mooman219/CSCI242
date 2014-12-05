
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * GUI for the Heist Game.
 *
 * @author Joseph Cumbo (mooman219)
 */
public class HeistGUI {

    public static void main(String[] args) {
        HeistModel model;
        try {
            model = new HeistModel("heist1.txt");
        } catch (FileNotFoundException e) {
            System.out.println("The file '" + args[0] + " was not found.");
            return;
        }
        new HeistGUI(model).show();
    }

    private final JFrame window;
    private final HeistModel model;
    private final JLabel statusBar;

    /**
     * Initializes a new HeistGUI object. Does not display anything to the user.
     */
    public HeistGUI(HeistModel model) {
        window = new JFrame("Heist Game");
        this.model = model;

        JPanel area = new JPanel();
        area.setLayout(new BorderLayout());

        // Status bar
        statusBar = new JLabel("Moves: 0");
        statusBar.setBackground(Color.white);
        statusBar.setOpaque(true);
        area.add(statusBar, BorderLayout.PAGE_START);

        // Cells
        JPanel input = new JPanel(new GridLayout(model.getDim(), model.getDim()));
        for (int i = 0; i < (model.getDim() * model.getDim()); i++) {
            final int cellNumber = i;
            JButton button = new JButton();
            button.setBackground(Color.WHITE);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Pressed: " + cellNumber);
                }
            });
            input.add(button);
        }
        area.add(input, BorderLayout.CENTER);

        // Options
        JPanel buttons = new JPanel(new GridLayout(1, model.getDim(), 5, 5));
        JLabel enterExit = new JLabel("Enter / Exit");
        enterExit.setBackground(Color.white);
        enterExit.setHorizontalAlignment(SwingConstants.CENTER);
        enterExit.setOpaque(true);
        buttons.add(enterExit);
        buttons.add(new JLabel(""));
        buttons.add(new JButton("EMP"));
        buttons.add(new JButton("Reset"));
        area.add(buttons, BorderLayout.PAGE_END);

        window.add(area);
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
