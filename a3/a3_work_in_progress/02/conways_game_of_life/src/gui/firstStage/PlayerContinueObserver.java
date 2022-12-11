package gui.firstStage;

import gamemodel.GameModel;
import gui.firstStage.IPlayerObserver;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class PlayerContinueObserver extends Parent implements IPlayerObserver {

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
        aButton.setVisible(aGameModel.allPlayersSet());
    }

    public Button getButton() {return aButton;}

}
