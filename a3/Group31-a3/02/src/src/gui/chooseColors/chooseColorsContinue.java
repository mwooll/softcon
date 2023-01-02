package gui.chooseColors;

import gui.AbstractContinue;
import initializer.IInitializerObservable;

public class chooseColorsContinue extends AbstractContinue {

    public chooseColorsContinue(IInitializerObservable pObservable) {
        super(pObservable);
    }

    @Override
    public void setVisibility() {
        aButton.setVisible(aObservable.playerColorSet());
    }

}
