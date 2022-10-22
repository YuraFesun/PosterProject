package service;

import dataReading.ParserCSV;
import mapper.MapParseCsvToAction;
import models.Action;

import java.util.List;

public class MainService {

    public static void main(String[] args) {
        MapParseCsvToAction mapParseCsvToAction = new MapParseCsvToAction();
        List<String[]> parse = ParserCSV.parseCsv();
        for (String[] strings : parse) {
            Action currentModel = mapParseCsvToAction.map(strings);
        }
    }
}
