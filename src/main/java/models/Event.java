package models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event {
    private ActionType actionType;
    private EventType eventType;
    private String eventName;
    private int ticketsQuantity;
    private LocalDateTime dateTime;

    public Event(ActionType actionType, EventType eventType, String eventName, int ticketsQuantity, LocalDateTime dateTime) {
        this.actionType = actionType;
        this.eventType = eventType;
        this.eventName = eventName;
        this.ticketsQuantity = ticketsQuantity;
        this.dateTime = dateTime;
    }

    public Event() {
    }

    public ActionType getActionType() {
        return actionType;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public int getTicketsQuantity() {
        return ticketsQuantity;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setTicketsQuantity(int ticketsQuantity) {
        this.ticketsQuantity = ticketsQuantity;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return ticketsQuantity == event.ticketsQuantity && actionType == event.actionType
                && eventType == event.eventType && Objects.equals(eventName, event.eventName)
                && Objects.equals(dateTime, event.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionType, eventType, eventName, ticketsQuantity, dateTime);
    }
}
