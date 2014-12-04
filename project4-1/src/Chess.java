
import java.util.ArrayList;

/**
 * This class is a puzzle which takes an input file representing a solitaire
 * chess board and produces an optimal solution for solving it.
 *
 * @author Joseph Cumbo (mooman219)
 */
public class Chess implements Puzzle<Chess.BoardState> {

    /**
     * Main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }

    private final BoardState start;
    private final BoardState goal;

    /**
     * Initializes a chess object.
     *
     */
    public Chess() {
    }

    /**
     * Get the starting config for this puzzle.
     *
     * @return the starting config.
     */
    @Override
    public BoardState getStart() {
    }

    /**
     * For an incoming config, generate and return all direct neighbors to this
     * config.
     *
     * @param config the incoming config.
     * @return the collection of neighbor configs.
     */
    @Override
    public ArrayList<BoardState> getNeighbors(BoardState config) {
    }

    /**
     * Get the goal config for this puzzle.
     *
     * @return the goal config.
     */
    @Override
    public BoardState getGoal() {
    }

    /**
     * The state of the board.
     */
    public static class BoardState {
    }

    /**
     * Wrapper for a possible move.
     */
    public static class Move {

        private final int x;
        private final int y;

        /**
         * Initializes a new move object.
         *
         * @param x the x position of the move
         * @param y the y position of the move
         */
        public Move(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Gets the x value for the move.
         *
         * @return the x value for the move
         */
        public int getX() {
            return x;
        }

        /**
         * * Gets the y value for the move.
         *
         * @return the y value for the move
         */
        public int getY() {
            return y;
        }
    }

    /**
     * Generates moves.
     */
    public static interface MoveCalculator {

        /**
         * Generates a list of valid moves that can be made from the given
         * position.
         *
         * @param position the current position of the piece
         * @return a list of valid moves that can be made
         */
        public ArrayList<Move> calculate(Move position);
    }
}
