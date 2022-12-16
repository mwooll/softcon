package cell;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grid implements Iterable<Cell> {
    private final int aWidth;
    private final int aHeight;

    private final List<List<Cell>> aGrid = new ArrayList<>();

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
     * @pre pRow and pCol must be bigger than 0
     * @return The Cell at row pRow and col pCol
     */
    public Cell getCell(int pRow, int pCol) {
        assert pRow >= 0 && pCol >= 0;
        return aGrid.get(pRow).get(pCol);
    }



    public void generateNextGeneration() {

    }

    public ArrayList<Cell> getNeighbors(Cell pCell) {
        return null;
    }

    @Override
    public Iterator<Cell> iterator() {
        return new iteratorTopLeftBottomRight();
    }

    /**
     *
     */
    class iteratorTopLeftBottomRight implements Iterator<Cell> {
        private int ctRow, ctCol = 0;
        private int ctIndex = 0;

        @Override
        public boolean hasNext() {

            // The first Cell must always be looked at
            if (ctRow == 0 && ctCol == 0) {
                return true;
            }

            int nextIndex = (ctRow * aWidth) + ctCol + 1;

            return nextIndex <= aHeight * aWidth;

        }

        @Override
        public Cell next() {

            Cell currentCell = getCell(ctRow, ctCol);

            boolean lastCol = ctCol == aWidth-1;

            // either one column to the right if possible, or otherwise reset ctCol and go one row down
            if (!lastCol) {ctCol += 1;} else {
                ctCol = 0;
                ctRow += 1;
            }

            return currentCell;

        }
    }
}
