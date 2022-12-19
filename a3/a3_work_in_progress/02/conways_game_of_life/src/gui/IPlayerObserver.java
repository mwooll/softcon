package gui;

import javafx.scene.paint.Color;
import player.PlayerColor;

public interface IPlayerObserver {


    void nameIsSet(String pName, int pIndex);

    void colorIsSet(PlayerColor pPlayerColor, String pName);


}
