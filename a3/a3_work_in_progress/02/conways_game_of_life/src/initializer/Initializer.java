package initializer;

import cell.Grid;
import player.Player;

public interface Initializer {

    String choosePlayerName();

    player.PlayerColor choosePlayerColor();

    int chooseGridDimensionHeight(int pMaxHeight, int pMinHeight);

    int chooseGridDimensionWidth(int pMaxWidth, int pMinWidth);

    Grid createStartingConfiguration(int gridWidth, int gridHeight, Player pPlayer);
}
