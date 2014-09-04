
import java.util.Scanner;

/**
 *
 * @author Joseph Cumbo
 */
public class Snowflake {

    public static Turtle turtle;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        turtle = new Turtle(0.5, 0.5, 0);
        turtle.setCanvasSize(500, 500);

        drawSnowflake(100, 3);
        // TODO code application logic here
    }

    public static void snowflakePart(int S, int N) {
        if (N > 0) {
            turtle.goForward(S / 1000d);
            if (N > 1) {
                // generate the 5 sub-branches of this snowflake part
                turtle.turnLeft(120);
                for (int i = 0; i < 5; i++) {
                    snowflakePart(S / 3, N - 1);
                    turtle.turnRight(60);
                }
                turtle.turnRight(180);
            }
            turtle.goForward(-(S / 1000d));
        }
    }

    public static void drawSnowflake(int S, int N) {
        for (int i = 0; i < 6; i++) {
            snowflakePart(S, N);
            turtle.turnLeft(60);
        }
    }
}
