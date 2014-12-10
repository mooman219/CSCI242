
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This class is a puzzle which takes an input file representing a solitaire
 * chess board and produces an optimal solution for solving it.
 *
 * @author Joseph Cumbo (jwc6999)
 */
public class Chess implements Puzzle {

    public static String[] testingArgs = "test.txt".split(" ");

    /**
     * Main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //args = Chess.testingArgs;
        /**
         * Validate the input
         */
        if (args.length < 1) {
            System.out.println("usage: java Chess input-file");
            return;
        }
        File configFile = new File(args[0]);
        Scanner in;
        try {
            in = new Scanner(configFile);
        } catch (FileNotFoundException ex) {
            System.out.println(args[0] + " not found.");
            return;
        }
        /**
         * Read the file
         */
        int vertical = in.nextInt();
        int horizontal = in.nextInt();
        char[][] board = new char[vertical][horizontal];
        for (int y = 0; y < vertical; y++) {
            for (int x = 0; x < horizontal; x++) {
                board[y][x] = in.next().charAt(0);
            }
        }
        in.close();
        /**
         * Solve the board
         */
        Solver solver = new Solver();
        Chess puzzle = new Chess(board);
        ArrayList<Chess> states = solver.solve(puzzle);
        for (int i = 0; i < states.size(); i++) {
            System.out.println("Step " + i + ":\n" + states.get(i).toString() + "\n");
        }
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
                    moves.add(new Point(curX - 2, curY - 1));
                    moves.add(new Point(curX - 2, curY + 1));
                    moves.add(new Point(curX - 1, curY - 2));
                    moves.add(new Point(curX - 1, curY + 2));
                    moves.add(new Point(curX + 2, curY - 1));
                    moves.add(new Point(curX + 2, curY + 1));
                    moves.add(new Point(curX + 1, curY - 2));
                    moves.add(new Point(curX + 1, curY + 2));
                    Chess.removeInvalidMoves(moves, hor, ver);
                    return moves;
                }
            });
            // Movements for pawn
            put('P', new MoveCalculator() {
                @Override
                public List<Point> calculate(int curX, int curY, int hor, int ver) {
                    ArrayList<Point> moves = new ArrayList<Point>();
                    moves.add(new Point(curX - 1, curY - 1));
                    moves.add(new Point(curX + 1, curY + 1));
                    moves.add(new Point(curX - 1, curY + 1));
                    moves.add(new Point(curX + 1, curY - 1));
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
                        if (i != curX) {
                            moves.add(new Point(i, curY));
                        }
                        if (i != curY) {
                            moves.add(new Point(curX, i));
                        }
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
                        if (i != curX) {
                            moves.add(new Point(i, curY));
                        }
                        if (i != curY) {
                            moves.add(new Point(curX, i));
                        }
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
            if (point.x < 0 || point.y < 0 || point.x >= lengthX || point.y >= lengthY) {
                iterator.remove();
            }
        }
    }

    private final char[][] board;

    /**
     * Initializes a chess object with a given board.
     *
     * @param board the given board
     */
    public Chess(char[][] board) {
        this.board = board;
    }

    /**
     * Gets the neighbors of this puzzle.
     *
     * @return the neighbors of this puzzle.
     */
    @Override
    public ArrayList<Chess> getNeighbors() {
        ArrayList<Chess> neighbors = new ArrayList<Chess>();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                /**
                 * If the piece is a valid chess piece.
                 */
                if (board[y][x] != '.' && moveStrategies.containsKey(board[y][x])) {
                    /**
                     * Calculate all the points the can be reached by the piece.
                     */
                    MoveCalculator moveCalculator = moveStrategies.get(board[y][x]);
                    List<Point> moves = moveCalculator.calculate(x, y, board[y].length, board.length);
                    /**
                     * If there's another piece in one of the reachable points,
                     * take it.
                     */
                    for (Point point : moves) {
                        if (board[point.y][point.x] != '.') {
                            char[][] localBoard = copyBoard();
                            localBoard[point.y][point.x] = board[y][x];
                            localBoard[y][x] = '.';
                            neighbors.add(new Chess(localBoard));
                        }
                    }
                }
            }
        }
        return neighbors;
    }

    /**
     * Gets the board.
     *
     * @return the board
     */
    public char[][] getBoard() {
        return board;
    }

    /**
     * Gets the number of pieces on the board.
     *
     * @return the number of pieces on the board
     */
    public int getPieces() {
        int pieces = 0;
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (board[x][y] != '.') {
                    pieces++;
                }
            }
        }
        return pieces;
    }

    /**
     * Checks if this puzzle is the goal puzzle.
     *
     * @return true if this puzzle is the goal puzzle, false otherwise
     */
    @Override
    public boolean isGoal() {
        return getPieces() == 1;
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
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    /**
     * Generates a hash code.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Arrays.deepHashCode(this.board);
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
        final Chess other = (Chess) obj;
        if (!Arrays.deepEquals(this.board, other.board)) {
            return false;
        }
        return true;
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
