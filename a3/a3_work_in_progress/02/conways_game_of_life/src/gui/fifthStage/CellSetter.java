package gui.fifthStage;

import cell.Cell;
import gamemodel.ITurnObserver;
import gui.ISetter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class CellSetter extends Parent implements ISetter {

    private ITurnObserver aObserver;
    private Cell aCell;
    protected final Button aButton = new Button("Kill");

    public CellSetter(ITurnObserver pObserver, Cell pCell) {
        aObserver = pObserver;
        aCell = pCell;
        aButton.setVisible(setVisibility());
        VBox vbox = new VBox(aButton);
        getChildren().add(vbox);

        aButton.setOnAction(handleSet());
    }

    @Override
    public EventHandler<ActionEvent> handleSet() {return e -> {
      // kill the cell
      aObserver.makeDeleteMove(aCell);
    };}

    boolean setVisibility() {
        return aCell.getState() == aObserver.returnCurrentPlayer().getColor();
    }
}
