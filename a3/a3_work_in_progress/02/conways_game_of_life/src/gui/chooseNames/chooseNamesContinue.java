package gui.chooseNames;

import gui.AbstractContinue;
import initializer.InitializerObservable;

public class chooseNamesContinue extends AbstractContinue {


    public chooseNamesContinue(InitializerObservable pObservable) {
        super(pObservable);
    }

    @Override
    public void setVisibility() {
        aButton.setVisible(aObservable.playerNamesSet());
    }
}
