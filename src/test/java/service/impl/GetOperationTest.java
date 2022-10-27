package service.impl;

import exception.EmptyStorage;
import models.ActionType;
import models.Event;
import models.EventType;
import org.junit.Assert;
import org.junit.Test;
import service.Operations;
import storage.Storage;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetOperationTest {
    public static final ActionType ACTION_TYPE = ActionType.GET;
    public static final EventType EVENT_TYPE = EventType.CIRCUS;
    public static final int TICKETS_QUANTITY = 1000;
    public static final String NAME = "Kaydash family";
    public static final LocalDateTime DATE_TIME = LocalDateTime.of(2022,3,10,11,20);

    @Test(expected = EmptyStorage.class)
    public void should_throwException_When_StorageIsEmpty() {
        Storage.setEventMap(null);
        Operations operations = new GetOperation();
        operations.doOperation(new Event());
    }

    @Test
    public void should_ResultTrue_When_StorageIsFull() {
        Storage.setEventMap(new HashMap<>());

        Operations operations = new GetOperation();
        Event event = createEvent();
        operations.doOperation(event);

        Map<String, List<Event>> filledMap = Storage.getInstance().getEventMap();
        List<Event> listEvents = filledMap.get(event.getEventName());

        Assert.assertEquals(1, filledMap.size());
        Assert.assertEquals(NAME, listEvents.get(0).getEventName());
        Assert.assertEquals(TICKETS_QUANTITY, listEvents.get(0).getTicketsQuantity());
        Assert.assertEquals(ACTION_TYPE, listEvents.get(0).getActionType());
        Assert.assertEquals(EVENT_TYPE, listEvents.get(0).getEventType());
        Assert.assertEquals(DATE_TIME, listEvents.get(0).getDateTime());
    }

    private Event createEvent() {
        Event event = new Event();
        event.setActionType(ACTION_TYPE);
        event.setEventType(EVENT_TYPE);
        event.setEventName(NAME);
        event.setTicketsQuantity(TICKETS_QUANTITY);
        event.setDateTime(DATE_TIME);
        return event;
    }
}