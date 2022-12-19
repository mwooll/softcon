package initializer;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TerminalInitializerTest {
    TerminalInitializer testInitializer = new TerminalInitializer();

    @Test
    public void testChoosePlayerName() {
        String testName = "Tester";
        String playerName = testInitializer.choosePlayerName();
        System.setIn(new ByteArrayInputStream(testName.getBytes()));

        assertEquals(testName, playerName);
    }
}
