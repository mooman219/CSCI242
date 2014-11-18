/**
 * Creates a number of threads and messages the console when each thread runs.
 *
 * @author Joseph Cumbo (mooman219)
 */
public class ManyThreads extends Thread {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage:  java ManyThreads number-of-threads");
            return;
        }
        int totalThreads = 0;
        try {
            totalThreads = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("ManyThreads:  Invalid number");
            return;
        }
        for (int i = 0; i < totalThreads; i++) {
            new ManyThreads("Hello I am thread " + i).start();
        }
    }

    private final String message;

    public ManyThreads(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println(message);
    }
}
