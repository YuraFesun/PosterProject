package service.impl;

import exception.NoEventsForThisDate;
import exception.NotEnoughTickets;
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

public class BuyOperationTest {
    public static final EventType EVENT_TYPE = EventType.CIRCUS;
    public static final int BUY_TICKETS_QUANTITY = 1000;
    public static final int LEFT_TICKETS_QUANTITY = 600;
    public static final String NAME = "Kaydash family";
    public static final LocalDateTime DATE_TIME = LocalDateTime.of(2022,12,10,11,20);

    @Test (expected = ObjectNotFound.class)
    public void should_throwException_When_ObjectIsNotFound() {
        Map<String, List<Event>> eventMap = new HashMap<>();
        eventMap.put(NAME, null);
        Storage.setEventMap(eventMap);

        Operations operations = new BuyOperation();
        Event event = createEvent(DATE_TIME, ActionType.BUY, BUY_TICKETS_QUANTITY);
        operations.doOperation(event);
    }

    @Test (expected = NoEventsForThisDate.class)
    public void should_throwException_When_NotExistEventOnThisDate() {
        Map<String, List<Event>> eventMap = new HashMap<>();
        List<Event> list = new LinkedList<>();
        list.add(createEvent(DATE_TIME, ActionType.GET, LEFT_TICKETS_QUANTITY));
        eventMap.put(NAME, list);
        Storage.setEventMap(eventMap);

        Operations operations = new BuyOperation();
        Event event = createEvent(DATE_TIME.plusYears(1), ActionType.BUY, BUY_TICKETS_QUANTITY);
        operations.doOperation(event);
    }

    @Test (expected = NotEnoughTickets.class)
    public void should_throwException_When_NotEnoughTickets() {
        Map<String, List<Event>> eventMap = new HashMap<>();
        List<Event> list = new LinkedList<>();
        list.add(createEvent(DATE_TIME.plusYears(1), ActionType.GET, LEFT_TICKETS_QUANTITY));
        eventMap.put(NAME, list);
        Storage.setEventMap(eventMap);

        Operations operations = new BuyOperation();
        Event event = createEvent(DATE_TIME, ActionType.BUY, BUY_TICKETS_QUANTITY);
        operations.doOperation(event);
    }

    @Test
    public void should_ReturnTrue_When_CurrentQuantityDecreased() {
        Map<String, List<Event>> eventMap = new HashMap<>();
        List<Event> list = new LinkedList<>();
        list.add(createEvent(DATE_TIME.plusYears(1), ActionType.GET, 1200));
        eventMap.put(NAME, list);
        Storage.setEventMap(eventMap);
        Event event = createEvent(DATE_TIME, ActionType.BUY, BUY_TICKETS_QUANTITY);

        int beforeQuantity = eventMap.get(event.getEventName()).stream()
                .filter(e -> e.getDateTime().isAfter(LocalDateTime.now()))
                .map(Event::getTicketsQuantity)
                .reduce(Integer::sum).orElse(0);

        Operations operations = new BuyOperation();
        operations.doOperation(event);

        int afterQuantity = eventMap.get(event.getEventName()).stream()
                    .filter(e -> e.getDateTime().isAfter(LocalDateTime.now()))
                    .map(Event::getTicketsQuantity)
                    .reduce(Integer::sum).orElse(0);

        Assert.assertEquals(BUY_TICKETS_QUANTITY, beforeQuantity-afterQuantity);
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