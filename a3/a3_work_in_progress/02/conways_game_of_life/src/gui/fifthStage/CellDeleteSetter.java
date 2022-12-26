package gui.fifthStage;

import cell.Cell;
import gamemodel.ICellSetterObserver;
import gamemodel.ITurnObservable;
import gui.ISetter;
import gui.ITurnObserver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import player.PlayerColor;

public class CellDeleteSetter extends Parent implements ISetter, ITurnObserver {

    private ICellSetterObserver aObserver;
    private ITurnObservable aObservable;
    private Cell aCell;
    protected final Button aButton = new Button("Kill");

    public CellDeleteSetter(ICellSetterObserver pObserver, Cell pCell, ITurnObservable pObservable) {
        aObserver = pObserver;
        aObservable = pObservable;
        aCell = pCell;
        aButton.setVisible(setVisibility());
        VBox vbox = new VBox(aButton);
        getChildren().add(vbox);

        aButton.setOnAction(handleSet());
        aObservable.addObserver(this);
    }

    @Override
    public EventHandler<ActionEvent> handleSet() {return e -> {
      // kill the cell
      aObserver.makeDeleteMove(aCell);
    };}

    boolean setVisibility() {
        // Only cells that are not white and not of own color
        PlayerColor currentCellColor = aCell.getState();
        PlayerColor currentPlayerColor = aObserver.returnCurrentPlayer().getColor();
        return currentCellColor != PlayerColor.WHITE && currentPlayerColor != currentCellColor;
    }

    @Override
    public void stateCanDeleteChanged() {
        if (aObservable.getStatusCellDeleted()) {
            aButton.setVisible(false);
        }
    }

    @Override
    public void stateCanCreateChanged() {}
}
