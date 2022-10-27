package service.impl;

import exception.EventNotFound;
import exception.ObjectNotFound;
import models.ActionType;
import models.Event;
import models.EventType;
import org.junit.Assert;
import org.junit.Test;
import service.Operations;
import storage.Storage;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CancelOperationTest {
    public static final EventType EVENT_TYPE = EventType.CIRCUS;
    public static final int TICKETS_QUANTITY = 1000;
    public static final int Cancel_TICKETS_QUANTITY = 200;
    public static final String NAME = "Kaydash family";
    public static final LocalDateTime DATE_TIME = LocalDateTime.of(2022,12,16,11,20);

    @Test (expected = ObjectNotFound.class)
    public void should_throwException_When_ObjectIsNotFound() {
        Map<String, List<Event>> eventMap = new HashMap<>();
        eventMap.put(NAME, null);
        Storage.setEventMap(eventMap);

        Operations operations = new CancelOperation();
        Event event = createEvent(DATE_TIME, ActionType.CANCEL, TICKETS_QUANTITY);
        operations.doOperation(event);
    }

    @Test (expected = EventNotFound.class)
    public void should_throwException_When_EventIsNotFound() {
        Map<String, List<Event>> eventMap = new HashMap<>();
        List<Event> list = new LinkedList<>();
        list.add(createEvent(DATE_TIME.minusMonths(1), ActionType.GET, TICKETS_QUANTITY));
        eventMap.put(NAME, list);
        Storage.setEventMap(eventMap);

        Operations operations = new CancelOperation();
        Event event = createEvent(DATE_TIME, ActionType.BUY, TICKETS_QUANTITY);
        operations.doOperation(event);
    }

    @Test
    public void should_ResultTrue_When_CurrentQuantityGrow() {
        Map<String, List<Event>> eventMap = new HashMap<>();
        List<Event> list = new LinkedList<>();
        list.add(createEvent(DATE_TIME.plusYears(2), ActionType.GET, TICKETS_QUANTITY));
        eventMap.put(NAME, list);
        Storage.setEventMap(eventMap);
        Event event = createEvent(DATE_TIME, ActionType.CANCEL, Cancel_TICKETS_QUANTITY);

        int beforeQuantity = eventMap.get(event.getEventName()).stream()
                .filter(e -> e.getDateTime().isAfter(LocalDateTime.now()))
                .map(Event::getTicketsQuantity)
                .reduce(Integer::sum).orElse(0);

        Operations operations = new CancelOperation();
        operations.doOperation(event);

        int afterQuantity = eventMap.get(event.getEventName()).stream()
                .filter(e -> e.getDateTime().isAfter(LocalDateTime.now()))
                .map(Event::getTicketsQuantity)
                .reduce(Integer::sum).orElse(0);

        Assert.assertEquals(Cancel_TICKETS_QUANTITY, afterQuantity-beforeQuantity);
    }

    private Event createEvent(LocalDateTime dateTime, ActionType type, int tickets) {
        Event event = new Event();
        event.setActionType(type);
        event.setEventType(EVENT_TYPE);
        event.setEventName(NAME);
        event.setTicketsQuantity(tickets);
        event.setDateTime(dateTime);
        return event;
    }
}