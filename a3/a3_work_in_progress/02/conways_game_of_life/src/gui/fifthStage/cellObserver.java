package gui.fifthStage;

import cell.ICellObservable;
import gui.ICellObserver;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class cellObserver extends Parent implements ICellObserver {

    private final int SIZE = 40;
    private final Color aFillColor;
    private final ICellObservable aObservable;

    public cellObserver(ICellObservable pObservable) {

        aObservable = pObservable;

        Rectangle rect = new Rectangle(SIZE, SIZE);
        aFillColor = pObservable.getState().getColorHex();
        rect.setFill(aFillColor);

        VBox vbox = new VBox(rect);
        getChildren().add(vbox);

        aObservable.addObserver(this);

    }

    @Override
    public void stateChanged() {
    }
}
