package cell;

import player.PlayerColor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    Cell aTestCell = new Cell();
    PlayerColor aTestColor = PlayerColor.RED;

    @Test
    public void testCreationAndGetters() {
        Cell testCell = new Cell();
        assertEquals(PlayerColor.WHITE, testCell.getState());
        assertFalse(testCell.hasStateChanged());
        assertFalse(testCell.isAlive());
    }

    @Test
    public void testInstantBirth() {
        aTestCell.instantBirth(aTestColor);
        assertEquals(aTestColor, aTestCell.getState());
        assertFalse(aTestCell.hasStateChanged());
        assertTrue(aTestCell.isAlive());
    }

    @Test
    public void testInstantDeath() {
        aTestCell.instantBirth(aTestColor);
        aTestCell.instantDeath();
        assertEquals(PlayerColor.WHITE, aTestCell.getState());
        assertFalse(aTestCell.hasStateChanged());
        assertFalse(aTestCell.isAlive());
    }

    @Test
    public void testArrive() {
        aTestCell.arrive(aTestColor);
        assertEquals(PlayerColor.WHITE, aTestCell.getState());
        assertTrue(aTestCell.hasStateChanged());
        assertFalse(aTestCell.isAlive());
    }

    @Test
    public void testDie() {
        aTestCell.instantBirth(aTestColor);
        aTestCell.die();
        assertEquals(aTestColor, aTestCell.getState());
        assertTrue(aTestCell.hasStateChanged());
        assertTrue(aTestCell.isAlive());
    }

    @Test
    public void testResetStateChanged() {
        aTestCell.arrive(aTestColor);
        aTestCell.resetChangedState();
        assertFalse(aTestCell.hasStateChanged());
    }

    @Test
    public void testUpdateStateArrive() {
        aTestCell.arrive(aTestColor);
        aTestCell.updateState();
        assertEquals(aTestColor, aTestCell.getState());
        assertFalse(aTestCell.hasStateChanged());
        assertTrue(aTestCell.isAlive());
    }

    @Test
    public void testUpdateStateArriveAndDie() {
        aTestCell.arrive(aTestColor);
        aTestCell.updateState();
        aTestCell.die();
        aTestCell.updateState();
        assertEquals(PlayerColor.WHITE, aTestCell.getState());
        assertFalse(aTestCell.hasStateChanged());
        assertFalse(aTestCell.isAlive());
    }
}
