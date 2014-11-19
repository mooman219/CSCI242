import java.util.ArrayList;

/**
 * This class is designed to solve any puzzle implementing the puzzle interface.
 *
 * @author Joseph Cumbo (mooman219)
 */
public class Solver {

    /**
     * Solves the given puzzle using a naive BFS.
     *
     * @param puzzle the puzzle to solve.
     * @return a list of steps taken to reach the solution. Null if no solution
     * is found.
     */
    public ArrayList<Integer> solve(Puzzle puzzle) {
        ArrayList<ArrayList<Integer>> queue = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> current = new ArrayList<Integer>();
        current.add(puzzle.getStart());
        queue.add(current);
        while (!queue.isEmpty()) {
            current = queue.remove(0);
            for (Integer neighbor : puzzle.getNeighbors(current.get(current.size() - 1))) {
                ArrayList<Integer> next = new ArrayList<Integer>(current);
                next.add(neighbor);
                if (neighbor == puzzle.getGoal()) { // It'll autobox the Integer
                    return next;
                } else {
                    queue.add(next);
                }
            }
        }
        // No solution
        return null;
    }
}
