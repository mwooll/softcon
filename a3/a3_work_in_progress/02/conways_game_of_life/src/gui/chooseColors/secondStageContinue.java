package gui.chooseColors;

import gui.AbstractContinue;
import initializer.InitializerObservable;

public class secondStageContinue extends AbstractContinue {

    public secondStageContinue(InitializerObservable pObservable) {
        super(pObservable);
    }

    @Override
    public void setVisibility() {
        aButton.setVisible(aObservable.playerColorSet());
    }

}
