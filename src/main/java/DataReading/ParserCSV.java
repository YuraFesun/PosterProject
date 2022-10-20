package DataReading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserCSV {

    public static final String SPLIT_BY = ",";

    public static List<String[]> parceCsv()  {
        String line;
        List<String[]> saveEvent = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(".idea/DataIn.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] events = line.split(SPLIT_BY);
                saveEvent.add(events);
            }
        } catch (IOException e) {
            System.out.println("Даний файл не знайдено або він є пошкодженим");
        }
        return saveEvent;
    }
}



