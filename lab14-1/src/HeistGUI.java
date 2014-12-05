
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;

/**
 * GUI for the Heist Game.
 *
 * @author Joseph Cumbo (jwc6999)
 */
public class HeistGUI {

    /**
     * Main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Heist <config-file>");
            return;
        }
        HeistModel model;
        try {
            model = new HeistModel(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("The file '" + args[0] + " was not found.");
            return;
        }
        HeistGUI gui = new HeistGUI(model);
        gui.show();
    }

    private final JFrame window;
    private final HeistModel model;
    private Timer timer;

    /**
     * Initializes a new HeistGUI object. Does not display anything to the user.
     */
    public HeistGUI(HeistModel model) {
        window = new JFrame("Heist Game");
        window.setPreferredSize(new Dimension(450, 400));
        this.model = model;

        JPanel area = new JPanel();
        area.setLayout(new BorderLayout());

        area.add(generateStatusBar(model), BorderLayout.PAGE_START);

        // Cells
        JPanel input = new JPanel(new GridLayout(model.getDim(), model.getDim()));
        List<JButton> cells = generateCells(model);
        for (JButton cell : cells) {
            input.add(cell);
        }
        area.add(input, BorderLayout.CENTER);

        // Options
        JPanel buttons = new JPanel(new GridLayout(1, model.getDim(), 5, 5));
        JLabel enterExit_label = new JLabel("Enter / Exit");
        enterExit_label.setBackground(Color.white);
        enterExit_label.setHorizontalAlignment(SwingConstants.CENTER);
        enterExit_label.setOpaque(true);
        buttons.add(enterExit_label);
        buttons.add(new JLabel("")); // Filler
        buttons.add(generateEMPButton(model));
        buttons.add(generateResetButton(model));
        area.add(buttons, BorderLayout.PAGE_END);

        /**
         * Now that everything has been generated and linked up, start the game
         * loop timer.
         */
        startTimer();
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

    /**
     * Starts the game's clock cycle if it isn't running. If the timer is
     * running, the time is reset.
     */
    public void startTimer() {
        if (timer != null) {
            if (timer.isRunning()) {
                timer.restart();
            } else {
                timer.start();
            }
        } else {
            timer = new Timer(model.getRefreshRate(), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    model.updateAlarmPattern();
                }
            });
            timer.start();
        }
    }

    /**
     * Starts the game's clock cycle if it is running.
     */
    public void stopTimer() {
        if (timer != null) {
            if (timer.isRunning()) {
                timer.stop();
            }
        }
    }

    /**
     * Generates a new status bar attached to the given HeistModel.
     *
     * @param model the HeistModel that the label will be attached to.
     * @return the generated status bar
     */
    private JLabel generateStatusBar(HeistModel model) {
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
        return statusBar_label;
    }

    /**
     * Generates a new EMP button attached to the given HeistModel.
     *
     * @param model the HeistModel that the label will be attached to.
     * @return the generated EMP button
     */
    private JButton generateEMPButton(HeistModel model) {
        JButton EMP_button = new JButton("EMP");
        EMP_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.disableAlarm();
            }
        });
        return EMP_button;
    }

    /**
     * Generates a new reset button attached to the given HeistModel.
     *
     * @param model the HeistModel that the label will be attached to.
     * @return the generated reset button
     */
    private JButton generateResetButton(HeistModel model) {
        JButton reset_button = new JButton("Reset");
        reset_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.reset();
                startTimer();
            }
        });
        return reset_button;
    }

    /**
     * Generates a list of cells attached to the given HeistModel.
     *
     * @param model the HeistModel that the label will be attached to.
     * @return the generated list of cells
     */
    private List<JButton> generateCells(HeistModel model) {
        ArrayList<JButton> cells = new ArrayList<JButton>();
        final Icon thief = new ImageIcon("Thief.JPG");
        final Icon jewels = new ImageIcon("Jewels.JPG");
        final Icon escape = new ImageIcon("Escape.JPG");
        for (int i = 0; i < (model.getDim() * model.getDim()); i++) {
            final int cellNumber = i;
            JButton cell_button = new JButton();
            cell_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    model.selectCell(cellNumber);
                }
            });
            Observer observer = new Observer() {
                @Override
                public void update(Observable o, Object arg) {
                    HeistModel model = (HeistModel) o;
                    if (model.getThiefLocation() == cellNumber) {
                        cell_button.setIcon(model.getAreJewelsStolen()
                                ? escape : thief);
                    } else if (!model.getAreJewelsStolen() && model.getJewelsLocation() == cellNumber) {
                        cell_button.setIcon(jewels);
                    } else if (cell_button.getIcon() != null) {
                        cell_button.setIcon(null);
                    }
                    if (model.getAlarms().get(cellNumber)) {
                        cell_button.setBackground(Color.BLUE);
                    } else {
                        cell_button.setBackground(Color.WHITE);
                    }
                }
            };
            observer.update(model, null);
            model.addObserver(observer);
            cells.add(cell_button);
        }
        return cells;
    }
}
