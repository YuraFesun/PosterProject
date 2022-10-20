package Model;

import java.util.LinkedList;

public class Storage {
    private LinkedList<Event> eventList;
    private static Storage instance;

    private Storage() {
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }
}
