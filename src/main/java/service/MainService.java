package service;

import workingWithFiles.ParserCSV;
import mapper.MapParseCsvToAction;
import models.Action;
import strategyOperation.StrategyOperation;

import java.util.List;

public class MainService {

    public static void main(String[] args) {
        StrategyOperation strategyOperation = new StrategyOperation();
        strategyOperation.initialize();

        MapParseCsvToAction mapParseCsvToAction = new MapParseCsvToAction();
        List<String[]> parse = ParserCSV.parseCsv();
        for (String[] strings : parse) {
            Action currentModel = mapParseCsvToAction.map(strings);
            strategyOperation.strategyMap.get(currentModel.getActionType()).doOperation(currentModel);
        }
    }
}
