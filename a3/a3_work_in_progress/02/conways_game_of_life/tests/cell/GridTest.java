package cell;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import player.PlayerColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        // Assert that Cell at (1,1) is of default PlayerColor.WHITE
        assertEquals(PlayerColor.WHITE, testGrid.getCell(1,1).getState());

        // Set Cell at (1,1) to RED
        testGrid.getCell(1,1).instantBirth(PlayerColor.RED);

        assertEquals(PlayerColor.RED, testGrid.getCell(1,1).getState());

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
        testGrid.getCell(1,1).instantBirth(PlayerColor.RED);

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
        testGrid.getCell(1,1).instantBirth(PlayerColor.RED);

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

    @Test
    public void testGenerateNextGeneration() {
        int testWidth = 3;
        int testHeight = 3;
        Grid testGrid = new Grid(testWidth, testHeight);
        testGrid.getCell(0, 0).instantBirth(PlayerColor.RED);
        testGrid.getCell(1, 0).instantBirth(PlayerColor.RED);
        testGrid.getCell(0, 1).instantBirth(PlayerColor.RED);

        testGrid.generateNextGeneration();


        PlayerColor middleMiddleColor = testGrid.getCell(1, 1).getState();
        assertEquals(PlayerColor.RED, middleMiddleColor);

        PlayerColor upperLeftColor = testGrid.getCell(0, 0).getState();
        assertEquals(PlayerColor.RED, upperLeftColor);

        PlayerColor lowerLeftColor = testGrid.getCell(2, 0).getState();
        assertEquals(PlayerColor.WHITE, lowerLeftColor);
    }
    @Test
    public void testGetNeighbours1x1() {
        int testWidth = 1;
        int testHeight = 1;
        Grid testGrid = new Grid(testWidth, testHeight);

        ArrayList<Cell> neighbors = testGrid.getNeighbors(0, 0);
        assertEquals(0, neighbors.size());
    }

    @Test
    public void testGetNeighbours2x2() {
        int testWidth = 2;
        int testHeight = 2;
        Grid testGrid = new Grid(testWidth, testHeight);

        ArrayList<Cell> upperLeft = testGrid.getNeighbors(0, 0);
        assertEquals(3, upperLeft.size());

        ArrayList<Cell> upperRight = testGrid.getNeighbors(0, 1);
        assertEquals(3, upperRight.size());

        ArrayList<Cell> lowerLeft = testGrid.getNeighbors(1, 0);
        assertEquals(3, lowerLeft.size());

        ArrayList<Cell> lowerRight = testGrid.getNeighbors(1, 1);
        assertEquals(3, lowerRight.size());
    }

    @Test
    public void testGetNeighbours3x3() {
        int testWidth = 3;
        int testHeight = 3;
        Grid testGrid = new Grid(testWidth, testHeight);

        ArrayList<Cell> upperLeft = testGrid.getNeighbors(0, 0);
        assertEquals(3, upperLeft.size());

        ArrayList<Cell> middleLeft = testGrid.getNeighbors(1, 0);
        assertEquals(5, middleLeft.size());

        ArrayList<Cell> middleRight = testGrid.getNeighbors(1, 2);
        assertEquals(5, middleRight.size());

        ArrayList<Cell> upperMiddle = testGrid.getNeighbors(0, 1);
        assertEquals(5, upperMiddle.size());

        ArrayList<Cell> lowerMiddle = testGrid.getNeighbors(2, 1);
        assertEquals(5, lowerMiddle.size());

        ArrayList<Cell> middleMiddle = testGrid.getNeighbors(1, 1);
        assertEquals(8, middleMiddle.size());
    }

    @Test
    public void testGenerateNextGeneration1x1() {
        int testWidth = 1;
        int testHeight = 1;
        Grid testGrid = new Grid(testWidth, testHeight);

        Cell testCell = testGrid.getCell(0, 0);

        testCell.instantBirth(PlayerColor.MAGENTA);
        testGrid.generateNextGeneration();
        assertEquals(PlayerColor.WHITE, testCell.getState());

        testCell.arrive(PlayerColor.YELLOW);
        testGrid.generateNextGeneration();
        assertEquals(PlayerColor.WHITE, testCell.getState());
    }

    @Test
    public void testGenerateNextGeneration3x3SingelCell() {
        int testWidth = 3;
        int testHeight = 3;
        Grid testGrid = new Grid(testWidth, testHeight);

        Cell testCell = testGrid.getCell(1, 1);

        testCell.instantBirth(PlayerColor.MAGENTA);
        testGrid.generateNextGeneration();
        assertEquals(PlayerColor.WHITE, testCell.getState());
    }

    @Test
    public void testGenerateNextGeneration3x3Mulitiple() {
        int testWidth = 3;
        int testHeight = 3;
        Grid testGrid = new Grid(testWidth, testHeight);

        /*
        making this arrangement:
            b _ y
            _ b _
            b _ y
         */
        testGrid.getCell(0, 0).instantBirth(PlayerColor.BLUE);
        testGrid.getCell(1, 1).instantBirth(PlayerColor.BLUE);
        testGrid.getCell(2, 0).instantBirth(PlayerColor.BLUE);
        testGrid.getCell(0, 2).instantBirth(PlayerColor.YELLOW);
        testGrid.getCell(2, 2).instantBirth(PlayerColor.YELLOW);

        // next generation should look like:
        //     _ b _
        //     b _ y
        //     _ b _
        // which is a stable configuration, since every cell has exactly 2 neighbors.
        ArrayList<PlayerColor> expectedColors = new ArrayList<>(
                Arrays.asList(
                        PlayerColor.WHITE, PlayerColor.BLUE, PlayerColor.WHITE,
                        PlayerColor.BLUE, PlayerColor.WHITE, PlayerColor.YELLOW,
                        PlayerColor.WHITE, PlayerColor.BLUE, PlayerColor.WHITE
                )
        );

        testGrid.generateNextGeneration();
        ArrayList<PlayerColor> actualColorsSecondGen = new ArrayList<>();
        for (Cell cell : testGrid.getIterator()) {
            actualColorsSecondGen.add(cell.getState());
        }
        assertEquals(expectedColors, actualColorsSecondGen);

        // since the configuration is stable it should not change going forwards
        testGrid.generateNextGeneration();
        ArrayList<PlayerColor> actualColorsThirdGen = new ArrayList<>();
        for (Cell cell : testGrid.getIterator()) {
            actualColorsThirdGen.add(cell.getState());
        }
        assertEquals(expectedColors, actualColorsThirdGen);


        // killing any cell should lead to a demise
        // we hence create:
        //     _ b _
        //     _ _ y
        //     _ b _
        testGrid.getCell(1, 0).instantDeath();

        // now the next generation should look like:
        //     _ _ _
        //     _ b y
        //     _ _ _
        ArrayList<PlayerColor> newExpectedColors = new ArrayList<>(
                Arrays.asList(
                        PlayerColor.WHITE, PlayerColor.WHITE, PlayerColor.WHITE,
                        PlayerColor.WHITE, PlayerColor.BLUE, PlayerColor.YELLOW,
                        PlayerColor.WHITE, PlayerColor.WHITE, PlayerColor.WHITE
                )
        );

        testGrid.generateNextGeneration();
        ArrayList<PlayerColor> actualColorsNextGen = new ArrayList<>();
        for (Cell cell : testGrid.getIterator()) {
            actualColorsNextGen.add(cell.getState());
        }
        assertEquals(newExpectedColors, actualColorsNextGen);

        // since we now have only 2 cells remaining they should both die
        ArrayList<PlayerColor> finalExpectedColors = new ArrayList<>(
                Arrays.asList(
                        PlayerColor.WHITE, PlayerColor.WHITE, PlayerColor.WHITE,
                        PlayerColor.WHITE, PlayerColor.WHITE, PlayerColor.WHITE,
                        PlayerColor.WHITE, PlayerColor.WHITE, PlayerColor.WHITE
                )
        );

        testGrid.generateNextGeneration();
        ArrayList<PlayerColor> actualColorsDeadGen = new ArrayList<>();
        for (Cell cell : testGrid.getIterator()) {
            actualColorsDeadGen.add(cell.getState());
        }
        assertEquals(finalExpectedColors, actualColorsDeadGen);
    }
}
