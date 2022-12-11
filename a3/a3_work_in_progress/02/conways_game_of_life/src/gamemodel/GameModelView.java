package gamemodel;

import javafx.scene.paint.Color;

public interface GameModelView {

    boolean checkPlayerName(String pPlayerName, int index);

    void setPlayerName(String pPlayerName, int index);

    void setPlayerColor(Color pColor, String pPlayerName);

    boolean playerNamesSet();

    boolean playerColorSet();

}
