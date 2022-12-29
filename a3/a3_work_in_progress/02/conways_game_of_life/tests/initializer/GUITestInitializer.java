package initializer;

import cell.Grid;
import parser.IParser;
import player.PlayerColor;

public class GUITestInitializer extends GUIInitializer {

    int H = 3;
    int W = 3;
    String pN1 = "b";
    String pN2 = "a";
    PlayerColor pC1 = PlayerColor.BLUE;
    PlayerColor pC2 = PlayerColor.RED;

    public GUITestInitializer(IParser pParser) {
        super(pParser);

        // set player names, colors, and the grid size
        setPlayerName(pN1, 0);
        setPlayerName(pN2, 1);
        setPlayerColor(pC1, pN1);
        setPlayerColor(pC2, pN2);
        setGridDimension(H, "H");
        setGridDimension(W, "W");

    }


}
