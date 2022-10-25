package service;

import exception.ObjectNotFound;
import models.Event;

public interface Operations {

    void doOperation(Event event) throws ObjectNotFound;
}
