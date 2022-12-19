package initializer;

import player.PlayerColor;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TerminalInitializerTest {


    @Test
    public void testChoosePlayerNameOneTry() {
        String testName = "Tester";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testName.getBytes());
        TerminalInitializer nameInitializer = new TerminalInitializer(inputStream, System.out);
        String playerName = nameInitializer.choosePlayerName();

        assertEquals(testName, playerName);
    }

    @Test
    public void testChoosePlayerNameTwoTries() {
        String badName = "%badName";
        String testName = "Tester";
        String testStream =  badName + "\n" + testName;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testStream.getBytes());
        TerminalInitializer nameInitializer = new TerminalInitializer(inputStream, System.out);
        String playerName = nameInitializer.choosePlayerName();

        assertEquals(testName, playerName);
    }

    @Test
    public void testChoosePlayerColorRed() {
        String redString = "Red";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(redString.getBytes());
        TerminalInitializer colorInitializer = new TerminalInitializer(inputStream, System.out);
        PlayerColor redColor = colorInitializer.choosePlayerColor();

        assertEquals(PlayerColor.RED, redColor);
    }

    @Test
    public void testChoosePlayerColorBlue() {
        String blueString = "Blue";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(blueString.getBytes());
        TerminalInitializer colorInitializer = new TerminalInitializer(inputStream, System.out);
        PlayerColor blueColor = colorInitializer.choosePlayerColor();

        assertEquals(PlayerColor.BLUE, blueColor);
    }
}
