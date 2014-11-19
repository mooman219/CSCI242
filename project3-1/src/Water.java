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
        // Local copy of the jugs
        int[] localJugs;
        for (int i = 0; i < config.jugs.length; i++) {
            // Note: Jugs referenced using 'i' will be called the "current jug"

            // If the current jug isn't full
            if (config.jugs[i] != maxAmounts[i]) {
                // Get a copy of the jugs in config
                localJugs = config.copyJugs();
                // Then fill the current jug
                localJugs[i] = maxAmounts[i];
                // Finally add it as a neighbor
                neighbors.add(new JugState(localJugs));
            }

            // If the current jug isn't empty
            if (config.jugs[i] != 0) {
                // Get a copy of the jugs in config
                localJugs = config.copyJugs();
                // Then empty the current jug
                localJugs[i] = 0;
                // Finally add it as a neighbor
                neighbors.add(new JugState(localJugs));

                for (int x = 0; x < config.jugs.length; x++) {
                    // Note: Jugs referenced using 'x' will be called the "other jug"

                    // If the current jug isn't the other jug
                    if (i != x) {
                        // If the other jug isn't full
                        if (config.jugs[x] != maxAmounts[x]) {
                            /**
                             * At this point, we know the current jug isn't
                             * empty, we're working with two different jugs, and
                             * the other jug isn't full. Now we can pour the
                             * current jug into the other jug.
                             */

                            // Get a copy of the jugs in config
                            localJugs = config.copyJugs();
                            /**
                             * Since you can't over fill a jug, or pour more
                             * that you have, get the minimum of the two values
                             */
                            int howMuchCanIPour = Math.min(maxAmounts[x] - config.jugs[x], config.jugs[i]);
                            localJugs[i] -= howMuchCanIPour;
                            localJugs[i] += howMuchCanIPour;
                            // Finally add it as a neighbor
                            neighbors.add(new JugState(localJugs));
                        }
                    }
                }
            }
        }
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

        public int[] copyJugs() {
            int[] jugsCopy = new int[this.jugs.length];
            System.arraycopy(this.jugs, 0, jugsCopy, 0, this.jugs.length);
            return jugsCopy;
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
