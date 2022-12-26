package gui.thirdStage;

import gui.AbstractContinue;
import initializer.InitializerObservable;

public class thirdStageContinue extends AbstractContinue {

    public thirdStageContinue(InitializerObservable pObservable) {
        super(pObservable);
    }

    @Override
    public void setVisibility() {
        aButton.setVisible(aObservable.gridSizeSet());
    }

}
