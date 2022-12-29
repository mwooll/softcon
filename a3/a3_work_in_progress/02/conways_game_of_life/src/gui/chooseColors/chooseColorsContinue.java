package gui.chooseColors;

import gui.AbstractContinue;
import initializer.InitializerObservable;

public class chooseColorsContinue extends AbstractContinue {

    public chooseColorsContinue(InitializerObservable pObservable) {
        super(pObservable);
    }

    @Override
    public void setVisibility() {
        aButton.setVisible(aObservable.playerColorSet());
    }

}
