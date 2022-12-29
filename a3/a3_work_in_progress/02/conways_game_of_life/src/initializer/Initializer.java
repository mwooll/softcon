package initializer;

import cell.Cell;
import cell.Grid;
import player.Player;

public interface Initializer {

    String choosePlayerName();

    player.PlayerColor choosePlayerColor();

    int chooseGridDimensionHeight(int pMaxHeight, int pMinHeight);

    int chooseGridDimensionWidth(int pMaxWidth, int pMinWidth);

    Cell chooseCell(Grid pGrid);

    Grid createStartingConfiguration();

    Grid tacticalStartingConfiguration(int gridWidth, int gridHeight, Player choosingPlayer, player.Player otherPlayer);
}
