
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    // Initialization for moveStrategies
    public static final Map<Character, MoveCalculator> moveStrategies = new HashMap<Character, MoveCalculator>() {
        {
            // Movements for an empty spot
            put('.', new MoveCalculator() {
                @Override
                public List<Point> calculate(int curX, int curY, int lengthX, int lengthY) {
                    return new ArrayList<Point>();
                }
            });
            // Movements for bishup
            put('B', new MoveCalculator() {
                @Override
                public List<Point> calculate(int curX, int curY, int lengthX, int lengthY) {
                    ArrayList<Point> moves = new ArrayList<Point>();
                    for (int i = 1; i < Math.max(lengthX, lengthY); i++) {
                        moves.add(new Point(curX - i, curY - i));
                        moves.add(new Point(curX + i, curY + i));
                        moves.add(new Point(curX - i, curY + i));
                        moves.add(new Point(curX + i, curY - i));
                    }
                    Chess.removeInvalidMoves(moves, lengthX, lengthY);
                    return moves;
                }
            });
            // Movements for king
            put('K', new MoveCalculator() {
                @Override
                public List<Point> calculate(int curX, int curY, int lengthX, int lengthY) {
                    ArrayList<Point> moves = new ArrayList<Point>();
                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            if (!(x == 0 && y == 0)) {
                                moves.add(new Point(curX + x, curY + y));
                            }
                        }
                    }
                    Chess.removeInvalidMoves(moves, lengthX, lengthY);
                    return moves;
                }
            });
            // Movements for knight
            put('N', new MoveCalculator() {
                @Override
                public List<Point> calculate(int curX, int curY, int lengthX, int lengthY) {
                    ArrayList<Point> moves = new ArrayList<Point>();
                    for (int x = -1; x <= 1; x += 2) {
                        for (int y = -1; y < 1; y += 2) {
                            moves.add(new Point(curX + x, curY + y * 2));
                            moves.add(new Point(curX + x * 2, curY + y));
                        }
                    }
                    Chess.removeInvalidMoves(moves, lengthX, lengthY);
                    return moves;
                }
            });
            // Movements for pawn
            put('P', new MoveCalculator() {
                @Override
                public List<Point> calculate(int curX, int curY, int lengthX, int lengthY) {
                    ArrayList<Point> moves = new ArrayList<Point>();
                    for (int x = -1; x <= 1; x += 2) {
                        for (int y = -1; y <= 1; y += 2) {
                            moves.add(new Point(curX + x, curY + y));
                        }
                    }
                    Chess.removeInvalidMoves(moves, lengthX, lengthY);
                    return moves;
                }
            });
            // Movements for rook
            put('R', new MoveCalculator() {
                @Override
                public List<Point> calculate(int curX, int curY, int lengthX, int lengthY) {
                    ArrayList<Point> moves = new ArrayList<Point>();
                    for (int i = 0; i < Math.max(lengthX, lengthY); i++) {
                        moves.add(new Point(i, curY));
                        moves.add(new Point(curX, i));
                    }
                    Chess.removeInvalidMoves(moves, lengthX, lengthY);
                    return moves;
                }
            });
            // Movements for Queen
            put('Q', new MoveCalculator() {
                @Override
                public List<Point> calculate(int curX, int curY, int lengthX, int lengthY) {
                    ArrayList<Point> moves = new ArrayList<Point>();
                    for (int i = 0; i < Math.max(lengthX, lengthY); i++) {
                        moves.add(new Point(i, curY));
                        moves.add(new Point(curX, i));
                        if (i > 1) {
                            moves.add(new Point(curX - i, curY - i));
                            moves.add(new Point(curX + i, curY + i));
                            moves.add(new Point(curX - i, curY + i));
                            moves.add(new Point(curX + i, curY - i));
                        }
                    }
                    Chess.removeInvalidMoves(moves, lengthX, lengthY);
                    return moves;
                }
            });
        }
    };

    /**
     * Removes invalid moves from the given list.
     *
     * @param moves the list of moves
     * @param lengthX the horizontal length of the board
     * @param lengthY the vertical length of the board
     */
    public static void removeInvalidMoves(List<Point> moves, int lengthX, int lengthY) {
        for (Iterator<Point> iterator = moves.iterator(); iterator.hasNext();) {
            Point point = iterator.next();
            if (point.getX() >= 0 && point.getY() >= 0
                    && point.getX() < lengthX && point.getY() < lengthY) {
                iterator.remove();
            }
        }
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

        private final boolean isGoal;
        private final int piecesRemaining;
        private final char[][] boardState;

        public BoardState(char[][] boardState) {
            this(false, boardState);
        }

        public BoardState(boolean isGoal, char[][] boardState) {
            int pieces = 0;
            for (int x = 0; x < boardState.length; x++) {
                for (int y = 0; y < boardState[x].length; y++) {
                    if (boardState[x][y] != '.') {
                        pieces++;
                    }
                }
            }
            this.isGoal = isGoal;
            this.piecesRemaining = pieces;
            this.boardState = boardState;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 29 * hash + this.piecesRemaining;
            hash = 29 * hash + Arrays.deepHashCode(this.boardState);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final BoardState other = (BoardState) obj;
            if ((isGoal || other.isGoal) && (piecesRemaining == 1 || other.piecesRemaining == 1)) {

            }
            if (this.piecesRemaining != other.piecesRemaining) {
                return false;
            }
            if (!Arrays.deepEquals(this.boardState, other.boardState)) {
                return false;
            }
            return true;
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
        public List<Point> calculate(int curX, int curY, int lengthX, int lengthY);
    }
}
