
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

    /**
     * This is a map of characters to move calculators. The user enters a
     * character that represents the piece and is then given a function to get
     * the possible moves for that piece.
     */
    public static final Map<Character, MoveCalculator> moveStrategies = new HashMap<Character, MoveCalculator>() {
        {
            // Movements for bishup
            put('B', new MoveCalculator() {
                @Override
                public List<Point> calculate(int curX, int curY, int hor, int ver) {
                    ArrayList<Point> moves = new ArrayList<Point>();
                    for (int i = 1; i < Math.max(hor, ver); i++) {
                        moves.add(new Point(curX - i, curY - i));
                        moves.add(new Point(curX + i, curY + i));
                        moves.add(new Point(curX - i, curY + i));
                        moves.add(new Point(curX + i, curY - i));
                    }
                    Chess.removeInvalidMoves(moves, hor, ver);
                    return moves;
                }
            });
            // Movements for king
            put('K', new MoveCalculator() {
                @Override
                public List<Point> calculate(int curX, int curY, int hor, int ver) {
                    ArrayList<Point> moves = new ArrayList<Point>();
                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            if (!(x == 0 && y == 0)) {
                                moves.add(new Point(curX + x, curY + y));
                            }
                        }
                    }
                    Chess.removeInvalidMoves(moves, hor, ver);
                    return moves;
                }
            });
            // Movements for knight
            put('N', new MoveCalculator() {
                @Override
                public List<Point> calculate(int curX, int curY, int hor, int ver) {
                    ArrayList<Point> moves = new ArrayList<Point>();
                    for (int x = -1; x <= 1; x += 2) {
                        for (int y = -1; y < 1; y += 2) {
                            moves.add(new Point(curX + x, curY + y * 2));
                            moves.add(new Point(curX + x * 2, curY + y));
                        }
                    }
                    Chess.removeInvalidMoves(moves, hor, ver);
                    return moves;
                }
            });
            // Movements for pawn
            put('P', new MoveCalculator() {
                @Override
                public List<Point> calculate(int curX, int curY, int hor, int ver) {
                    ArrayList<Point> moves = new ArrayList<Point>();
                    for (int x = -1; x <= 1; x += 2) {
                        for (int y = -1; y <= 1; y += 2) {
                            moves.add(new Point(curX + x, curY + y));
                        }
                    }
                    Chess.removeInvalidMoves(moves, hor, ver);
                    return moves;
                }
            });
            // Movements for rook
            put('R', new MoveCalculator() {
                @Override
                public List<Point> calculate(int curX, int curY, int hor, int ver) {
                    ArrayList<Point> moves = new ArrayList<Point>();
                    for (int i = 0; i < Math.max(hor, ver); i++) {
                        moves.add(new Point(i, curY));
                        moves.add(new Point(curX, i));
                    }
                    Chess.removeInvalidMoves(moves, hor, ver);
                    return moves;
                }
            });
            // Movements for Queen
            put('Q', new MoveCalculator() {
                @Override
                public List<Point> calculate(int curX, int curY, int hor, int ver) {
                    ArrayList<Point> moves = new ArrayList<Point>();
                    for (int i = 0; i < Math.max(hor, ver); i++) {
                        moves.add(new Point(i, curY));
                        moves.add(new Point(curX, i));
                        if (i > 1) {
                            moves.add(new Point(curX - i, curY - i));
                            moves.add(new Point(curX + i, curY + i));
                            moves.add(new Point(curX - i, curY + i));
                            moves.add(new Point(curX + i, curY - i));
                        }
                    }
                    Chess.removeInvalidMoves(moves, hor, ver);
                    return moves;
                }
            });
        }
    };

    /**
     * Removes invalid moves from the given list. And invalid move is one that
     * goes outside the bounds of the board.
     *
     * @param moves the list of moves
     * @param lengthX the horizontal length of the board
     * @param lengthY the vertical length of the board
     */
    public static void removeInvalidMoves(List<Point> moves, int lengthX, int lengthY) {
        for (Iterator<Point> iterator = moves.iterator(); iterator.hasNext();) {
            Point point = iterator.next();
            if (point.x >= 0 && point.y >= 0 && point.x < lengthX && point.y < lengthY) {
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
    public Chess(char[][] initialBoard) {
        start = new BoardState(initialBoard);
        goal = new BoardState(1);
    }

    /**
     * Get the starting config for this puzzle.
     *
     * @return the starting config.
     */
    @Override
    public BoardState getStart() {
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
    public ArrayList<BoardState> getNeighbors(BoardState config) {
        ArrayList<BoardState> neighbors = new ArrayList<BoardState>();
        char[][] board = config.board;
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                /**
                 * If the piece is a valid chess piece.
                 */
                if (board[x][y] != '.' && moveStrategies.containsKey(board[x][y])) {
                    /**
                     * Calculate all the points the can be reached by the piece.
                     */
                    MoveCalculator moveCalculator = moveStrategies.get(board[x][y]);
                    List<Point> moves = moveCalculator.calculate(x, y, board[x].length, board.length);
                    /**
                     * If there's another piece in one of the reachable points,
                     * take it.
                     */
                    for (Point point : moves) {
                        if (board[point.x][point.y] != '.') {
                            char[][] localBoard = config.copyBoard();
                            localBoard[point.x][point.y] = board[x][y];
                            localBoard[x][y] = '.';
                            neighbors.add(new BoardState(localBoard));
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
    public BoardState getGoal() {
        return goal;
    }

    /**
     * The state of the board.
     */
    public static class BoardState {

        private final boolean isGoal;
        private final int pieces;
        private final char[][] board;

        /**
         * Initializes a new BoardState based off of the given board.
         *
         * @param board the board to back this board state
         */
        public BoardState(char[][] board) {
            /**
             * Calculate the pieces on the board.
             */
            int pieces = 0;
            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board[x].length; y++) {
                    if (board[x][y] != '.') {
                        pieces++;
                    }
                }
            }

            this.isGoal = false;
            this.board = board;
            this.pieces = pieces;
        }

        /**
         * Initializes a new BoardState to be used as a goal state. This
         * BoardState only checks that the number of pieces remaining are equal
         * in the equals method.
         *
         * @param pieces the number of pieces required for another board to
         * match this board
         */
        private BoardState(int pieces) {
            this.isGoal = true;
            this.board = null;
            this.pieces = pieces;
        }

        /**
         * Copies the current board.
         *
         * @return a copy of the current board
         */
        public char[][] copyBoard() {
            char[][] boardCopy = new char[board.length][board[0].length];
            for (int i = 0; i < board.length; i++) {
                System.arraycopy(board[i], 0, boardCopy[i], 0, board[i].length);
            }
            return boardCopy;
        }

        /**
         * Generates a string representation of the current board.
         *
         * @return a string representation of the current board.
         */
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            for (int x = 0; x < board.length; x++) {
                builder.append(board[x]).append("\n");
            }
            return builder.toString();
        }

        /**
         * Generates a hash code.
         *
         * @return a hash code
         */
        @Override
        public int hashCode() {
            int hash = 7;
            hash = 29 * hash + pieces;
            hash = 29 * hash + Arrays.deepHashCode(board);
            return hash;
        }

        /**
         * Compares this BoardState to another BoardState.
         *
         * @param obj the other BoardState
         * @return true if both object are equivalent or, the isGoal flag is set
         * and the pieces remaining for each board are equal.
         */
        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final BoardState other = (BoardState) obj;
            if ((isGoal || other.isGoal) && pieces == other.pieces) {
                return true;
            }
            if (pieces != other.pieces) {
                return false;
            }
            if (!Arrays.deepEquals(board, other.board)) {
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
         * @param curX the current x position
         * @param curY the current y position
         * @param hor the horizontal length of the board
         * @param ver the vertical length of the board
         * @return a list of points that can be reached from the given (x, y)
         * position.
         */
        public List<Point> calculate(int curX, int curY, int hor, int ver);
    }
}
