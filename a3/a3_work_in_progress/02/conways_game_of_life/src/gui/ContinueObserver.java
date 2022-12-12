package gui;

import gamemodel.GameModel;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ContinueObserver extends Parent implements  IPlayerObserver {

    protected final GameModel aGameModel;
    protected final Button aButton;

    private Color FORBIDDEN_COLOR = Color.WHITE;

    public ContinueObserver(GameModel pGameModel) {

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
    public void colorIsSet(Color pColor, String pName) {
        aButton.setVisible(aGameModel.playerColorSet());
    }

    public Button getButton() {
        return aButton;
    }
}
