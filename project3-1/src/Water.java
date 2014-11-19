import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is a puzzle which takes a desired amount and a number of jugs and
 * returns the shortest number of steps it takes to get from the initial state
 * (all jugs empty) to the first jug containing the desired amount.
 *
 * @author Joseph Cumbo (mooman219)
 */
public class Water implements Puzzle<Water.JugState> {

    /**
     * Main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    }

    private final int[] maxAmounts;
    private final JugState start;
    private final JugState goal;

    /**
     * Initializes a new water object.
     */
    public Water(int desiredAmount, int... maxAmounts) {
        this.maxAmounts = maxAmounts;
        this.start = new JugState(new int[maxAmounts.length]);
        this.goal = new JugState(true, desiredAmount);
    }

    /**
     * Get the starting config for this puzzle.
     *
     * @return the starting config.
     */
    @Override
    public JugState getStart() {
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
    public ArrayList<JugState> getNeighbors(JugState config) {
        ArrayList<JugState> neighbors = new ArrayList<JugState>();
        return neighbors;
    }

    /**
     * Get the goal config for this puzzle.
     *
     * @return the goal config.
     */
    @Override
    public JugState getGoal() {
        return goal;
    }

    public static class JugState {

        private final boolean isGoal;
        private final int[] jugs;

        public JugState(int... jugs) {
            this(false, jugs);
        }

        private JugState(boolean isGoal, int... jugs) {
            this.isGoal = isGoal;
            this.jugs = jugs;
        }

        public int[] getJugs() {
            return jugs;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final JugState other = (JugState) obj;
            if (isGoal && this.jugs[0] == other.jugs[0]) {
                return true;
            } else if (!Arrays.equals(this.jugs, other.jugs)) {
                return false;
            }
            return true;
        }
    }

}
