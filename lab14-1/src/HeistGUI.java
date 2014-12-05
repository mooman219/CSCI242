
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * GUI for the Heist Game.
 *
 * @author Joseph Cumbo (mooman219)
 */
public class HeistGUI {

    private final JFrame window;

    /**
     * Initializes a new HeistGUI object. Does not display anything to the user.
     */
    public HeistGUI() {
        this.window = new JFrame("Heist Game");

        this.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
