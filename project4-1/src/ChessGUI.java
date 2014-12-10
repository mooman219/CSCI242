
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * GUI for the chess game.
 *
 * @author Joseph Cumbo (mooman219)
 */
public class ChessGUI {

    public static String[] testingArgs = "test.txt".split(" ");

    /**
     * Main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        args = testingArgs;
        if (args.length < 1) {
            System.out.println("usage: java Chess input-file");
            return;
        }
        ChessModel model;
        try {
            model = new ChessModel(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println(args[0] + " not found.");
            return;
        }
        ChessGUI gui = new ChessGUI(model);
        gui.show();
    }

    /**
     * Map of characters to their appropriate icon.
     */
    private static HashMap<Character, ImageIcon> icons = new HashMap<Character, ImageIcon>() {
        {
            put('B', new ImageIcon("bishop.png"));
            put('K', new ImageIcon("king.png"));
            put('N', new ImageIcon("knight.png"));
            put('P', new ImageIcon("pawn.png"));
            put('R', new ImageIcon("rook.png"));
            put('Q', new ImageIcon("queen.png"));
        }
    };

    private final JFrame window;

    /**
     * Initializes a new ChessGUI linked to the given model.
     *
     * @param model the model that this ChessGUI will be linked to
     */
    public ChessGUI(ChessModel model) {
        window = new JFrame("Chess Game");
        window.setPreferredSize(new Dimension(100 * model.getLengthX(), 125 * model.getLengthY()));

        JPanel area = new JPanel();
        area.setLayout(new BorderLayout());

        // Status bar
        JPanel top = new JPanel();
        top.add(generateMoveBar(model));
        top.add(generateStatusBar(model));
        area.add(top, BorderLayout.PAGE_START);

        // Cells
        JPanel input = new JPanel(new GridLayout(model.getLengthY(), model.getLengthX()));
        List<JButton> cells = generateCells(model);
        for (JButton cell : cells) {
            input.add(cell);
        }
        area.add(input, BorderLayout.CENTER);

        // Options
        JPanel bottom = new JPanel();
        bottom.add(generateMoveButton(model), BorderLayout.PAGE_END);
        bottom.add(generateResetButton(model), BorderLayout.PAGE_END);
        area.add(bottom, BorderLayout.PAGE_END);

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
     * Generates a new status bar attached to the given ChessModel.
     *
     * @param model the ChessModel that the label will be attached to.
     * @return the generated status bar
     */
    private JLabel generateMoveBar(ChessModel model) {
        final JLabel statusBar_label = new JLabel("Moves: 0");
        statusBar_label.setBackground(Color.white);
        statusBar_label.setOpaque(true);
        model.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                ChessModel model = (ChessModel) o;
                statusBar_label.setText("Moves: " + model.getMoveCount());
            }
        });
        return statusBar_label;
    }

    /**
     * Generates a new status bar attached to the given ChessModel.
     *
     * @param model the ChessModel that the label will be attached to.
     * @return the generated status bar
     */
    private JLabel generateStatusBar(ChessModel model) {
        final JLabel statusBar_label = new JLabel("Status: In progress...");
        statusBar_label.setBackground(Color.white);
        statusBar_label.setOpaque(true);
        model.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                ChessModel model = (ChessModel) o;
                switch (model.getState()) {
                    case 0:
                        statusBar_label.setText("Status: In progress...");
                        break;
                    case 1:
                        statusBar_label.setText("Status: Solved!");
                        break;
                    default:
                        statusBar_label.setText("Status: Unsolveable.");
                        break;
                }
            }
        });
        return statusBar_label;
    }

    /**
     * Generates a new move button attached to the given ChessModel.
     *
     * @param model the ChessModel that the label will be attached to.
     * @return the generated reset button
     */
    private JButton generateMoveButton(final ChessModel model) {
        JButton reset_button = new JButton("Move");
        reset_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.move();
            }
        });
        return reset_button;
    }

    /**
     * Generates a new reset button attached to the given ChessModel.
     *
     * @param model the ChessModel that the label will be attached to.
     * @return the generated reset button
     */
    private JButton generateResetButton(final ChessModel model) {
        JButton reset_button = new JButton("Reset");
        reset_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.reset();
            }
        });
        return reset_button;
    }

    /**
     * Generates a list of cells attached to the given ChessModel.
     *
     * @param model the ChessModel that the label will be attached to.
     * @return the generated list of cells
     */
    private List<JButton> generateCells(final ChessModel model) {
        ArrayList<JButton> cells = new ArrayList<JButton>();
        for (int y = 0; y < model.getLengthY(); y++) {
            for (int x = 0; x < model.getLengthX(); x++) {
                final int cellX = x;
                final int cellY = y;
                final JButton cell_button = new JButton();
                cell_button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        model.selectCell(cellX, cellY);
                    }
                });
                Observer observer = new Observer() {
                    @Override
                    public void update(Observable o, Object arg) {
                        ChessModel model = (ChessModel) o;
                        Point point = model.getSelectedPoint();
                        if (model.getState() == 1) {
                            cell_button.setBackground(Color.GREEN);
                        } else if (model.getState() == 2) {
                            cell_button.setBackground(Color.RED);
                        } else if (point != null && point.x == cellX && point.y == cellY) {
                            cell_button.setBackground(Color.BLUE);
                        } else if (model.isMovePossible(cellX, cellY)) {
                            if (model.getPiece(cellX, cellY) != '.') {
                                cell_button.setBackground(Color.RED);
                            } else {
                                cell_button.setBackground(Color.LIGHT_GRAY);
                            }
                        } else {
                            cell_button.setBackground((cellX + cellY & 1) == 1 ? Color.WHITE : Color.DARK_GRAY);
                        }
                        cell_button.setIcon(icons.get(model.getPiece(cellX, cellY)));
                    }
                };
                observer.update(model, null);
                model.addObserver(observer);
                cells.add(cell_button);
            }
        }
        return cells;
    }

}
