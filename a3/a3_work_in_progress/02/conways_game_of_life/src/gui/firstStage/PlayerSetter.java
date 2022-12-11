package gui.firstStage;

import gamemodel.GameModel;

import gui.firstStage.AbstractPlayerSetter;
import javafx.scene.layout.HBox;

public class PlayerSetter extends AbstractPlayerSetter {

    private final String DEFAULT_TEXT = "Player Name";

    public PlayerSetter(GameModel pGameModel, int pIndex) {

        aGameModel = pGameModel;
        aIndex = pIndex;
        aDefaultText = DEFAULT_TEXT;

        aCurrentText = String.format("%s %s", aDefaultText, aIndex + 1);
        aLabel.setText(aCurrentText);

        final HBox hbox = new HBox(aLabel, aTextField, aButton);
        getChildren().add(hbox);

        aButton.setOnAction(handleSet());

    }

}
