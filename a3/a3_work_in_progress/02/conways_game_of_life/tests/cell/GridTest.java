package cell;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    @Test
    public void testConstructorAndGetters() {
        int testWidth = 20;
        int testHeight = 30;
        Grid testGrid = new Grid(testWidth, testHeight);
        assertEquals(testWidth, testGrid.getWidth());
        assertEquals(testHeight, testGrid.getHeight());
    }

    @Test
    public void testGetCell() {
        int testWidth = 3;
        int testHeight = 2;
        Grid testGrid = new Grid(testWidth, testHeight);

        // Assert that Cell at (1,1) is of default player.PlayerColor.WHITE
        assertEquals(player.PlayerColor.WHITE, testGrid.getCell(1,1).getState());

        // Set Cell at (1,1) to RED
        testGrid.getCell(1,1).instantBirth(player.PlayerColor.RED);

        assertEquals(player.PlayerColor.RED, testGrid.getCell(1,1).getState());

    }

    @Test
    public void testGetCellOutOfBounds() {
        int testWidth = 3;
        int testHeight = 2;
        Grid testGrid = new Grid(testWidth, testHeight);

        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            public void execute() throws Throwable {
                testGrid.getCell(1,42);
            }
        });

    }
}
