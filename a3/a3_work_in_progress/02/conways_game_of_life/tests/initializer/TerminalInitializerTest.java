package initializer;

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

    /*
    @Test
    public void testChoosePlayerColorRed() {
        String testColor = "Red";
        PlayerColor playerColor = testInitializer.choosePlayerColor();
        System.setIn(new ByteArrayInputStream((testColor.getBytes())));

        assertEquals(PlayerColor.RED, playerColor);
    }

    @Test
    public void testChoosePlayerColorBlue() {
        String testColor = "Blue";
        PlayerColor playerColor = testInitializer.choosePlayerColor();
        System.setIn(new ByteArrayInputStream((testColor.getBytes())));

        assertEquals(PlayerColor.BLUE, playerColor);
    }
    */
}
