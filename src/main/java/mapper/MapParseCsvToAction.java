package mapper;

import models.Action;
import models.ActionType;
import models.EventType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MapParseCsvToAction {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Action map(String[] arr) {
        Action action = new Action();

        action.setActionType(ActionType.valueOf(arr[0].toUpperCase()));
        action.setDateTime(LocalDateTime.parse(arr[4], FORMATTER));
        action.setEventType(EventType.valueOf(arr[1].toUpperCase()));
        action.setEventName(arr[2]);
        action.setTicketsQuantity(Integer.parseInt(arr[3]));

        return action;
    }
}
