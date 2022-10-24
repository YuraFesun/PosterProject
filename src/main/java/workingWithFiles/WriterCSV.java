package workingWithFiles;

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
        HashMap<String, LinkedList<Event>> eventList = Storage.getInstance().getEventList();

        try (BufferedWriter br = new BufferedWriter(new FileWriter(FILE_NAME))) {
            StringBuilder resultText= new StringBuilder();
            eventList.forEach((key, value) -> {
                        long currentQuantity = value.stream()
                                .filter(e -> e.getDate().isAfter(LocalDateTime.now()))
                                .map(Event::getTicketsQuantity)
                                .count();
                        resultText.append(key).append(currentQuantity).append("\n");
                    }
            );
                    br.write(resultText.toString());

        } catch (IOException e) {
            throw new BadFileReaderException();
        }
    }
}
