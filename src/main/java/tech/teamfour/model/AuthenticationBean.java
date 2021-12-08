package tech.teamfour.model;


/**
 * The Class AuthenticationBean.
 */
public class AuthenticationBean {

    /** The message. */
    private String message;

    /**
     * Instantiates a new authentication bean.
     *
     * @param message the message
     */
    public AuthenticationBean(String message) {
	this.message = message;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
	return message;
    }

    /**
     * Sets the message.
     *
     * @param message the new message
     */
    public void setMessage(String message) {
	this.message = message;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
	return String.format("HellowWorldBean [message=%s]", message);
    }
}
