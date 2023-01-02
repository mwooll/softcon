package initializer;

import cell.Cell;
import player.PlayerColor;

public interface IInitializerSetterObserver {

    boolean validatePlayerName(String pPlayerName);

    boolean validateColorName(PlayerColor pPlayerColor);

    boolean validateWidth(String pWidth);

    boolean validateHeight(String pHeight);

    void setPlayerName(String pPlayerName, int pIndex);

    void setPlayerColor(PlayerColor pPlayerColor, String pPlayerName);

    void setGridDimension(int pValue, String pIdentifier);

    void chooseCell(Cell pCell);

}
