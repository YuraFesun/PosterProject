package service.implementation;

import models.Event;
import service.Operations;
import storage.Storage;

import java.util.HashMap;
import java.util.LinkedList;

public class GetOperation implements Operations {
    static HashMap<String, LinkedList<Event>> eventMap;
    static {
         eventMap = Storage.getInstance().getEventMap();
    }
    @Override
    public void doOperation(Event event) {
        LinkedList<Event> eventList = eventMap.get(event.getEventName());
        if (eventList == null) {
            eventList = new LinkedList<>();
        }
        eventList.add(event);
        eventMap.put(event.getEventName(), eventList);
        }
    }

