
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;
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

    /**
     * Main method.
     *
     * @param args the command line arguments
     */
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

    /**
     * Initializes a new HeistGUI object. Does not display anything to the user.
     */
    public HeistGUI(HeistModel model) {
        window = new JFrame("Heist Game");
        window.setPreferredSize(new Dimension(450, 400));

        JPanel area = new JPanel();
        area.setLayout(new BorderLayout());

        // Status bar
        JLabel statusBar_label = new JLabel("Moves: 0");
        statusBar_label.setBackground(Color.white);
        statusBar_label.setOpaque(true);
        model.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                HeistModel model = (HeistModel) o;
                switch (model.getGameStatus()) {
                    case 0:
                        statusBar_label.setText("Moves: " + model.getMoveCount()
                                + " GAME OVER - ALARM TRIGGERED.");
                        break;
                    case 1:
                        statusBar_label.setText("Moves: " + model.getMoveCount());
                        break;
                    default:
                        statusBar_label.setText("Moves: " + model.getMoveCount()
                                + " A WINNER IS YOU - JEWELS STOLEN.");
                        break;
                }
            }
        });
        area.add(statusBar_label, BorderLayout.PAGE_START);

        // Cells
        JPanel input = new JPanel(new GridLayout(model.getDim(), model.getDim()));
        for (int i = 0; i < (model.getDim() * model.getDim()); i++) {
            final int cellNumber = i;
            JButton cell_button = new JButton();
            cell_button.setBackground(Color.WHITE);
            cell_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    model.selectCell(cellNumber);
                }
            });
            model.addObserver(new Observer() {
                @Override
                public void update(Observable o, Object arg) {
                    HeistModel model = (HeistModel) o;
                    if (model.getAlarms().get(cellNumber)) {
                        cell_button.setBackground(Color.BLUE);
                    } else {
                        cell_button.setBackground(Color.WHITE);
                    }
                }
            });
            input.add(cell_button);
        }
        area.add(input, BorderLayout.CENTER);

        // Options
        JPanel buttons = new JPanel(new GridLayout(1, model.getDim(), 5, 5));
        JLabel enterExit_label = new JLabel("Enter / Exit");
        enterExit_label.setBackground(Color.white);
        enterExit_label.setHorizontalAlignment(SwingConstants.CENTER);
        enterExit_label.setOpaque(true);
        buttons.add(enterExit_label);
        buttons.add(new JLabel(""));
        JButton EMP_button = new JButton("EMP");
        EMP_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.disableAlarm();
            }
        });
        buttons.add(EMP_button);
        JButton reset_button = new JButton("Reset");
        reset_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.reset();
            }
        });
        buttons.add(reset_button);
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
