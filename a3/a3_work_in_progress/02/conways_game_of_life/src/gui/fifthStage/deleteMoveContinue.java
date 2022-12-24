package gui.fifthStage;

import gamemodel.ITurnObservable;
import gui.AbstractContinue;
import gui.IContinue;
import gui.ITurnObserver;
import initializer.InitializerObservable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class deleteMoveContinue extends Parent implements IContinue, ITurnObserver {

    protected final ITurnObservable aObservable;
    protected final Button aButton;

    public deleteMoveContinue(ITurnObservable pObservable) {
        aObservable = pObservable;

        aButton = new Button("A cell has been killed");
        aButton.setVisible(false);

        VBox vbox = new VBox(aButton);
        getChildren().add(vbox);

        aObservable.addObserver(this);
    }

    public void setVisibility() {
        aButton.setVisible(aObservable.getStatusCellDeleted());
    }

    public Button getButton() {
        return aButton;
    }

    @Override
    public void stateCanDeleteChanged() {
        if (aObservable.getStatusCellDeleted()) {
            setVisibility();
        }
    }

    @Override
    public void stateCanCreateChanged() {

    }
}
