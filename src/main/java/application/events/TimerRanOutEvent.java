<<<<<<< HEAD
package application.events;

public class TimerRanOutEvent {
    private static TimerRanOutEvent instance;

    /**
     * This is a singleton class. Hence the private constructor.
     */

    private TimerRanOutEvent() {}

    /**
     * Returns the only instance of the class.
     *
     * @return the only instance
     */

    public static TimerRanOutEvent instance() {
	if (instance == null) instance = new TimerRanOutEvent();
	return instance;
    }
}
=======
package application.events;

public class TimerRanOutEvent {
    private static TimerRanOutEvent instance;

    /**
     * This is a singleton class. Hence the private constructor.
     */

    private TimerRanOutEvent() {}

    /**
     * Returns the only instance of the class.
     *
     * @return the only instance
     */

    public static TimerRanOutEvent instance() {
	if (instance == null) instance = new TimerRanOutEvent();
	return instance;
    }
}
>>>>>>> 660f5ff24e83d3d3fabf28a71c42e1b862a7df40
