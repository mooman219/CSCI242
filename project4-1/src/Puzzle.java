
import java.util.ArrayList;

/**
 * An interface to a Puzzle. It contains the routines necessary for accessing
 * the start and goal configs, as well as generating new neighboring configs.
 *
 * @author Joseph Cumbo (jwc6999)
 */
public interface Puzzle {

    /**
     * Gets the neighbors of this puzzle.
     *
     * @return the neighbors of this puzzle.
     */
    public ArrayList<? extends Puzzle> getNeighbors();

    /**
     * Checks if this puzzle is the goal puzzle.
     *
     * @return true if this puzzle is the goal puzzle, false otherwise
     */
    public boolean isGoal();
}
