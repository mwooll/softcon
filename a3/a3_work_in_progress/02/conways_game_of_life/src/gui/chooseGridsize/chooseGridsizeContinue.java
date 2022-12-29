package gui.chooseGridsize;

import gui.AbstractContinue;
import initializer.InitializerObservable;

public class chooseGridsizeContinue extends AbstractContinue {

    public chooseGridsizeContinue(InitializerObservable pObservable) {
        super(pObservable);
    }

    @Override
    public void setVisibility() {
        aButton.setVisible(aObservable.gridSizeSet());
    }

}
