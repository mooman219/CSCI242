import java.util.ArrayList;

/**
 * An interface to a Puzzle. It contains the routines necessary for accessing
 * the start and goal configs, as well as generating new neighboring configs.
 *
 * @author Joseph Cumbo (mooman219)
 */
public interface Puzzle<E> {

    /**
     * Get the starting config for this puzzle.
     *
     * @return the starting config.
     */
    public E getStart();

    /**
     * For an incoming config, generate and return all direct neighbors to this
     * config.
     *
     * @param config the incoming config.
     * @return the collection of neighbor configs.
     */
    public ArrayList<E> getNeighbors(E config);

    /**
     * Get the goal config for this puzzle.
     *
     * @return the goal config.
     */
    public E getGoal();
}
