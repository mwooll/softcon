package gui.firstStage;

import gamemodel.GameModel;
import gui.AbstractContinue;

public class firstStageContinue extends AbstractContinue {


    public firstStageContinue(GameModel pGameModel) {
        super(pGameModel);
    }

    @Override
    public void setVisibility() {
        aButton.setVisible(aGameModel.playerNamesSet());
    }
}
