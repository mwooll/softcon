package terminal;

import cell.Grid;
import initializer.Initializer;
import initializer.TerminalInitializer;
import org.junit.jupiter.api.Test;
import parser.IParser;
import parser.InitializerParser;
import player.Player;
import player.PlayerColor;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class TerminalMainTest {
    @Test
    public void testConstructor() {
        Initializer playerInitializer = new TerminalInitializer();
        IParser playerParser = new InitializerParser();
        TerminalMain aTerminal = new TerminalMain(playerInitializer, playerParser);
    }

    @Test
    public void testSetGridDimensionExtremaValid() {
        Initializer playerInitializer = new TerminalInitializer();
        IParser playerParser = new InitializerParser();
        TerminalMain aTerminal = new TerminalMain(playerInitializer, playerParser);
        boolean worked = aTerminal.setGridDimensionBounds(10,0,10,0);
        assertTrue(worked);
    }

    @Test
    public void testSetGridDimensionExtremaInvalidHeight() {
        Initializer playerInitializer = new TerminalInitializer();
        IParser playerParser = new InitializerParser();
        TerminalMain aTerminal = new TerminalMain(playerInitializer, playerParser);
        boolean workedNot = aTerminal.setGridDimensionBounds(10,20,10,0);
        assertFalse(workedNot);
    }

    @Test
    public void testSetGridDimensionExtremaInvalidWidth() {
        Initializer playerInitializer = new TerminalInitializer();
        IParser playerParser = new InitializerParser();
        TerminalMain aTerminal = new TerminalMain(playerInitializer, playerParser);
        boolean workedNot = aTerminal.setGridDimensionBounds(10,0,10,20);
        assertFalse(workedNot);
    }

    @Test
    public void testInitializePlayersValid() {
        String inputString = """
                PlayerA
                green
                PlayerB
                orange""";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        Initializer playerInitializer = new TerminalInitializer(inputStream, System.out);
        IParser playerParser = new InitializerParser();
        TerminalMain playerSetter = new TerminalMain(playerInitializer, playerParser, inputStream, System.out);

        Player[] aPlayers = playerSetter.initializePlayers();
        assertEquals("PlayerA", aPlayers[0].getName());
        assertEquals(PlayerColor.GREEN, aPlayers[0].getColor());
        assertEquals("PlayerB", aPlayers[1].getName());
        assertEquals(PlayerColor.ORANGE, aPlayers[1].getColor());
    }

    @Test
    public void testInitializePlayersInvalid() {
        String inputString = """
                !+*ç
                PlayerA
                white
                green
                PlayerA
                PlayerB
                green
                orange""";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        Initializer playerInitializer = new TerminalInitializer(inputStream, System.out);
        IParser playerParser = new InitializerParser();
        TerminalMain playerSetter = new TerminalMain(playerInitializer, playerParser, inputStream, System.out);

        Player[] aPlayers = playerSetter.initializePlayers();
        assertEquals("PlayerA", aPlayers[0].getName());
        assertEquals(PlayerColor.GREEN, aPlayers[0].getColor());
        assertEquals("PlayerB", aPlayers[1].getName());
        assertEquals(PlayerColor.ORANGE, aPlayers[1].getColor());
    }

    @Test
    public void testOrderPlayersUnchanged() {
        Player firstPlayer = new Player("A", PlayerColor.ORANGE);
        Player secondPlayer = new Player("Z", PlayerColor.GREEN);
        Player[] pPlayers = {firstPlayer, secondPlayer};
        Player[] aPlayers = TerminalMain.orderPlayers(pPlayers);
        assertEquals("A", aPlayers[0].getName());
        assertEquals("Z", aPlayers[1].getName());
    }

    @Test
    public void testOrderPlayersChanged() {
        Player firstPlayer = new Player("A", PlayerColor.ORANGE);
        Player secondPlayer = new Player("Z", PlayerColor.GREEN);
        Player[] pPlayers = {secondPlayer, firstPlayer};
        Player[] aPlayers = TerminalMain.orderPlayers(pPlayers);
        assertEquals("A", aPlayers[0].getName());
        assertEquals("Z", aPlayers[1].getName());
    }

    @Test
    public void testInitializeGridValid() {
        String inputString = """
                5
                5
                0,0
                0,1
                quit
                """;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        Initializer gridInitializer = new TerminalInitializer(inputStream, System.out);
        IParser gridParser = new InitializerParser();
        TerminalMain gridSetter = new TerminalMain(gridInitializer, gridParser, inputStream, System.out);

        Player firstPlayer = new Player("A", PlayerColor.ORANGE);
        Player secondPlayer = new Player("Z", PlayerColor.GREEN);
        Player[] aPlayers = {firstPlayer, secondPlayer};
        Grid aGrid = gridSetter.initializeGrid(aPlayers);

        assertEquals(5, aGrid.getWidth());
        assertEquals(5, aGrid.getHeight());
        assertEquals(4, aGrid.getNumberOfColoredCells());

        assertEquals(PlayerColor.ORANGE, aGrid.getCell(0, 0).getState());
        assertEquals(PlayerColor.GREEN, aGrid.getCell(4, 4).getState());
    }
}
