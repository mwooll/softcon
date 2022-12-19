package initializer;

import javafx.scene.paint.Color;
import player.PlayerColor;

import java.util.List;

public interface InitializerObserver {

    boolean validatePlayerName(String pPlayerName);

    boolean validateColorName(PlayerColor pPlayerColor);

    boolean validateWidth(String pWidth);

    boolean validateHeight(String pHeight);

    void setPlayerName(String pPlayerName, int pIndex);

    void setPlayerColor(PlayerColor pPlayerColor, String pPlayerName);

    void setGridDimension(int pValue, String pIdentifier);

}
