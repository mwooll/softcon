package gui.thirdStage;

import gamemodel.GameModel;
import gui.AbstractContinue;

public class thirdStageContinue extends AbstractContinue {

    public thirdStageContinue(GameModel pGameModel) {
        super(pGameModel);
    }

    @Override
    public void setVisibility() {
        aButton.setVisible(aGameModel.gridSizeSet());
    }

}
