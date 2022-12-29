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
        GUIInitializer dummyinit = new GameModelTestInitializer(parser);
        GameModel gm = new GameModel(dummyinit, parser);

        assertFalse(gm.hasAPlayerLost());
        assertEquals(0, gm.returnCurrentTurnNumber());
        assertFalse(gm.getStatusCellCreated());
        assertFalse(gm.getStatusCellDeleted());
    }

    @Test
    public void testCreationNoWinnerYet() {
        IParser parser = new InitializerParser();
        GUIInitializer dummyinit = new GameModelTestInitializer(parser);
        GameModel gm = new GameModel(dummyinit, parser);

        assertEquals("Nobody lost yet.", gm.determineWinner());

    }

    @Test
    public void testCreationGrid() {
        IParser parser = new InitializerParser();
        GUIInitializer dummyinit = new GameModelTestInitializer(parser);
        GameModel gm = new GameModel(dummyinit, parser);
        Grid g = gm.returnGrid();

        assertEquals(2, g.getNumberOfMatchingCells(PlayerColor.BLUE));
        assertEquals(2, g.getNumberOfMatchingCells(PlayerColor.RED));

    }

    @Test
    public void testCreationNoCurrentPlayerAssigned() {
        IParser parser = new InitializerParser();
        GUIInitializer dummyinit = new GameModelTestInitializer(parser);
        GameModel gm = new GameModel(dummyinit, parser);

        assertNull(gm.returnCurrentPlayer());

    }


    @Test
    public void testPlayTurnPlayerOrder() {
        IParser parser = new InitializerParser();
        GUIInitializer dummyinit = new GameModelTestInitializer(parser);
        GameModel gm = new GameModel(dummyinit, parser);

        gm.playTurn();

        assertEquals("a", gm.returnCurrentPlayer().getName());
        assertEquals(1, gm.returnCurrentTurnNumber());

    }


    @Test
    public void testPlayTurn() {
        IParser parser = new InitializerParser();
        GUIInitializer dummyinit = new GameModelTestInitializer(parser);
        GameModel gm = new GameModel(dummyinit, parser);
        Grid g = gm.returnGrid();

        gm.playTurn();
        gm.makeBirthMove(g.getCell(0,4));
        gm.makeDeleteMove(g.getCell(0,0));

        assertEquals(3, g.getNumberOfMatchingCells(gm.returnCurrentPlayer().getColor()));

    }

    @Test
    public void testPlayTurnMultiple() {
        IParser parser = new InitializerParser();
        GUIInitializer dummyinit = new GameModelTestInitializer(parser);
        GameModel gm = new GameModel(dummyinit, parser);
        Grid g = gm.returnGrid();

        gm.playTurn();
        gm.playTurn();
        gm.playTurn();
        gm.playTurn();

        assertEquals(4, gm.returnCurrentTurnNumber());
        assertEquals("b", gm.returnCurrentPlayer().getName());

    }

    @Test
    public void testGameOver() {
        IParser parser = new InitializerParser();
        GUIInitializer dummyinit = new GameModelTestInitializer(parser);
        GameModel gm = new GameModel(dummyinit, parser);
        Grid g = gm.returnGrid();

        gm.playTurn();
        gm.makeDeleteMove(g.getCell(0,0));
        gm.makeDeleteMove(g.getCell(1,1));

        assertTrue(gm.hasAPlayerLost());

        String expected = String.format("Player b with Color Blue lost.");
        assertEquals(expected, gm.determineWinner());

    }

    @Test
    public void testGameOverTie() {
        IParser parser = new InitializerParser();
        GUIInitializer dummyinit = new GameModelTestInitializer(parser);
        GameModel gm = new GameModel(dummyinit, parser);
        Grid g = gm.returnGrid();

        gm.playTurn();
        gm.makeDeleteMove(g.getCell(0,0));
        gm.makeDeleteMove(g.getCell(1,1));
        gm.makeDeleteMove(g.getCell(0,5));
        gm.makeDeleteMove(g.getCell(1,4));

        assertTrue(gm.hasAPlayerLost());

        String expected = String.format("It's a tie, both players lost.");
        assertEquals(expected, gm.determineWinner());

    }

}