package gui.createInitialConfig;

import gui.AbstractContinue;
import initializer.IInitializerObservable;

public class createInitialConfigContinue extends AbstractContinue {

    public createInitialConfigContinue(IInitializerObservable pObservable) {
        super(pObservable);
    }

    @Override
    public void setVisibility() {
        aButton.setVisible(aObservable.cellsAreSet());
    }

}
