
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is a puzzle which takes a desired amount and a number of jugs and
 * returns the shortest number of steps it takes to get from the initial state
 * (all jugs empty) to the first jug containing the desired amount.
 *
 * @author Joseph Cumbo (jwc6999)
 */
public class Water implements Puzzle<Water.JugState> {

    /**
     * Main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Water amount jug1 jug2 ...");
            return;
        }
        int desiredAmount;
        int[] jugs = new int[args.length - 1];
        try {
            desiredAmount = Integer.parseInt(args[0]);
            for (int i = 1; i < args.length; i++) {
                jugs[i - 1] = Integer.parseInt(args[i]);
            }
        } catch (NumberFormatException e) {
            System.out.println("Usage: java Water amount jug1 jug2 ...");
            return;
        }

        Water water = new Water(desiredAmount, jugs);
        Solver solver = new Solver();
        ArrayList<JugState> result = solver.solve(water);
        if (result != null) {
            for (int i = 0; i < result.size(); i++) {
                System.out.println("Step " + i + ": " + result.get(i));
            }
        } else {
            System.out.println("No Solution");
        }
    }

    private final int[] maxAmounts;
    private final JugState start;
    private final JugState goal;

    /**
     * Initializes a new water object.
     *
     * @param desiredAmount the target amount of the first jug
     * @param maxAmounts the number of jugs identified by their max capacity
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
                            localJugs[x] += howMuchCanIPour;
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

    /**
     * This class represents the state of a number of jugs.
     */
    public static class JugState {

        private final boolean isGoal;
        private final int[] jugs;

        /**
         * Initializes a new jug state.
         *
         * @param jugs the current amounts for a number of jugs
         */
        public JugState(int... jugs) {
            this(false, jugs);
        }

        /**
         * Initializes a new jug state.
         *
         * @param isGoal a flag to mark this jug state as a goal, meaning only
         * the first jug's water level is compared when using equals()
         * @param jugs the current amounts for a number of jugs
         */
        private JugState(boolean isGoal, int... jugs) {
            this.isGoal = isGoal;
            this.jugs = jugs;
        }

        /**
         * Copies the current jug fill levels.
         *
         * @return a copy of the current jug fill levels
         */
        public int[] copyJugs() {
            int[] jugsCopy = new int[this.jugs.length];
            System.arraycopy(this.jugs, 0, jugsCopy, 0, this.jugs.length);
            return jugsCopy;
        }

        /**
         * Compares this JugState to another JugState.
         *
         * @param obj
         * @return true if both object are equivalent or, the isGoal flag is set
         * and the first jug in both JugStates are equal.
         */
        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final JugState other = (JugState) obj;
            if ((isGoal || other.isGoal) && this.jugs[0] == other.jugs[0]) {
                return true;
            } else if (!Arrays.equals(this.jugs, other.jugs)) {
                return false;
            }
            return true;
        }

        /**
         * Generates a string of the current jug fill levels.
         *
         * @return a string of the current jug fill levels
         */
        @Override
        public String toString() {
            StringBuilder ret = new StringBuilder();
            if (jugs.length > 0) {
                for (int jug : jugs) {
                    ret.append(jug).append(' ');
                }
                ret.deleteCharAt(ret.length() - 1);
            }
            return ret.toString();
        }

        /**
         * Generates a hash code.
         *
         * @return a hash code
         */
        @Override
        public int hashCode() {
            int hash = 5;
            hash = 83 * hash + (this.isGoal ? 1 : 0);
            hash = 83 * hash + Arrays.hashCode(this.jugs);
            return hash;
        }
    }
}
