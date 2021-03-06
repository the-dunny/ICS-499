package tech.teamfour.events;


/**
 * The Class TimerStart.
 */
public class TimerStart {

    /** The instance. */
    private static TimerStart instance;

    /**
     * This is a singleton class. Hence the private constructor.
     */
    private TimerStart() {}

    /**
     * Returns the only instance of the class.
     *
     * @return the only instance
     */
    public static TimerStart instance() {
	if (instance == null) instance = new TimerStart();
	return instance;
    }

}
