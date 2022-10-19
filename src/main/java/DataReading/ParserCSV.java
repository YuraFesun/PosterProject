package DataReading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParserCSV {

    public static void parceCSV() {
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(".idea/DataIn.csv"));
            while ((line = br.readLine()) != null)   //check are we have a next line
            {
                String[] ivents = line.split(splitBy);    // use comma as separator
                System.out.println("Event type" + ivents[0] + ivents[1] + ", Designation=" + ivents[2]
                        + ", Contact=" + ivents[3]);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

