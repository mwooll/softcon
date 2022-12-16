package cell;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grid implements Iterator<Cell> {
    private final int aWidth;
    private final int aHeight;

    private final List<List<Cell>> aGrid = new ArrayList<List<Cell>>();

    public Grid(int pWidth, int pHeight) {
        aWidth = pWidth;
        aHeight = pHeight;

        // Create Cell objects for each field in the grid
        for (int row = 0; row < aHeight; row++) {
            aGrid.add(new ArrayList<>());
            for (int col = 0; col < aWidth; col++) {
                aGrid.get(row).add(new Cell());
            }
        }

    }

    public int getWidth() { return aWidth; }
    public int getHeight() { return aHeight; }

    /**
     * 0 Indexed
     * @throws IndexOutOfBoundsException when the indices are out of bounds
     * @pre pRow and pCol must be positive
     * @return The Cell at row pRow and col pCol
     */
    public Cell getCell(int pRow, int pCol) {
        assert pRow > 0 && pCol > 0;
        return aGrid.get(pRow).get(pCol);
    }

    public void generateNextGeneration() {

    }

    public ArrayList<Cell> getNeighbors(Cell pCell) {
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Cell next() {
        return null;
    }
}
