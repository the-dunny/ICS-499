package application.timer;

import application.events.TimerRanOutEvent;
import application.events.TimerTickedEvent;

/**
 * An entity that can be notified of timing events
 *
 */
public interface Notifiable {
    public void handleEvent(TimerTickedEvent event);
    public void handleEvent(TimerRanOutEvent event);
}