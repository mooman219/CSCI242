
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is a puzzle which takes a desired amount and a number of jugs and
 * returns the shortest number of steps it takes to get from the initial state
 * (all jugs empty) to the first jug containing the desired amount.
 *
 * @author Joseph Cumbo (jwc6999)
 */
public class Water implements Puzzle {

    public static final String[] testingArgs = "17 20 11 4".split(" ");

    /**
     * Main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //args = Water.testingArgs;
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
        ArrayList<Water> result = solver.solve(water);
        if (result != null) {
            for (int i = 0; i < result.size(); i++) {
                System.out.println("Step " + i + ": " + result.get(i));
            }
        } else {
            System.out.println("No solution.");
        }
    }

    private final int[] maxAmounts;
    private final int[] jugs;
    private int goal;

    /**
     * Initializes a new water object.
     *
     * @param desiredAmount the target amount of the first jug
     * @param maxAmounts the number of jugs identified by their max capacity
     */
    public Water(int desiredAmount, int... maxAmounts) {
        this.maxAmounts = maxAmounts;
        this.jugs = new int[maxAmounts.length];
        this.goal = desiredAmount;
    }

    /**
     * Initializes a new water object based on the constants of a given water
     * object.
     *
     * @param puzzle the water puzzle to take the constants from
     * @param jugs the new jug fill levels to be used
     */
    public Water(Water puzzle, int[] jugs) {
        this.maxAmounts = puzzle.maxAmounts;
        this.jugs = jugs;
        this.goal = puzzle.goal;
    }

    /**
     * Gets the neighbors of this puzzle.
     *
     * @return the neighbors of this puzzle.
     */
    @Override
    public ArrayList<Water> getNeighbors() {
        ArrayList<Water> neighbors = new ArrayList<Water>();
        // Local copy of the jugs
        int[] localJugs;
        for (int i = 0; i < jugs.length; i++) {
            // Note: Jugs referenced using 'i' will be called the "current jug"

            // If the current jug isn't full
            if (jugs[i] != maxAmounts[i]) {
                // Get a copy of the jugs in config
                localJugs = copyJugs();
                // Then fill the current jug
                localJugs[i] = maxAmounts[i];
                // Finally add it as a neighbor
                neighbors.add(new Water(this, localJugs));
            }

            // If the current jug isn't empty
            if (jugs[i] != 0) {
                // Get a copy of the jugs in config
                localJugs = copyJugs();
                // Then empty the current jug
                localJugs[i] = 0;
                // Finally add it as a neighbor
                neighbors.add(new Water(this, localJugs));

                for (int x = 0; x < jugs.length; x++) {
                    // Note: Jugs referenced using 'x' will be called the "other jug"

                    // If the current jug isn't the other jug
                    if (i != x) {
                        // If the other jug isn't full
                        if (jugs[x] != maxAmounts[x]) {
                            /**
                             * At this point, we know the current jug isn't
                             * empty, we're working with two different jugs, and
                             * the other jug isn't full. Now we can pour the
                             * current jug into the other jug.
                             */

                            // Get a copy of the jugs in config
                            localJugs = copyJugs();
                            /**
                             * Since you can't over fill a jug, or pour more
                             * that you have, get the minimum of the two values
                             */
                            int howMuchCanIPour = Math.min(maxAmounts[x] - jugs[x], jugs[i]);
                            localJugs[i] -= howMuchCanIPour;
                            localJugs[x] += howMuchCanIPour;
                            // Finally add it as a neighbor
                            neighbors.add(new Water(this, localJugs));
                        }
                    }
                }
            }
        }
        return neighbors;
    }

    /**
     * Checks if this puzzle is the goal puzzle.
     *
     * @return true if this puzzle is the goal puzzle, false otherwise
     */
    @Override
    public boolean isGoal() {
        return jugs[0] == goal;
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
        hash = 89 * hash + Arrays.hashCode(this.maxAmounts);
        hash = 89 * hash + Arrays.hashCode(this.jugs);
        hash = 89 * hash + this.goal;
        return hash;
    }

    /**
     * Check is this and the given object are equal.
     *
     * @param obj the given object
     * @return true if this and the given object are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Water other = (Water) obj;
        if (!Arrays.equals(this.maxAmounts, other.maxAmounts)) {
            return false;
        }
        if (!Arrays.equals(this.jugs, other.jugs)) {
            return false;
        }
        if (this.goal != other.goal) {
            return false;
        }
        return true;
    }

}
