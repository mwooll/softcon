package parser;

import cell.Cell;
import player.PlayerColor;

import java.util.List;

public class GameParser implements IParser {

    @Override
    public boolean validatePlayerName(List<String> pNamesInUse, String pName) {
        return false;
    }

    @Override
    public boolean validateColor(List<PlayerColor> pColors, PlayerColor pColor) {
        return false;
    }

    @Override
    public boolean validateHeight(int pMaxHeight, int pMinHeight, String pHeight) {
        return false;
    }

    @Override
    public boolean validateWidth(int pMaxWidth, int pMinWidth, String pWidth) {
        return false;
    }

    @Override
    public boolean validateCellInGrid(int pHeight, int pWidth) { return false; };
}
