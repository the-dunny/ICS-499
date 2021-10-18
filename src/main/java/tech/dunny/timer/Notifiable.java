package tech.dunny.timer;


import tech.dunny.events.TimerStart;
import tech.dunny.events.TimerStop;
import tech.dunny.events.TimerTickedEvent;

/**
 * An entity that can be notified of timing events
 *
 */
public interface Notifiable {
	
    public void handleEvent(TimerTickedEvent event);
    public void handleEvent(TimerStart event);
    public void handleEvent(TimerStop event);

}