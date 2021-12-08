package tech.teamfour.timer;


import tech.teamfour.events.TimerStart;
import tech.teamfour.events.TimerStop;
import tech.teamfour.events.TimerTickedEvent;


/**
 * An entity that can be notified of timing events.
 */
public interface Notifiable {

    /**
     * Handle event.
     *
     * @param event the event
     */
    public void handleEvent(TimerTickedEvent event);

    /**
     * Handle event.
     *
     * @param event the event
     */
    public void handleEvent(TimerStart event);

    /**
     * Handle event.
     *
     * @param event the event
     */
    public void handleEvent(TimerStop event);

}