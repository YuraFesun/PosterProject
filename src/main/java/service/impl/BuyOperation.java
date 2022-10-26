package service.impl;

import exception.NoEventsForThisDate;
import exception.ObjectNotFound;
import models.ActionType;
import models.Event;
import service.Operations;
import storage.Storage;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import static models.ActionType.GET;

public class BuyOperation implements Operations {
    static HashMap<String, LinkedList<Event>> eventMap;

    static {
        eventMap = Storage.getInstance().getEventMap();
    }

    @Override
    public void doOperation(Event event) {
        LinkedList<Event> list = eventMap.get(event.getEventName());

        if (list == null || list.size() == 0) {
            throw new ObjectNotFound();
        }

        List<Event> sortedList = list.stream()
                .filter(e -> e.getActionType().equals(GET) &&
                        e.getDateTime().isAfter(event.getDateTime()))
                .collect(Collectors.toList());

        int currentQuantity = sortedList.stream()
                .map(Event::getTicketsQuantity)
                .reduce(Integer::sum).orElse(0);

        if (currentQuantity == 0) {
            throw new NoEventsForThisDate();
        }

        int remainingTickets = event.getTicketsQuantity();

        if (currentQuantity < remainingTickets) {
            throw new RuntimeException();
        }

        for (Event currentEvent : sortedList) {
            int currentTicketsQuantity = currentEvent.getTicketsQuantity();
            if (currentTicketsQuantity <= remainingTickets) {
                remainingTickets -= currentTicketsQuantity;
                currentEvent.setTicketsQuantity(0);
            } else {
                currentEvent.setTicketsQuantity(currentTicketsQuantity - remainingTickets);
                break;
            }
        }

        sortedList.removeIf(currentEvent -> currentEvent.getTicketsQuantity() == 0);
    }
}

