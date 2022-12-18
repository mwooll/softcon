package gui.firstStage;

import gamemodel.GameModel;
import gui.AbstractContinue;
import initializer.InitializerObservable;

public class firstStageContinue extends AbstractContinue {


    public firstStageContinue(InitializerObservable pObservable) {
        super(pObservable);
    }

    @Override
    public void setVisibility() {
        aButton.setVisible(aObservable.playerNamesSet());
    }
}
