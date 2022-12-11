package gui.secondStage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface IColorPicker {

    /**
     * Classes implementing this interface must be able to set the colors upon clicking the set button
     */

    EventHandler<ActionEvent> handleSet();

}
