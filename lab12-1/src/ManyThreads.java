/**
 * Creates a number of threads and messages the console when each thread runs.
 *
 * @author Joseph Cumbo (mooman219)
 */
public class ManyThreads extends Thread {

    /**
     * The main method.
     *
     * @param args the number of threads to be created
     */
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

    // Message that the ManyThreads object will output.
    private final String message;

    /**
     * Initializes a new ManyThreads object.
     *
     * @param message the message that will be outputted when run() is called
     */
    public ManyThreads(String message) {
        this.message = message;
    }

    /**
     * The default run method.
     */
    @Override
    public void run() {
        System.out.println(message);
    }
}
