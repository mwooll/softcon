package gui.createInitialConfig;

import gui.AbstractContinue;
import initializer.InitializerObservable;

public class createInitialConfigContinue extends AbstractContinue {

    public createInitialConfigContinue(InitializerObservable pObservable) {
        super(pObservable);
    }

    @Override
    public void setVisibility() {
        aButton.setVisible(aObservable.cellsAreSet());
    }

}
