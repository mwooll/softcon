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

    @Test
    public void testEmptyConstructor() {
        Player testPlayer = new Player();
        assertEquals("", testPlayer.getName());
        assertEquals(PlayerColor.WHITE, testPlayer.getColor());
    }

    @Test
    public void testSetName() {
        Player testPlayer = new Player();
        testPlayer.setName("TestName");
        assertEquals("TestName", testPlayer.getName());
    }

    @Test
    public void testSetColor() {
        Player testPlayer = new Player();
        testPlayer.setColor(PlayerColor.GREEN);
        assertEquals(PlayerColor.GREEN, testPlayer.getColor());
    }
}
