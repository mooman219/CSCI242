import java.util.concurrent.locks.ReentrantLock;

/**
 * Simulation of the bridge troll controlling the passage of pedestrians on the
 * bridge crossing the Zyxnine River. This bridge troll will only allow one
 * Woolie on the bridge at a time. Waiting Woolies are selected at random and
 * allowed to cross when the bridge is free.
 *
 * @author Joseph Cumbo (mooman219)
 */
public class Bridge {

    /**
     * The main method.
     *
     * @param args
     */
    public static void main(String[] args) {
        Bridge bridge = new Bridge();
        new Woolie("A", 5, "Narnia", bridge).start();
        new Woolie("B", 8, "Hogwarts", bridge).start();
        new Woolie("C", 2, "Around the corner", bridge).start();
        new Woolie("D", 3, "Where he started", bridge).start();
    }

    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Constructor for the Bridge class.
     */
    public Bridge() {
    }

    /**
     * Request permission to enter the bridge.
     */
    public void enterBridge() {
        lock.lock();
    }

    /**
     * Notify the bridge troll that a Woolie is leaving the bridge.
     */
    public void leaveBridge() {
        lock.unlock();
    }

}
