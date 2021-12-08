package tech.teamfour.events;


/**
 * The Class TimerTickedEvent.
 */
public class TimerTickedEvent {

    /** The time. */
    private int time;

    /**
     * This is a singleton class. Hence the private constructor.
     *
     * @param value the value
     */
    public TimerTickedEvent(int value) {
	this.time = value;
    }

    /**
     * Returns the only instance of the class.
     *
     * @return the only instance
     */
    public int getTimeValue() {
	return time;
    }
}
