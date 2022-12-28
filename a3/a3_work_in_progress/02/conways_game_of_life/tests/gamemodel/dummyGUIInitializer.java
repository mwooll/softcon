package gamemodel;

import cell.Grid;
import gui.IInitializerObserver;
import initializer.GUIInitializer;
import parser.IParser;
import player.PlayerColor;

import java.util.Collections;
import java.util.List;

public class dummyGUIInitializer extends GUIInitializer {

    int H = 3;
    int W = 3;
    String pN1 = "a";
    String pN2 = "b";
    PlayerColor pC1 = PlayerColor.BLUE;
    PlayerColor pC2 = PlayerColor.RED;

    public dummyGUIInitializer(IParser pParser) {
        super(pParser);

        // set player names, colors, and the gridsize
        setPlayerName(pN1, 0);
        setPlayerName(pN2, 1);
        setPlayerColor(pC1, pN1);
        setPlayerColor(pC2, pN2);
        setGridDimension(H, "H");
        setGridDimension(W, "W");

        // set the initial grid which is given to the gamemodel
        createEmptyStartingGrid();
        createStartingConfiguration();

    }

    @Override
    public void createEmptyStartingGrid() {
        Grid tmpGrid = new Grid(3,3);

        tmpGrid.getCell(0,0).instantBirth(PlayerColor.BLACK);
        tmpGrid.getCell(1,1).instantBirth(PlayerColor.BLACK);

        aInitialGrid = tmpGrid;
    }

}

