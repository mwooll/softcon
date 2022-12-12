package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface ISetter {

    EventHandler<ActionEvent> handleSet();

    default boolean validate(String pInput){return true;};

}
