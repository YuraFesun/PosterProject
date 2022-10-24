package storage;

import models.Event;

import java.util.HashMap;
import java.util.LinkedList;

public class Storage {
    private HashMap <String, LinkedList<Event>> eventList;
    private static Storage instance;

    private Storage() {
    }

    public HashMap<String, LinkedList<Event>> getEventList() {
        return eventList;
    }

    public void setEventList(HashMap<String, LinkedList<Event>> eventList) {
        this.eventList = eventList;
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }
}
