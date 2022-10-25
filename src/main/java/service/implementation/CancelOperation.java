package service.implementation;

import exception.ObjectNotFound;
import models.Event;
import service.Operations;
import storage.Storage;
import java.util.HashMap;
import java.util.LinkedList;

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
                .filter(e -> e.getDateTime().plusYears(1).isAfter(e.getDateTime().plusDays(1)))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Діма маладєц"));

        eventList.add(event);
    }
}
