package cell;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


//import static java.lang.Math.min;
import static java.lang.Math.max;

public class Grid implements Iterator<Cell> {
    private final int aWidth;
    private final int aHeight;
    private int aListIterator = 0;
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
    /**
     * @pre valid coordinates of a cell
     * @return an ArrayList of all Neighbors of the cell
     */
    public ArrayList<Cell> getNeighbors(int aCellHeight, int aCellWidth) {
        ArrayList<Cell> aNeighbors = new ArrayList<Cell>();
        if (aCellHeight != 0) {
            for (int i = max(0, aCellWidth - 1); i <= max(aWidth - 1, aCellWidth + 1); i++) {
                aNeighbors.add(aGrid.get(aCellHeight-1).get(i));
            }
        }
        if (aCellHeight != aHeight-1){
            for (int i = max(0, aCellWidth - 1); i <= max(aWidth - 1, aCellWidth + 1); i++) {
                aNeighbors.add(aGrid.get(aCellHeight+1).get(i));
            }
        }
        if (aCellWidth > 0){
            aNeighbors.add(aGrid.get(aCellHeight).get(aCellWidth-1));
        }
        if (aCellWidth < 2*aWidth - 1){
            aNeighbors.add(aGrid.get(aCellHeight).get(aCellWidth+1));
        }
        return aNeighbors;
    }

    @Override
    public boolean hasNext() {
        return aListIterator < aWidth * aHeight - 1;
    }

    @Override
    public Cell next() {
        aListIterator += 1;
        return aGrid.get(aListIterator / aHeight).get(aListIterator % aHeight);
    }
}
