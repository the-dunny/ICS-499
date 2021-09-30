<<<<<<< HEAD
package application.timer;

import java.beans.*;

public class Clock implements Runnable {
    private Thread thread = new Thread(this);
    private static Clock clock;
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
	this.propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
	this.propertyChangeSupport.removePropertyChangeListener(listener);
    }

    private Clock() {
	thread.start();
    }

    public static Clock instance() {
	if (clock == null) clock = new Clock();
	return clock;
    }

    public void run() {
	try {
	    while (true) {
		Thread.sleep(1000);
		this.propertyChangeSupport.firePropertyChange(null, null, null);
	    }
	} catch (InterruptedException ie) {}
    }
}
=======
package application.timer;

import java.beans.*;

public class Clock implements Runnable {
    private Thread thread = new Thread(this);
    private static Clock clock;
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
	this.propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
	this.propertyChangeSupport.removePropertyChangeListener(listener);
    }

    private Clock() {
	thread.start();
    }

    public static Clock instance() {
	if (clock == null) clock = new Clock();
	return clock;
    }

    public void run() {
	try {
	    while (true) {
		Thread.sleep(1000);
		this.propertyChangeSupport.firePropertyChange(null, null, null);
	    }
	} catch (InterruptedException ie) {}
    }
}
>>>>>>> 660f5ff24e83d3d3fabf28a71c42e1b862a7df40
