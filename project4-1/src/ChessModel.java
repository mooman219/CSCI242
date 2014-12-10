
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

/**
 * Class definition for the model of a chess game.
 *
 * @author Joseph Cumbo (jwc6999)
 */
public class ChessModel extends Observable {

    private final int lengthX;
    private final int lengthY;
    private final char[][] defaultBoard;
    private final char[][] board;
    private final Chess puzzle;
    private final Solver solver;

    private Point selectedPoint = null;
    private int moveCount = 0;
    private int state = 0;
    private List<Point> validMoves;

    /**
     * Initializes a new ChessModel.
     *
     * @param filename the name of the file to read from
     * @throws FileNotFoundException if the given filename does not exist
     */
    public ChessModel(String filename) throws FileNotFoundException {
        File configFile = new File(filename);
        Scanner in = new Scanner(configFile);

        lengthY = in.nextInt();
        lengthX = in.nextInt();
        defaultBoard = new char[lengthY][lengthX];
        board = new char[lengthY][lengthX];
        for (int y = 0; y < lengthY; y++) {
            for (int x = 0; x < lengthX; x++) {
                defaultBoard[y][x] = in.next().charAt(0);
                board[y][x] = defaultBoard[y][x];
            }
        }
        puzzle = new Chess(board);
        solver = new Solver();

        in.close();
    }

    /**
     * Gets the length in the x direction of the board.
     *
     * @return the length in the x direction of the board
     */
    public int getLengthX() {
        return lengthX;
    }

    /**
     * Gets the length in the y direction of the board.
     *
     * @return the length in the y direction of the board
     */
    public int getLengthY() {
        return lengthY;
    }

    /**
     * Gets the piece at the given location.
     *
     * @param x the x position of the piece
     * @param y the y position of the piece
     * @return the piece at the given location
     */
    public char getPiece(int x, int y) {
        return board[y][x];
    }

    /**
     * Gets the state of the game. A state of 0 means the game is in progress. A
     * state of 1 means the game has been won. A state of 2 means the game has
     * been lost.
     *
     * @return the state of the game
     */
    public int getState() {
        return state;
    }

    /**
     * Gets the total moves made this game.
     *
     * @return the total moves made this game.
     */
    public int getMoveCount() {
        return moveCount;
    }

    /**
     * Gets the currently selected point. If no point is selected, the selected
     * point is null.
     *
     * @return the currently selected point
     */
    public Point getSelectedPoint() {
        return selectedPoint;
    }

    /**
     * Checks if the currently selected piece can move to the given location.
     *
     * @param x the x position of the location
     * @param y the y position of the location
     * @return ture if the currently selected piece can move to the given
     * location, false otherwise
     */
    public boolean isMovePossible(int x, int y) {
        return validMoves != null && validMoves.contains(new Point(x, y));
    }

    /**
     * Automatically move to the next position if possible.
     */
    public void move() {
        if (state != 0) {
            return;
        }
        List<Chess> states = solver.solve(puzzle);
        if (states != null) {
            if (states.size() > 1) {
                for (int y = 0; y < lengthY; y++) {
                    for (int x = 0; x < lengthX; x++) {
                        board[y][x] = states.get(1).getBoard()[y][x];
                    }
                }
            }
            selectedPoint = null;
            validMoves = null;
            if (puzzle.getPiecesRemining() <= 1) {
                state = 1;
            }
            moveCount++;
        } else {
            state = 2;
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Selects the cell at the given location.
     *
     * @param x the x position of the location
     * @param y the y position of the location
     */
    public void selectCell(int x, int y) {
        if (state != 0) {
            return;
        } else if (x < 0 || y < 0 || x >= lengthX || y >= lengthY) {
            if (selectedPoint == null) {
                return;
            }
            selectedPoint = null;
            validMoves = null;
        } else if (selectedPoint != null) {
            if (!(selectedPoint.x == x && selectedPoint.y == y)) {
                if (board[y][x] != '.' && isMovePossible(x, y)) {
                    board[y][x] = board[selectedPoint.y][selectedPoint.x];
                    board[selectedPoint.y][selectedPoint.x] = '.';
                    moveCount++;
                    if (puzzle.getPiecesRemining() <= 1) {
                        state = 1;
                    } else if (solver.solve(puzzle) == null) {
                        state = 2;
                    }
                }
            }
            selectedPoint = null;
            validMoves = null;
        } else {
            if (Chess.isPieceValid(board[y][x])) {
                selectedPoint = new Point(x, y);
                validMoves = Chess.getMoves(board[selectedPoint.y][selectedPoint.x], selectedPoint.x, selectedPoint.y, lengthX, lengthY);
            } else {
                return;
            }
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Resets the game to its original state.
     */
    public void reset() {
        validMoves = null;
        selectedPoint = null;
        moveCount = 0;
        state = 0;
        for (int y = 0; y < lengthY; y++) {
            for (int x = 0; x < lengthX; x++) {
                board[y][x] = defaultBoard[y][x];
            }
        }
        setChanged();
        notifyObservers();
    }
}
