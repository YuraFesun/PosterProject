package strategyOperation;

import models.ActionType;
import service.Operations;
import service.implementation.BuyOperation;
import service.implementation.CancelOperation;
import service.implementation.GetOperation;

import java.util.HashMap;
import java.util.Map;

public class StrategyOperation {

    public Map<ActionType, Operations> strategyMap = new HashMap<>();

    public void initialize() {
        strategyMap.put(ActionType.GET, new GetOperation());
        strategyMap.put(ActionType.BUY, new BuyOperation());
        strategyMap.put(ActionType.CANCEL, new CancelOperation());
    }



}
