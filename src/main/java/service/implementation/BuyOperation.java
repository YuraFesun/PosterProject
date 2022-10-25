package service.implementation;

import exception.NoEventsForThisDate;
import exception.ObjectNotFound;
import models.Event;
import service.Operations;
import storage.Storage;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;


public class BuyOperation implements Operations {

    public static final int TERM_AVAILABLE_EVENTS = 1;
    static HashMap<String, LinkedList<Event>> eventMap;

    static {
        eventMap = Storage.getInstance().getEventMap();
    }


    @Override
    public void doOperation(Event event)  {
        LinkedList<Event> list = eventMap.get(event.getEventName());

        if (list == null || list.size() == 0) {
            throw new ObjectNotFound();
        }

        Integer currentQuantity = list.stream()
                .filter(e -> e.getDateTime().plusYears(TERM_AVAILABLE_EVENTS).isAfter(e.getDateTime()))
                .map(Event::getTicketsQuantity)
                .reduce(Integer::sum).get();

        if (currentQuantity == 0) {
            throw new NoEventsForThisDate();
        }

        int remainingTickets = event.getTicketsQuantity();

        if (currentQuantity < event.getTicketsQuantity()) {
            throw new RuntimeException();
        }

        for (Event currentEvent : list) {




            if (remainingTickets == 0) {
                break;
            }
        }


    }
}

