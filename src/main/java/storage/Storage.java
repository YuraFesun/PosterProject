package storage;


import models.Event;

import java.util.HashMap;
import java.util.LinkedList;

public class Storage {
    public static HashMap <String, LinkedList<Event>> eventMap;
    private static Storage instance;

    private Storage() {
    }

    public HashMap<String, LinkedList<Event>> getEventMap() {
        return eventMap;
    }

    public static void setEventMap(HashMap<String, LinkedList<Event>> eventMap) {
        Storage.eventMap = eventMap;
    }

    public synchronized static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
            eventMap = new HashMap<>();
        }
        return instance;
    }
}
