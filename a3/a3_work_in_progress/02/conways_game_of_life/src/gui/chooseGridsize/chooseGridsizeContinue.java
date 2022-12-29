package gui.chooseGridsize;

import gui.AbstractContinue;
import initializer.IInitializerObservable;

public class chooseGridsizeContinue extends AbstractContinue {

    public chooseGridsizeContinue(IInitializerObservable pObservable) {
        super(pObservable);
    }

    @Override
    public void setVisibility() {
        aButton.setVisible(aObservable.gridSizeSet());
    }

}
