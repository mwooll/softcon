package parser;

import player.PlayerColor;

import java.util.List;

public interface IParser {

    boolean validatePlayerName(List<String> pPlayers, String pPlayerName);

    boolean validateColor(List<PlayerColor> pColors, PlayerColor pColor);

    boolean validateHeight(int pMaxHeight, int pMinHeight, String pHeight);
    boolean validateWidth(int pMaxWidth, int pMinWidth, String pWidth);

    boolean validateCell(cell.Cell pCell);

}
