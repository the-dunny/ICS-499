package tech.teamfour.timer;


import tech.teamfour.events.TimerTickedEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


/**
 * The Class Timer.
 */
public class Timer implements PropertyChangeListener {
	
	/** The time value. */
	private int timeValue;
	
	/** The client. */
	private Notifiable client;

	/**
	 * Instantiates a new timer.
	 *
	 * @param client the client
	 * @param timeValue the time value
	 */
	public Timer(Notifiable client, int timeValue) {
		this.client = client;
		this.timeValue = timeValue;
	}

	/**
	 * Adds the time value.
	 *
	 * @param value the value
	 */
	public void addTimeValue(int value) {
		timeValue += value;
	}

	/**
	 * Gets the time value.
	 *
	 * @return the time value
	 */
	public int getTimeValue() {
		return timeValue;
	}

	/**
	 * Start.
	 */
	public void start() {
		Clock.instance().addPropertyChangeListener(this);
	}

	/**
	 * Stop.
	 *
	 * @return the int
	 */
	public int stop() {
		int finalScore;
		finalScore = timeValue;
		timeValue = 0;
		Clock.instance().removePropertyChangeListener(this);
		return finalScore;
	}

	/**
	 * Property change.
	 *
	 * @param arg0 the arg 0
	 */
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		timeValue = timeValue + 1_00;
		client.handleEvent(new TimerTickedEvent(timeValue));

	}
}
