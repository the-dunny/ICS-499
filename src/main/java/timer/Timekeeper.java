package timer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Implements a time keeper as a Runnable. An object adapter that uses
 * PropertyChangeSupport to implement the Observable part of the Observer
 * pattern.
 *
 */
public class Timekeeper implements Runnable {
    private static Timekeeper timekeeper;
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private Thread thread;

    /**
     * Method to remove a listener from the clock
     *
     * @param listener- the listener that we don't need
     */
    public void removePropertyChangeListener(PropertyChangeListener targetListener) {
        changeSupport.removePropertyChangeListener(targetListener);
    }

    /**
     * Method to add a listener to the clock
     *
     * @param new Listener that will be added to Clock
     */
    public void addPropertyChangeListener(PropertyChangeListener newListener) {
        changeSupport.addPropertyChangeListener(newListener);
    }

    private Timekeeper() {
        thread = new Thread(() -> run());
        thread.start();
    }

    /**
     * public method to create an instance of the clock. This method will call the
     * clock constructor which starts the thread.
     *
     * @return
     */
    public static Timekeeper instance() {
        if (timekeeper == null) {
            return timekeeper = new Timekeeper();
        } else {
            return timekeeper;
        }
    }

    /**
     * Override the run method. Thread thread will fire a change every second. It
     * will adjust for time spent in calculation to ensure that the amount of time request
     * is how long it will wait.
     */
    @Override
    public void run() {
        boolean keepGoing = true;
        long oneSecondAdjusted = 1_000L;
        try {

            while (keepGoing) {
                long timeBeforeSleep = System.currentTimeMillis();
                Thread.sleep(oneSecondAdjusted);
                changeSupport.firePropertyChange(null, null, null);
                long timeAfterSleep = System.currentTimeMillis();
                long actualElapsedTime = timeAfterSleep - timeBeforeSleep;

                // check to see if we drifted and adjust the 1 second timer
                if (actualElapsedTime > 1_000L) {
                    oneSecondAdjusted = 1_000L - (actualElapsedTime - 1_000L);
                } else {
                    oneSecondAdjusted = 1_000L;
                }
            }

        } catch (Exception e) {
            System.err.println("Error type: " + e.getClass() + '\n' + "Error message: " + e.getMessage());
            thread.interrupt();
        }

    }

}