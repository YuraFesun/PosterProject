package service.impl;

import exception.EmptyStorage;
import models.Event;
import service.Operations;
import storage.Storage;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GetOperation implements Operations {
    static Map<String, List<Event>> eventMap;
    static {
         eventMap = Storage.getInstance().getEventMap();
    }
    @Override
    public void doOperation(Event event) {

        if (eventMap == null) {
            throw new EmptyStorage();
        }

        List<Event> eventList = eventMap.get(event.getEventName());

        if (eventList == null) {
            eventList = new LinkedList<>();
        }

        eventList.add(event);
        eventMap.put(event.getEventName(), eventList);
        }
    }

