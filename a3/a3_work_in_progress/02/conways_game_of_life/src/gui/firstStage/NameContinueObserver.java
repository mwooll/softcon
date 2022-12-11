package gui.firstStage;

import gamemodel.GameModel;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class NameContinueObserver extends Parent implements IPlayerObserver {

    private final GameModel aGameModel;
    private final Button aButton;

    public NameContinueObserver(GameModel pGameModel) {

        aGameModel = pGameModel;

        aButton = new Button("Continue");
        aButton.setVisible(false);

        VBox vbox = new VBox(aButton);
        getChildren().add(vbox);

        aGameModel.addPlayersObservers(this);

    }

    @Override
    public void nameIsSet(String pName, int pIndex) {
        aButton.setVisible(aGameModel.playerNamesSet());
    }

    @Override
    public void colorIsSet(Color pColor, String pName) {};

    public Button getButton() {return aButton;}

}
