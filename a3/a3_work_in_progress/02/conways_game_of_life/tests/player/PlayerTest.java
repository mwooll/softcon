package player;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    @Test
    public void testSortingNames() {

        Player p1 = new Player("bill", PlayerColor.BLUE);
        Player p2 = new Player("adam", PlayerColor.RED);

        List<Player> expected = Arrays.asList(p2, p1);

        List<Player> testPlayers = Arrays.asList(p1, p2);

        Collections.sort(testPlayers, Player.nameComparator());

        // assert same ordering of players
        assertEquals(expected, testPlayers);

    }

}
