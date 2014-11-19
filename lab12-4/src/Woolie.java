/**
 * Woolie - simulates a woolie crossing a bridge
 *
 * Each woolie object is constructed with a name, length of time it takes the
 * woolie to cross the bridge, and a destination city. Woolie objects extend the
 * Thread class and execute as an individual thread.
 *
 * @author Joseph Cumbo (mooman219)
 */
public class Woolie extends Thread {

    private final String myName;
    private final int myCrossingTime;
    private final String myDestination;
    private final Bridge bridge;

    /**
     * Construct a new Woolie object that can be started in a separate thread.
     * The constructor will simply initialize all of the instance fields.
     *
     * @param myName the name of this Woolie
     * @param myCrossingTime the number of seconds it takes the Woolie to cross
     * the bridge
     * @param myDestination the city the Woolie is heading to
     */
    public Woolie(String myName, int myCrossingTime, String myDestination, Bridge bridge) {
        this.myName = myName;
        this.myCrossingTime = myCrossingTime;
        this.myDestination = myDestination;
        this.bridge = bridge;
    }

    /**
     * This method handles the Woolie's crossing of the bridge. There are
     * several message that must be displayed to describe the Woolie's progress
     * crossing the bridge. Note: In all the following messages name is the name
     * of the Woolie.
     *
     * When the Woolie thread starts, the message "name has arrived at the
     * bridge." is displayed.
     *
     * When the Woolie starts crossing the bridge, at time 0, the message "name
     * is starting to cross." is displayed.
     *
     * For every one second interval, beyond time 0, that the Woolie is on the
     * bridge a message "'tab' name x seconds." should be printed where x is the
     * number of seconds that the Woolie has been on the bridge and "tab" is the
     * tab character - '\t'.
     *
     * When the Woolie reaches his or her destination display the message "name
     * leaves at city." where city is the Woolie's destination.
     */
    @Override
    public void run() {
        System.out.println(myName + " has arrived at the bridge.");
        bridge.enterBridge();
        System.out.println(myName + " is starting to cross.");
        for (int i = 0; i < myCrossingTime; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("\t" + myName + " " + i + " seconds.");
            } catch (InterruptedException ex) {
                System.out.println("A wild jehovah's witness has interrupted " + myName + ".");
            }
        }
        bridge.leaveBridge();
        System.out.println(myName + " leaves at " + myDestination + ".");
    }
}
