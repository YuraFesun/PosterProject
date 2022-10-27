package csvWorker;

import exception.BadFileReaderException;
import models.ActionType;
import models.Event;
import models.EventType;
import org.junit.Assert;
import org.junit.Test;
import service.Operations;
import service.impl.GetOperation;
import storage.Storage;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class WriterCSVTest {
    public static final ActionType ACTION_TYPE = ActionType.GET;
    public static final EventType EVENT_TYPE = EventType.CIRCUS;
    public static final int TICKETS_QUANTITY = 1000;
    public static final String NAME = "Kaydash family";
    public static final LocalDateTime DATE_TIME = LocalDateTime.of(2022,12,10,11,20);

    @Test
    public void should_PositiveResult_When_FileIsWritten() {
        List<String[]> expected = new LinkedList<>();
        expected.add(new String[]{"Name", "quantity"});
        expected.add(new String[]{"Kaydash family", "1000"});

        Map<String, List<Event>> eventMap = new HashMap<>();
        List<Event> list = new LinkedList<>();
        list.add(createEvent());
        eventMap.put(NAME, list);
        Storage.setEventMap(eventMap);
        WriterCSV.writerCSV("WriterTest.csv");

        List<String[]> currentWriterData = ReadCSV.parseCsv("WriterTest.csv");

        Assert.assertEquals(expected.get(0), currentWriterData.get(0));
        Assert.assertEquals(expected.get(1), currentWriterData.get(1));
    }

    private Event createEvent() {
        Event event = new Event();
        event.setActionType(ACTION_TYPE);
        event.setEventType(EVENT_TYPE);
        event.setEventName(NAME);
        event.setTicketsQuantity(TICKETS_QUANTITY);
        event.setDateTime(DATE_TIME);
        return event;
    }
}