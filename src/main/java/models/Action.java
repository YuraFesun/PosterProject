package models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Action {
    private ActionType actionType;
    private EventType eventType;
    private String eventName;
    private int ticketsQuantity;
    private LocalDateTime dateTime;

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
        Action action = (Action) o;
        return ticketsQuantity == action.ticketsQuantity && actionType == action.actionType
                && eventType == action.eventType && Objects.equals(eventName, action.eventName)
                && Objects.equals(dateTime, action.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionType, eventType, eventName, ticketsQuantity, dateTime);
    }
}
