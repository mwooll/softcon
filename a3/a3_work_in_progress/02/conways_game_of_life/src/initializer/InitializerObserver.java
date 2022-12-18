package initializer;

import javafx.scene.paint.Color;

public interface InitializerObserver {

    void setPlayerName(String pPlayerName, int pIndex);

    void setPlayerColor(Color pColor, String pPlayerName);

    void setGridDimension(int pValue, String pIdentifier);

}
