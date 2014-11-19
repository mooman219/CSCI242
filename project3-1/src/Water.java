import java.util.ArrayList;

/**
 * This class is a puzzle which takes a desired amount and a number of jugs and
 * returns the shortest number of steps it takes to get from the initial state
 * (all jugs empty) to the first jug containing the desired amount.
 *
 * @author Joseph Cumbo (mooman219)
 */
public class Water implements Puzzle<Integer> {

    /**
     * Main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    }

    /**
     * Initializes a new water object.
     */
    public Water() {
    }

    /**
     * Get the starting config for this puzzle.
     *
     * @return the starting config.
     */
    @Override
    public Integer getStart() {
    }

    /**
     * For an incoming config, generate and return all direct neighbors to this
     * config.
     *
     * @param config the incoming config.
     * @return the collection of neighbor configs.
     */
    @Override
    public ArrayList<Integer> getNeighbors(Integer config) {
        ArrayList<Integer> neighbors = new ArrayList<Integer>();
        return neighbors;
    }

    /**
     * Get the goal config for this puzzle.
     *
     * @return the goal config.
     */
    @Override
    public Integer getGoal() {
    }

}
