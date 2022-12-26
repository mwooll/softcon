package gui.fifthStage;

import gamemodel.ITurnObservable;
import gui.IContinue;
import gui.ITurnObserver;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class turnContinue extends Parent implements IContinue, ITurnObserver {

    protected final ITurnObservable aObservable;
    protected final Button aButton;

    public turnContinue(ITurnObservable pObservable) {
        aObservable = pObservable;

        aButton = new Button("You killed and created a cell. Go through generation.");
        aButton.setVisible(false);

        VBox vbox = new VBox(aButton);
        getChildren().add(vbox);

        aObservable.addObserver(this);
    }

    public void setVisibility() {
        aButton.setVisible(
                aObservable.getStatusCellDeleted() && aObservable.getStatusCellCreated()
        );
    }

    public Button getButton() {
        return aButton;
    }

    @Override
    public void stateCanDeleteChanged() {
        if (aObservable.getStatusCellDeleted() && aObservable.getStatusCellCreated()) {
            setVisibility();
        }
    }

    @Override
    public void stateCanCreateChanged() {
        if (aObservable.getStatusCellDeleted() && aObservable.getStatusCellCreated()) {
            setVisibility();
        }
    }
}
