package gui.fourthStage;

import gamemodel.GameModel;
import gui.AbstractContinue;

public class fourthStageContinue extends AbstractContinue {

    public fourthStageContinue(GameModel pGameModel) {
        super(pGameModel);
    }

    @Override
    public void setVisibility() {
        aButton.setVisible(aGameModel.gridSizeSet());
    }

}
