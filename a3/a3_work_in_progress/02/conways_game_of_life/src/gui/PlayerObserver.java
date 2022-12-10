package gui;

import gamemodel.*;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PlayerObserver extends VBox implements IPlayerObserver {

    private final GameModel aGameModel;
    private final int aIndex;
    private String defaultText = "Name Player";
    private String currentName;
    private Text text = new Text();

    public PlayerObserver(GameModel pGameModel, int pIndex) {

        aGameModel = pGameModel;
        aIndex = pIndex;

        defaultText = String.format("%s %s:", defaultText, aIndex+1);
        text.setText(defaultText);

        VBox vbox = new VBox(text);
        getChildren().add(vbox);

        aGameModel.addPlayersObservers(this);

    }

    @Override
    public void nameIsSet(String pName, int pIndex) {
        if (pIndex == aIndex) {
            text.setText(String.format("%s %s", defaultText, pName));
        }
    }

}
