package gui.chooseNames;

import gui.AbstractContinue;
import initializer.IInitializerObservable;

public class chooseNamesContinue extends AbstractContinue {


    public chooseNamesContinue(IInitializerObservable pObservable) {
        super(pObservable);
    }

    @Override
    public void setVisibility() {
        aButton.setVisible(aObservable.playerNamesSet());
    }
}
