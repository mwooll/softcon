package gui.firstStage;

import gamemodel.*;
import gui.*;
import initializer.InitializerObservable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import player.PlayerColor;

public class PlayerObserver extends Parent implements IPlayerObserver {

    private final InitializerObservable aObservable;
    private final int aIndex;
    private String aDefaultText = "Name Player";
    private final Text text = new Text();

    public PlayerObserver(InitializerObservable pObservable, int pIndex) {

        aObservable = pObservable;
        aIndex = pIndex;

        aDefaultText = String.format("%s %s:", aDefaultText, aIndex+1);
        text.setText(aDefaultText);

        VBox vbox = new VBox(text);
        getChildren().add(vbox);

        aObservable.addPlayerObserver(this);

    }

    @Override
    public void nameIsSet(String pName, int pIndex) {
        if (pIndex == aIndex) {
            text.setText(String.format("%s %s", aDefaultText, pName));
        }
    }

    @Override
    public void colorIsSet(PlayerColor pPlayerColor, String pName) {};


}
