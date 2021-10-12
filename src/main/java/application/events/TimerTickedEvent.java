<<<<<<< HEAD
package application.events;

public class TimerTickedEvent {
    private int time;

    /**
     * This is a singleton class. Hence the private constructor.
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
=======
package application.events;

public class TimerTickedEvent {
    private int time;

    /**
     * This is a singleton class. Hence the private constructor.
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
>>>>>>> 660f5ff24e83d3d3fabf28a71c42e1b862a7df40
