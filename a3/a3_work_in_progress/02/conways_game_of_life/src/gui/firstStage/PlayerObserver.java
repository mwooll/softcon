package gui.firstStage;

import gamemodel.*;
import gui.*;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PlayerObserver extends Parent implements IPlayerObserver {

    private final GameModel aGameModel;
    private final int aIndex;
    private String aDefaultText = "Name Player";
    private final Text text = new Text();

    public PlayerObserver(GameModel pGameModel, int pIndex) {

        aGameModel = pGameModel;
        aIndex = pIndex;

        aDefaultText = String.format("%s %s:", aDefaultText, aIndex+1);
        text.setText(aDefaultText);

        VBox vbox = new VBox(text);
        getChildren().add(vbox);

        aGameModel.addPlayersObservers(this);

    }

    @Override
    public void nameIsSet(String pName, int pIndex) {
        if (pIndex == aIndex) {
            text.setText(String.format("%s %s", aDefaultText, pName));
        }
    }

    @Override
    public void colorIsSet(Color pColor, String pName) {};


}
