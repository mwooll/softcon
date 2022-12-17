package cell;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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


    @Test
    public void testIterator() {
        int testWidth = 3;
        int testHeight = 2;
        Grid testGrid = new Grid(testWidth, testHeight);

        // Set Cell at (1,1) to RED
        testGrid.getCell(1,1).instantBirth(player.PlayerColor.RED);

        List<String> actualColors = new ArrayList<>();

        for (Cell c : testGrid.getIterator()) {
            actualColors.add(c.getState().getColorName());
        }

        assertEquals("Red", actualColors.get(4));

    }

    @Test
    public void testIteratorReversed() {
        int testWidth = 3;
        int testHeight = 2;
        Grid testGrid = new Grid(testWidth, testHeight);

        // Set Cell at (1,1) to RED
        testGrid.getCell(1,1).instantBirth(player.PlayerColor.RED);

        List<String> actualColors = new ArrayList<>();

        for (Cell c : testGrid.getIteratorReversed()) {
            actualColors.add(c.getState().getColorName());
        }

        assertEquals("Red", actualColors.get(1));

    }

    @Test
    public void testIteratorSingleElement() {

        int testWidth = 1;
        int testHeight = 1;
        Grid testGrid = new Grid(testWidth, testHeight);

        List<String> actualColors = new ArrayList<>();
        for (Cell c : testGrid.getIterator()) {
            actualColors.add(c.getState().getColorName());
        }
        assertEquals(1, actualColors.size());
        assertEquals("White", actualColors.get(0));


        // Same for reversedIterator
        List<String> actualColors2 = new ArrayList<>();
        for (Cell c : testGrid.getIteratorReversed()) {
            actualColors2.add(c.getState().getColorName());
        }
        assertEquals(1, actualColors2.size());
        assertEquals("White", actualColors2.get(0));

    }
}
