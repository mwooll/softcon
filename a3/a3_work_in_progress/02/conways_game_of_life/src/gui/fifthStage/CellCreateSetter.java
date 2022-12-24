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

public class CellCreateSetter extends Parent implements ISetter, ITurnObserver {

    private ICellSetterObserver aObserver;
    private ITurnObservable aObservable;
    private Cell aCell;
    protected final Button aButton = new Button("Create");

    public CellCreateSetter(ICellSetterObserver pObserver, Cell pCell, ITurnObservable pObservable) {
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
        aObserver.makeBirthMove(aCell);
    };}

    boolean setVisibility() {
        return aCell.getState() == PlayerColor.WHITE;
    }

    @Override
    public void stateCanDeleteChanged() {}

    @Override
    public void stateCanCreateChanged() {
        if (aObservable.getStatusCellCreated()) {
            aButton.setVisible(false);
        }
    }
}
