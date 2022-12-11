package gui.firstStage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface IPlayerSetter {

    /**
     * Classes implementing this interface must be able to validate an input and set it upon click on set button
     */

    EventHandler<ActionEvent> handleSet();

    boolean validatePlayerName(String pName);
}
