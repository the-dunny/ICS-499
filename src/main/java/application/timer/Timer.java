<<<<<<< HEAD
package application.timer;

import java.beans.*;

import application.events.TimerRanOutEvent;
import application.events.TimerTickedEvent;

public class Timer implements PropertyChangeListener {
    private int timeValue;
    private Notifiable client;

    public Timer(Notifiable client, int timeValue) {
	this.client = client;
	this.timeValue = timeValue;
    }

    public void addTimeValue(int value) {
	timeValue += value;
    }

    public int getTimeValue() {
	return timeValue;
    }

    public void start() {
	Clock.instance().addPropertyChangeListener(this);
    }

    public void stop() {
	timeValue = 0;
	Clock.instance().removePropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent arg0) {
	if (--timeValue <= 0) {
	    client.handleEvent(TimerRanOutEvent.instance());
	    Clock.instance().removePropertyChangeListener(this);
	} else {
	    client.handleEvent(new TimerTickedEvent(timeValue));
	}
    }
}
=======
package application.timer;

import java.beans.*;

import application.events.TimerRanOutEvent;
import application.events.TimerTickedEvent;

public class Timer implements PropertyChangeListener {
    private int timeValue;
    private Notifiable client;

    public Timer(Notifiable client, int timeValue) {
	this.client = client;
	this.timeValue = timeValue;
    }

    public void addTimeValue(int value) {
	timeValue += value;
    }

    public int getTimeValue() {
	return timeValue;
    }

    public void start() {
	Clock.instance().addPropertyChangeListener(this);
    }

    public void stop() {
	timeValue = 0;
	Clock.instance().removePropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent arg0) {
	if (--timeValue <= 0) {
	    client.handleEvent(TimerRanOutEvent.instance());
	    Clock.instance().removePropertyChangeListener(this);
	} else {
	    client.handleEvent(new TimerTickedEvent(timeValue));
	}
    }
}
>>>>>>> 660f5ff24e83d3d3fabf28a71c42e1b862a7df40
