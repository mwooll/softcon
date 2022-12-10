package gui;

import gamemodel.GameModel;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class PlayerContinueObserver extends VBox implements IPlayerObserver {

    private final GameModel aGameModel;
    private final Button aButton;

    public PlayerContinueObserver(GameModel pGameModel) {

        aGameModel = pGameModel;

        aButton = new Button("Continue");
        aButton.setVisible(false);

        VBox vbox = new VBox(aButton);
        getChildren().add(vbox);

        aGameModel.addPlayersObservers(this);

    }

    @Override
    public void nameIsSet(String pName, int pIndex) {
        if (aGameModel.allPlayersSet()) {
            aButton.setVisible(true);
        } else {
            aButton.setVisible(false);
        }
    }

}
