package gui;

import gamemodel.*;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class PlayerObserver extends HBox implements IPlayerObserver {

    private final GameModel aGameModel;
    private final String DEFAULT = "";
    private Text text = new Text();

    public PlayerObserver(GameModel pGameModel) {
        aGameModel = pGameModel;

        text.setText(DEFAULT);
        getChildren().addAll(text);

        aGameModel.addPlayersObservers(this);

    }

    @Override
    public void nameIsSet(String pName) {
        text.setText(pName);
    }

}
