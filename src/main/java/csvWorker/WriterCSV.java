package csvWorker;

import exception.BadFileReaderException;
import models.Event;
import storage.Storage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;


public class WriterCSV {

    public static final String FILE_NAME = "Result.CSV";

    public static void writerCSV() {
        HashMap<String, LinkedList<Event>> eventList = Storage.getInstance().getEventMap();

        try (BufferedWriter br = new BufferedWriter(new FileWriter(FILE_NAME))) {
            StringBuilder resultText= new StringBuilder();
            resultText.append("Name, quantity")
                            .append("\n");
            eventList.forEach((key, value) -> {
                        Integer currentQuantity = value.stream()
                                .filter(e -> e.getDateTime().isAfter(LocalDateTime.now()))
                                .map(Event::getTicketsQuantity)
                                .reduce(Integer::sum).get();
                        resultText.append(key).append(",").append(currentQuantity).append("\n");
                    }
            );
                    br.write(resultText.toString());

        } catch (IOException e) {
            throw new BadFileReaderException();
        }
    }
}
