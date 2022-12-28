package gui.createInitialConfig;

import gui.AbstractContinue;
import initializer.InitializerObservable;

public class fourthStageContinue extends AbstractContinue {

    public fourthStageContinue(InitializerObservable pObservable) {
        super(pObservable);
    }

    @Override
    public void setVisibility() {
        aButton.setVisible(aObservable.cellsAreSet());
    }

}
