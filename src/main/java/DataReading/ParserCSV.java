package DataReading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserCSV {

    public static final String SPLIT_BY = ",";
    public static final String FILE_NAME = ".idea/DataIn.csv";

    public static List<String[]> parseCsv()  {
        String line;
        List<String[]> saveEvent = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            while ((line = br.readLine()) != null) {
                String[] events = line.split(SPLIT_BY);
                saveEvent.add(events);
            }
        } catch (IOException e) {
            System.out.println("The given file was not found or it is corrupted");
        }
        return saveEvent;
    }
}





