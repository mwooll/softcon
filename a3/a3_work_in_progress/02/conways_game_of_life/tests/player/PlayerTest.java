package player;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    public void testConstructorAndGetters() {
        String testName = "Name";
        PlayerColor testColor = PlayerColor.BLUE;
        Player testPlayer = new Player(testName, testColor);
        assertEquals(testName, testPlayer.getName());
        assertEquals(testColor, testPlayer.getColor());
    }
}
