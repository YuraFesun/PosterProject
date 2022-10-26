package service.impl;

import exception.EventNotFound;
import exception.ObjectNotFound;
import models.Event;
import service.Operations;
import storage.Storage;
import java.util.HashMap;
import java.util.LinkedList;
import static models.ActionType.CANCEL;

public class CancelOperation implements Operations {
    static HashMap<String, LinkedList<Event>> eventMap;

    static {
        eventMap = Storage.getInstance().getEventMap();
    }

    @Override
    public void doOperation(Event event) {
        LinkedList<Event> eventList = eventMap.get(event.getEventName());

        if (eventList == null) {
            throw new ObjectNotFound();
        }

        eventList.stream()
                .filter(e -> e.getActionType().equals(CANCEL) &&
                        e.getDateTime().isAfter(event.getDateTime().plusDays(1)))
                .findAny()
                .orElseThrow(EventNotFound::new);

        eventList.add(event);
    }
}
