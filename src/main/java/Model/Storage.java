package Model;

import java.util.HashMap;
import java.util.LinkedList;

public class Storage {
    private HashMap<String,Event> eventList;
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
