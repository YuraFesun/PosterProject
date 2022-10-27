package service.impl;

import exception.EventNotFound;
import exception.NotEnoughTickets;
import exception.ObjectNotFound;
import models.Event;
import service.Operations;
import storage.Storage;
import java.util.List;
import java.util.Map;

import static models.ActionType.GET;

public class CancelOperation implements Operations {
    static Map<String, List<Event>> eventMap;

    static {
        eventMap = Storage.getInstance().getEventMap();
    }

    @Override
    public void doOperation(Event event) {
        List<Event> eventList = eventMap.get(event.getEventName());

        if (eventList == null) {
            throw new ObjectNotFound();
        }

        eventList.stream()
                .filter(e -> e.getActionType().equals(GET) &&
                        e.getDateTime().isAfter(event.getDateTime().plusDays(1)))
                .findAny()
                .orElseThrow(() -> new EventNotFound(event.getEventName()));

        eventList.add(event);
    }
}
