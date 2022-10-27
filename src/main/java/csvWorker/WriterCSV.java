package csvWorker;

import exception.BadFileReaderException;
import models.Event;
import storage.Storage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class WriterCSV {


    public static void writerCSV(String fileWriterName) {
        Map<String, List<Event>> eventList = Storage.getInstance().getEventMap();

        try (BufferedWriter br = new BufferedWriter(new FileWriter(fileWriterName))) {
            StringBuilder resultText= new StringBuilder();
            resultText.append("Name,quantity").append("\n");
            eventList.forEach((key, value) -> {
                        Integer currentQuantity = value.stream()
                                .filter(e -> e.getDateTime().isAfter(LocalDateTime.now()))
                                .map(Event::getTicketsQuantity)
                                .reduce(Integer::sum).orElse(0);
                        resultText.append(key).append(",").append(currentQuantity).append("\n");
            });
            br.write(resultText.toString());
        } catch (IOException e) {
            throw new BadFileReaderException();
        }
    }
}
