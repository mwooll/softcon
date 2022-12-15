package cell;

import org.junit.jupiter.api.Test;
import player.PlayerColor;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    Cell aCell = new Cell();
    PlayerColor aColor = PlayerColor.RED;

    @Test
    public void testCreationAndGetters() {
        Cell testCell = new Cell();
        assertEquals(PlayerColor.WHITE, testCell.getState());
        assertFalse(testCell.hasStateChanged());
    }

    @Test
    public void testInstantBirth() {
        aCell.instantBirth(aColor);
        assertEquals(aColor, aCell.getState());
        assertFalse(aCell.hasStateChanged());
    }

    @Test
    public void testInstantDeath() {
        aCell.instantBirth(aColor);
        aCell.instantDeath();
        assertEquals(PlayerColor.WHITE, aCell.getState());
        assertFalse(aCell.hasStateChanged());
    }

    @Test
    public void testArrive() {
        aCell.arrive(aColor);
        assertEquals(PlayerColor.WHITE, aCell.getState());
        assertTrue(aCell.hasStateChanged());
    }

    @Test
    public void testDie() {
        aCell.instantBirth(aColor);
        aCell.die();
        assertEquals(aColor, aCell.getState());
        assertTrue(aCell.hasStateChanged());
    }

    @Test
    public void testResetStateChanged() {
        aCell.arrive(aColor);
        aCell.resetChangedState();
        assertFalse(aCell.hasStateChanged());
    }

    @Test
    public void testUpdateStateArrive() {
        aCell.arrive(aColor);
        aCell.updateState();
        assertEquals(aColor, aCell.getState());
        assertFalse(aCell.hasStateChanged());
    }

    @Test
    public void testUpdateStateArriveAndDie() {
        aCell.arrive(aColor);
        aCell.updateState();
        aCell.die();
        aCell.updateState();
        assertEquals(PlayerColor.WHITE, aCell.getState());
        assertFalse(aCell.hasStateChanged());
    }
}
