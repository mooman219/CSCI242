import java.util.ArrayList;

/**
 *
 * @author Joseph Cumbo (mooman219)
 */
public class Clock implements Puzzle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }

    private final int total;
    private final int start;
    private final int goal;

    public Clock(int total, int start, int goal) {
        this.total = total;
        this.start = start;
        this.goal = goal;
    }

    /**
     * Get the starting config for this puzzle.
     *
     * @return the starting config.
     */
    @Override
    public int getStart() {
        return start;
    }

    /**
     * For an incoming config, generate and return all direct neighbors to this
     * config.
     *
     * @param config the incoming config.
     * @return the collection of neighbor configs.
     */
    @Override
    public ArrayList<Integer> getNeighbors(int config) {
        ArrayList<Integer> neighbors = new ArrayList<Integer>(2);
        if (config < 1 || config > total) {
            return neighbors;
        }
        neighbors.add(config == total ? 1 : config + 1);
        neighbors.add(config == 1 ? total : config - 1);
        return neighbors;
    }

    /**
     * Get the goal config for this puzzle.
     *
     * @return the goal config.
     */
    @Override
    public int getGoal() {
        return goal;
    }

}
