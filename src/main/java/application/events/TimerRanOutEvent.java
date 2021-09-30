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
