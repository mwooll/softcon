package terminal;

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
    public void testConstructorAndGetters() {
        TerminalMain aTerminal = new TerminalMain();
        Player[] aPlayers = aTerminal.getPlayers();
        assertEquals("", aPlayers[0].getName());
        assertEquals(PlayerColor.WHITE, aPlayers[1].getColor());
    }

    @Test
    public void testSetGridDimensionExtremaValid() {
        TerminalMain aTerminal = new TerminalMain();
        boolean worked = aTerminal.setGridDimensionBounds(10,0,10,0);
        assertTrue(worked);
    }

    @Test
    public void testSetGridDimensionExtremaInvalidHeight() {
        TerminalMain aTerminal = new TerminalMain();
        boolean workedNot = aTerminal.setGridDimensionBounds(10,20,10,0);
        assertFalse(workedNot);
    }

    @Test
    public void testSetGridDimensionExtremaInvalidWidth() {
        TerminalMain aTerminal = new TerminalMain();
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
        TerminalMain playerSetter = new TerminalMain(inputStream, System.out);
        Initializer playerInitializer = new TerminalInitializer(inputStream, System.out);
        IParser playerParser = new InitializerParser();

        playerSetter.initializePlayers(playerInitializer, playerParser);
        Player[] aPlayers = playerSetter.getPlayers();
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
        TerminalMain playerSetter = new TerminalMain(inputStream, System.out);
        Initializer playerInitializer = new TerminalInitializer(inputStream, System.out);
        IParser playerParser = new InitializerParser();

        playerSetter.initializePlayers(playerInitializer, playerParser);
        Player[] aPlayers = playerSetter.getPlayers();
        assertEquals("PlayerA", aPlayers[0].getName());
        assertEquals(PlayerColor.GREEN, aPlayers[0].getColor());
        assertEquals("PlayerB", aPlayers[1].getName());
        assertEquals(PlayerColor.ORANGE, aPlayers[1].getColor());
    }
}
