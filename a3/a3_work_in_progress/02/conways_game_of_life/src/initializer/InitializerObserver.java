package initializer;

import javafx.scene.paint.Color;

public interface InitializerObserver {

    boolean validatePlayerName(String pPlayerName);

    boolean validateWidth(String pWidth);
    boolean validateHeight(String pHeight);

    void setPlayerName(String pPlayerName, int pIndex);

    void setPlayerColor(Color pColor, String pPlayerName);

    void setGridDimension(int pValue, String pIdentifier);

}
