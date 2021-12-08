package tech.teamfour.timer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**
 * The Class Clock.
 */
public class Clock implements Runnable {

    /** The thread. */
    private Thread thread = new Thread(this);

    /** The clock. */
    private static Clock clock;

    /** The property change support. */
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Adds the property change listener.
     *
     * @param listener the listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
	this.propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Removes the property change listener.
     *
     * @param listener the listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
	this.propertyChangeSupport.removePropertyChangeListener(listener);
    }

    /**
     * Instantiates a new clock.
     */
    private Clock() {
	thread.start();
    }

    /**
     * Instance.
     *
     * @return the clock
     */
    public static Clock instance() {
	if (clock == null) clock = new Clock();
	return clock;
    }

    /**
     * Run.
     */
    public void run() {

	try {
	    long oneTenthSecondAdjusted = 100L;
	    while (true) {
		long timeBeforeSleep = System.currentTimeMillis();
		Thread.sleep(oneTenthSecondAdjusted);
		this.propertyChangeSupport.firePropertyChange(null, null, null);
		long timeAfterSleep = System.currentTimeMillis();
		long actualElapsedTime = timeAfterSleep - timeBeforeSleep;

		//correct drift that comes from Thread's sleep method
		if(actualElapsedTime > 100L){
		    oneTenthSecondAdjusted = 100L - (actualElapsedTime - 100L);
		}else {
		    oneTenthSecondAdjusted = 100L;
		}
	    }
	} catch (InterruptedException ie) {}
    }
}
