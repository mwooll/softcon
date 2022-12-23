package parser;

import player.PlayerColor;

import java.util.List;

public interface IParser {

    boolean validatePlayerName(List<String> pNamesInUse, String pName);
    boolean validateColor(List<PlayerColor> pColors, PlayerColor pColor);
    boolean validateHeight(int pMaxHeight, int pMinHeight, String pHeight);
    boolean validateWidth(int pMaxWidth, int pMinWidth, String pWidth);

    boolean validateCellInGrid(cell.Grid pGrid, int pHeight, int pWidth);

}
