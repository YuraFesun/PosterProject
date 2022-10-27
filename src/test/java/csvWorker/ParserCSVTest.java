package csvWorker;

import exception.BadFileReaderException;
import org.junit.Assert;
import org.junit.Test;
import java.util.LinkedList;
import java.util.List;
public class ParserCSVTest {
    @Test
    public void should_ReturnTrue_When_FileFilled() {
        List<String[]> expected = new LinkedList<>();
        expected.add(new String[]{"get", "movie", "harry potter", "15", "2022-07-10 15:00"});
        expected.add(new String[]{"buy", "movie", "I am legend", "15", "2022-11-10 18:00"});
        expected.add(new String[]{"get", "play", "Gamlet", "20", "2022-03-10 11:00"});

        List<String[]> currentParseData = ReadCSV.parseCsv("TestParse.csv");

        Assert.assertEquals(expected.size(), currentParseData.size());
        Assert.assertEquals(expected.get(0), currentParseData.get(0));
    }

    @Test (expected = BadFileReaderException.class)
    public void should_ThrowException_When_FileIsEmpty() {
        ReadCSV.parseCsv("EmptyFile.csv");
    }

    @Test (expected = BadFileReaderException.class)
    public void should_ThrowException_When_FileNotExist() {
        ReadCSV.parseCsv("Testnew.csv");
    }
}