package mapper;

import models.Action;
import models.ActionType;
import models.EventType;
import java.time.LocalDateTime;

public class MapParseCsvToAction {

    public Action map(String[] arr) {
        Action action = new Action();

        action.setActionType(ActionType.valueOf(arr[0]));
        action.setDateTime(LocalDateTime.parse(arr[4]));
        action.setEventType(EventType.valueOf(arr[1]));
        action.setEventName(arr[2]);
        action.setTicketsQuantity(Integer.parseInt(arr[3]));

        return action;
    }
}
