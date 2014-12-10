
import java.util.ArrayList;
import java.util.HashSet;

/**
 * This class is designed to solve any puzzle implementing the puzzle interface.
 *
 * @author Joseph Cumbo (jwc6999)
 */
public class Solver {

    /**
     * Solves the given puzzle using a naive BFS.
     *
     * @param puzzle the puzzle to solve.
     * @return a list of steps taken to reach the solution. Null if no solution
     * is found.
     */
    public <T extends Puzzle> ArrayList<T> solve(T puzzle) {
        HashSet<T> visited = new HashSet<T>();
        ArrayList<ArrayList<T>> queue = new ArrayList<ArrayList<T>>();
        ArrayList<T> current = new ArrayList<T>();
        current.add(puzzle);
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
            for (T neighbor : (ArrayList<T>) target.getNeighbors()) {
                ArrayList<T> next = new ArrayList<T>(current);
                next.add(neighbor);
                if (neighbor.isGoal()) {
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
