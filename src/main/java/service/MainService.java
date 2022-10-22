package service;

import dataReading.ParserCSV;
import mapper.MapParseCsvToAction;
import models.Action;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class MainService {


    public static void main(String[] args) {
        ParserCSV parserCSV = new ParserCSV();
        MapParseCsvToAction mapParseCsvToAction = new MapParseCsvToAction();
        List<String[]> parse = parserCSV.parseCsv();
        for (String[] strings : parse) {
            Action currentModel = mapParseCsvToAction.map(strings);
        }
    }
}
