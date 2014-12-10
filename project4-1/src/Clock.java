
import java.util.ArrayList;

/**
 * This class is a puzzle where it is given a total amount of hours and must
 * find the shortest path from a starting number to a goal number.
 *
 * @author Joseph Cumbo (jwc6999)
 */
public class Clock implements Puzzle {

    public static final String[] testingArgs = "15 2 14".split(" ");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //args = Clock.testingArgs;
        if (args.length < 3) {
            System.out.println("Usage: java Clock hours start goal");
            return;
        }
        int hours, start, goal;
        try {
            hours = Integer.parseInt(args[0]);
            start = Integer.parseInt(args[1]);
            goal = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Usage: java Clock hours start goal");
            return;
        }
        Solver solver = new Solver();
        Clock puzzle = new Clock(hours, start, goal);
        ArrayList<Clock> solution = solver.solve(puzzle);
        if (solution == null) {
            System.out.println("No solution.");
        } else {
            for (int i = 0; i < solution.size(); i++) {
                System.out.println("Step " + i + ": " + solution.get(i));
            }
        }
    }

    private final int total;
    private final int current;
    private final int goal;

    /**
     * Initializes a new clock object.
     *
     * @param total the total hours in the clock.
     * @param current the current hour on the clock.
     * @param goal the goal hour to reach on the clock.
     */
    public Clock(int total, int current, int goal) {
        this.total = total;
        this.current = current;
        this.goal = goal;
    }

    /**
     * Initializes a new clock object based on a given clock.
     *
     * @param puzzle the clock to copy the goal and total from
     * @param current the current time
     */
    public Clock(Clock puzzle, int current) {
        this.total = puzzle.total;
        this.current = current;
        this.goal = puzzle.goal;
    }

    /**
     * Gets the neighbors of this puzzle.
     *
     * @return the neighbors of this puzzle.
     */
    @Override
    public ArrayList<Clock> getNeighbors() {
        ArrayList<Clock> neighbors = new ArrayList<Clock>();
        if (current < 1 || current > total) {
            return neighbors;
        }
        neighbors.add(new Clock(this, current == total ? 1 : current + 1));
        neighbors.add(new Clock(this, current == 1 ? total : current - 1));
        return neighbors;
    }

    /**
     * Checks if this puzzle is the goal puzzle.
     *
     * @return true if this puzzle is the goal puzzle, false otherwise
     */
    @Override
    public boolean isGoal() {
        return current == goal;
    }

    /**
     * Generates a string of the current time.
     *
     * @return a string of the current time
     */
    @Override
    public String toString() {
        return current + "";
    }

    /**
     * Generates a hash code.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.total;
        hash = 17 * hash + this.current;
        hash = 17 * hash + this.goal;
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
        final Clock other = (Clock) obj;
        if (this.total != other.total) {
            return false;
        }
        if (this.goal != other.goal) {
            return false;
        }
        return true;
    }
}
