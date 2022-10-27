package storage;

import models.Event;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {
    public static Map <String, List<Event>> eventMap;
    private static Storage instance;

    private Storage() {}

    public Map<String, List<Event>> getEventMap() {
        return eventMap;
    }

    public static void setEventMap(Map<String, List<Event>> eventMap) {
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
