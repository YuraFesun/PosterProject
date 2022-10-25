package service;

import csvWorker.ParserCSV;
import mapper.MapParseCsvToAction;
import models.Event;
import strategyOperation.StrategyOperation;
import csvWorker.WriterCSV;

import java.util.List;

public class MainService {

    public static final String FILE_NAME = "DataIn.csv";

    public static void main(String[] args) {
        StrategyOperation strategyOperation = new StrategyOperation();
        strategyOperation.initialize();

        MapParseCsvToAction mapParseCsvToAction = new MapParseCsvToAction();
        List<String[]> parse = ParserCSV.parseCsv(FILE_NAME);
        for (String[] strings : parse) {
            Event currentModel = mapParseCsvToAction.map(strings);
            strategyOperation.strategyMap.get(currentModel.getActionType()).
                    doOperation(currentModel);
        }
        WriterCSV.writerCSV();
    }
}
