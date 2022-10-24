package strategyOperation;

import models.ActionType;
import models.Operations;
import models.implementation.BuyOperation;
import models.implementation.CancelOperation;
import models.implementation.ConscienceOperation;
import models.implementation.GetOperation;

import java.util.HashMap;
import java.util.Map;

public class StrategyOperation {

    public Map<ActionType, Operations> strategyMap = new HashMap<>();

    public void initialize() {
        strategyMap.put(ActionType.GET, new GetOperation());
        strategyMap.put(ActionType.BUY, new BuyOperation());
        strategyMap.put(ActionType.CANCEL, new CancelOperation());
        strategyMap.put(ActionType.CONSCIENCE, new ConscienceOperation());
    }



}
