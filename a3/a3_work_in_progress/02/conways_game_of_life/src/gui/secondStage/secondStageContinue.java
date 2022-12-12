package gui.secondStage;

import gamemodel.GameModel;
import gui.AbstractContinue;

public class secondStageContinue extends AbstractContinue {

    public secondStageContinue(GameModel pGameModel) {
        super(pGameModel);
    }

    @Override
    public void setVisibility() {
        aButton.setVisible(aGameModel.playerColorSet());
    }

}
