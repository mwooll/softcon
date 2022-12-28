package gui.createInitialConfig;

import cell.Cell;
import cell.ICellObservable;
import gui.ICellObserver;
import gui.IInitializerObserver;
import gui.ISetter;
import initializer.IInitializerSetterObserver;
import initializer.InitializerObservable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import player.PlayerColor;

public class CellChooseSetter extends Parent implements ISetter, IInitializerObserver, ICellObserver {

    private IInitializerSetterObserver aObserver;
    private InitializerObservable aObservable;
    private ICellObservable aCellObservable;
    protected final Button aButton = new Button("Add");

    public CellChooseSetter(IInitializerSetterObserver pObserver, ICellObservable pCellObservable, InitializerObservable pObservable) {
        aObserver = pObserver;
        aObservable = pObservable;
        aCellObservable = pCellObservable;

        setVisibility();

        VBox vbox = new VBox(aButton);
        getChildren().add(vbox);

        aButton.setOnAction(handleSet());
        aObservable.addObserver(this);
        aCellObservable.addObserver(this);

    }

    @Override
    public void setVisibility() {
        boolean hasMaxReached = aObservable.maxCellsReached();
        boolean canBeChosen = aCellObservable.getState() == PlayerColor.WHITE;
        aButton.setVisible(hasMaxReached && canBeChosen);
    }

    @Override
    public void cellIsChosen() {
        setVisibility();
    }

    @Override
    public EventHandler<ActionEvent> handleSet() {return e -> {
        // make the cell Black
        aObserver.chooseCell((Cell) aCellObservable);
    };}

    @Override
    public void stateChanged() {
        // when a cell changes its state, recalculate if the button should still be visible or not
        setVisibility();
    }


    @Override
    public void nameIsSet(String pName, int pIndex) {}
    @Override
    public void colorIsSet(PlayerColor pPlayerColor, String pName) {}
    @Override
    public void heightIsSet(int pHeight) {}
    @Override
    public void widthIsSet(int pWidth) {}
}
