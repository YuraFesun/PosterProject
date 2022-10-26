package service.impl;


import exception.EmptyStorage;
import models.Event;
import org.junit.Test;
import service.Operations;
import storage.Storage;

import java.util.HashMap;
import java.util.LinkedList;

import static storage.Storage.eventMap;


public class GetOperationTest {

    @Test (expected = EmptyStorage.class)
    public void should_throwException_When_StorageIsEmpty() {
        Storage.setEventMap(null);

        Operations operations = new GetOperation();
        operations.doOperation(new Event());

    }


}