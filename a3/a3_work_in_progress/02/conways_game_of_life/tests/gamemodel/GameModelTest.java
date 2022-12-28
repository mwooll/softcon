package gamemodel;

import cell.Grid;
import initializer.GUIInitializer;
import org.junit.jupiter.api.Test;
import parser.IParser;
import parser.InitializerParser;
import player.PlayerColor;

import static org.junit.jupiter.api.Assertions.*;

class GameModelTest {

    @Test
    public void testCreation() {
        IParser parser = new InitializerParser();
        GUIInitializer dummyinit = new dummyGUIInitializer(parser);
        GameModel gm = new GameModel(dummyinit, parser);

        assertFalse(gm.hasAPlayerLost());

    }

    @Test
    public void testCreationGrid() {
        IParser parser = new InitializerParser();
        GUIInitializer dummyinit = new dummyGUIInitializer(parser);
        GameModel gm = new GameModel(dummyinit, parser);
        Grid g = gm.returnGrid();

        assertTrue(g.getNumberOfMatchingCells(PlayerColor.BLUE) == 2);
        assertTrue(g.getNumberOfMatchingCells(PlayerColor.RED) == 2);

    }

}