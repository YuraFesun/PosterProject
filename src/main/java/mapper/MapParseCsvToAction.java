package mapper;

import models.Event;
import models.ActionType;
import models.EventType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MapParseCsvToAction {
    public static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Event map(String[] arr) {
        Event event = new Event();

        event.setActionType(ActionType.valueOf(arr[0].toUpperCase()));
        event.setDateTime(LocalDateTime.parse(arr[4], FORMATTER));
        event.setEventType(EventType.valueOf(arr[1].toUpperCase()));
        event.setEventName(arr[2]);
        event.setTicketsQuantity(Integer.parseInt(arr[3]));

        return event;
    }
}
