package gui.playGame;

import cell.ICellObservable;
import gui.ICellObserver;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellObserver extends Parent implements ICellObserver {

    private final int SIZE = 40;
    private final Rectangle rect;
    private final ICellObservable aObservable;

    public CellObserver(ICellObservable pObservable) {

        aObservable = pObservable;

        rect = new Rectangle(SIZE, SIZE);
        rect.setStroke(Color.BLACK);
        rect.setFill(aObservable.getState().getColorHex());

        VBox vbox = new VBox(rect);
        getChildren().add(vbox);

        aObservable.addObserver(this);

    }

    @Override
    public void stateChanged() {
        rect.setFill(aObservable.getState().getColorHex());
    }
}
