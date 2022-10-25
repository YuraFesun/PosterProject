package csvWorker;

import exception.BadFileReaderException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ParserCSV {

    public static final String SPLIT_BY = ",";

    public static List<String[]> parseCsv(String fileName)  {
        String line;
        List<String[]> saveEvent = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            line = br.readLine();
            if (line == null) {
                throw new BadFileReaderException();
            }
           do  {
                String[] events = line.split(SPLIT_BY);
                saveEvent.add(events);
            } while ((line = br.readLine()) != null);
        } catch (IOException e) {
            throw new BadFileReaderException();
        }
        return saveEvent;
    }
}





