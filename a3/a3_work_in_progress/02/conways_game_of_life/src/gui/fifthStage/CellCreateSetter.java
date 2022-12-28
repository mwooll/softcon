package gui.fifthStage;

import cell.Cell;
import cell.ICellObservable;
import gamemodel.ICellSetterObserver;
import gamemodel.IGameModelObservable;
import gui.ICellObserver;
import gui.ISetter;
import gui.IGameModelObserver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import player.PlayerColor;

public class CellCreateSetter extends Parent implements ISetter, IGameModelObserver, ICellObserver {

    private ICellSetterObserver aObserver;
    private IGameModelObservable aObservable;
    private ICellObservable aCellObservable;
    protected final Button aButton = new Button("Create");

    public CellCreateSetter(ICellSetterObserver pObserver, ICellObservable pCellObservable, IGameModelObservable pObservable) {
        aObserver = pObserver;
        aObservable = pObservable;
        aCellObservable = pCellObservable;
        aButton.setVisible(setVisibility());
        VBox vbox = new VBox(aButton);
        getChildren().add(vbox);

        aButton.setOnAction(handleSet());
        aObservable.addObserver(this);
        aCellObservable.addObserver(this);
    }

    @Override
    public EventHandler<ActionEvent> handleSet() {return e -> {
        // kill the cell
        aObserver.makeBirthMove((Cell) aCellObservable);
    };}

    boolean setVisibility() {
        return aCellObservable.getState() == PlayerColor.WHITE;
    }

    @Override
    public void stateCanDeleteChanged() {}

    @Override
    public void stateCanCreateChanged() {
        if (aObservable.getStatusCellCreated()) {
            aButton.setVisible(false);
        }
    }

    @Override
    public void currentPlayerChanged() {}

    @Override
    public void stateChanged() {
        // when the cell changes its state, recalculate if you need to show button
        aButton.setVisible(setVisibility());
    }
}
