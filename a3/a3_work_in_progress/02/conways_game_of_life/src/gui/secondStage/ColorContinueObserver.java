package gui.secondStage;

import gamemodel.GameModel;
import gui.firstStage.*;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ColorContinueObserver extends Parent implements IPlayerObserver {

    private final GameModel aGameModel;
    private final Button aButton;

    public ColorContinueObserver(GameModel pGameModel) {

        aGameModel = pGameModel;

        aButton = new Button("Continue");
        aButton.setVisible(false);

        VBox vbox = new VBox(aButton);
        getChildren().add(vbox);

        aGameModel.addPlayersObservers(this);

    }

    @Override
    public void nameIsSet(String pName, int pIndex) {}

    @Override
    public void colorIsSet(Color pColor, String pName) {aButton.setVisible(aGameModel.playerColorSet());}

    public Button getButton() {return aButton;}
}
