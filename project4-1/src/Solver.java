import java.util.ArrayList;
import java.util.HashSet;

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
    public <T> ArrayList<T> solve(Puzzle<T> puzzle) {
        HashSet<T> visited = new HashSet<T>();
        ArrayList<ArrayList<T>> queue = new ArrayList<ArrayList<T>>();
        ArrayList<T> current = new ArrayList<T>();
        current.add(puzzle.getStart());
        queue.add(current);
        while (!queue.isEmpty()) {
            current = queue.remove(0);
            /**
             * To avoid looping, a HashSet of previously visited nodes is
             * stored.
             */
            T target = current.get(current.size() - 1);
            if (visited.contains(target)) {
                continue;
            } else {
                visited.add(target);
            }
            for (T neighbor : puzzle.getNeighbors(target)) {
                ArrayList<T> next = new ArrayList<T>(current);
                next.add(neighbor);
                if (neighbor.equals(puzzle.getGoal())) {
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
