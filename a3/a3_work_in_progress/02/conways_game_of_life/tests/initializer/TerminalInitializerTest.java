package initializer;

import player.PlayerColor;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;


public class TerminalInitializerTest {


    @Test
    public void testChoosePlayerName() {
        String testName = "Tester";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testName.getBytes());
        TerminalInitializer nameInitializer = new TerminalInitializer(inputStream, System.out);
        String playerName = nameInitializer.choosePlayerName();

        assertEquals(testName, playerName);
    }

    @Test
    public void testChoosePlayerColorCamelCasing() {
        String colorString = "Red";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(colorString.getBytes());
        TerminalInitializer colorInitializer = new TerminalInitializer(inputStream, System.out);
        PlayerColor chosenColor = colorInitializer.choosePlayerColor();

        assertEquals(PlayerColor.RED, chosenColor);
    }

    @Test
    public void testChoosePlayerColorAllLowerCase() {
        String colorString = "blue";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(colorString.getBytes());
        TerminalInitializer colorInitializer = new TerminalInitializer(inputStream, System.out);
        PlayerColor chosenColor = colorInitializer.choosePlayerColor();

        assertEquals(PlayerColor.BLUE, chosenColor);
    }

    @Test
    public void testChoosePlayerColorAllUpperCase() {
        String colorString = "GREEN";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(colorString.getBytes());
        TerminalInitializer colorInitializer = new TerminalInitializer(inputStream, System.out);
        PlayerColor chosenColor = colorInitializer.choosePlayerColor();

        assertEquals(PlayerColor.GREEN, chosenColor);
    }

    @Test
    public void testChoosePlayerColorCapslockCamel() {
        String colorString = "oRANGE";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(colorString.getBytes());
        TerminalInitializer colorInitializer = new TerminalInitializer(inputStream, System.out);
        PlayerColor chosenColor = colorInitializer.choosePlayerColor();

        assertEquals(PlayerColor.ORANGE, chosenColor);
    }

    @Test
    public void testChoosePlayerColorReversedCamel() {
        String colorString = "magentA";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(colorString.getBytes());
        TerminalInitializer colorInitializer = new TerminalInitializer(inputStream, System.out);
        PlayerColor chosenColor = colorInitializer.choosePlayerColor();

        assertEquals(PlayerColor.MAGENTA, chosenColor);
    }

    @Test
    public void testChoosePlayerColorStrokeCasing() {
        String colorString = "yElLoW";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(colorString.getBytes());
        TerminalInitializer colorInitializer = new TerminalInitializer(inputStream, System.out);
        PlayerColor chosenColor = colorInitializer.choosePlayerColor();

        assertEquals(PlayerColor.YELLOW, chosenColor);
    }

    @Test
    public void testChoosePlayerColorRejectWhite() {
        String colorString = "white\nOrange";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(colorString.getBytes());
        TerminalInitializer colorInitializer = new TerminalInitializer(inputStream, System.out);
        PlayerColor chosenColor = colorInitializer.choosePlayerColor();

        assertEquals(PlayerColor.ORANGE, chosenColor);
    }

    @Test
    public void testChoosePlayerColorRejectBlack() {
        String colorString = "BLACK\ngreen";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(colorString.getBytes());
        TerminalInitializer colorInitializer = new TerminalInitializer(inputStream, System.out);
        PlayerColor chosenColor = colorInitializer.choosePlayerColor();

        assertEquals(PlayerColor.GREEN, chosenColor);
    }

    @Test
    public void testChoosePlayerColorRejectNonSense() {
        String colorString = "uisuasudf\nMAGENTA";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(colorString.getBytes());
        TerminalInitializer colorInitializer = new TerminalInitializer(inputStream, System.out);
        PlayerColor chosenColor = colorInitializer.choosePlayerColor();

        assertEquals(PlayerColor.MAGENTA, chosenColor);
    }
}
