package cell;

import player.PlayerColor;

import java.util.*;

import static java.lang.Math.min;
import static java.lang.Math.max;

public class Grid {
    private final int aWidth;
    private final int aHeight;

    private final List<List<Cell>> aGrid;
    

    public Grid(int pWidth, int pHeight) {
        aWidth = pWidth;
        aHeight = pHeight;
        aGrid = new ArrayList<>();

        // Create Cell objects for each field in the grid
        for (int row = 0; row < aHeight; row++) {
            aGrid.add(new ArrayList<>());
            for (int col = 0; col < aWidth; col++) {
                aGrid.get(row).add(new Cell());
            }
        }
    }

    /**
     * Copy Constructor
     */
    public Grid(Grid pOtherGrid) {

        aWidth = pOtherGrid.aWidth;
        aHeight = pOtherGrid.aHeight;
        aGrid = new ArrayList<>();

        // Create Cell objects for each field in the grid
        for (int row = 0; row < aHeight; row++) {
            aGrid.add(new ArrayList<>());
            for (int col = 0; col < aWidth; col++) {
                aGrid.get(row).add(pOtherGrid.getCell(row, col));
            }
        }

    }

    /**
     * Iterators must be implemented as nested classes within grid.$
     * Goal: Provide each Grid instance with a method to return an Iterator<Cell> object which can be used by
     * the client code
     * GridIteratorBase provides default functionality TOP_LEFT_TO_BOTTOM_RIGHT. Can be overridden by subclasses.
     * Base does not depend on the aGrid, only on getCell
     */
    abstract class GridIteratorBase implements Iterator<Cell>, Iterable<Cell> {

        final int maxIndex = aHeight * aWidth;
        int ctRow, ctCol = 0;

        public boolean hasNext() {

            // The first Cell must always be looked at
            if (ctRow == 0 && ctCol == 0) {
                return true;
            }

            int nextIndex = (ctRow * aWidth) + ctCol + 1;
            return nextIndex <= maxIndex;
        }

        public Cell next() {

            Cell currentCell = getCell(ctRow, ctCol);

            boolean lastCol = ctCol == aWidth-1;

            // either one column to the right if possible, or otherwise reset ctCol and go one row down
            if (!lastCol) {
                ctCol += 1;
            } else {
                ctCol = 0;
                ctRow += 1;
            }

            return currentCell;
        }

        public Iterator<Cell> iterator() {
            return this;
        }

    }

    /**
     * Method to obtain an object which implements the Iterable<Cell> Interface through the abstract class GridIteratorBase
     * @return Object which implements the Iterable<Cell> interface to loop over
     */
    public Iterable<Cell> getIterator() {
        // For the default we can use an anonymous class
        return new GridIteratorBase() {};
    }

    public class GridIteratorReversed extends GridIteratorBase {
        private final Iterator<Cell> reversedIterator;

        public GridIteratorReversed() {
            List<Cell> flatGrid = flattenGrid(aGrid);
            Collections.reverse(flatGrid);
            reversedIterator = flatGrid.iterator();
        }

        private List<Cell> flattenGrid(List<List<Cell>> pGrid) {
            List<Cell> outFlatGrid = new ArrayList<>();
            pGrid.stream().flatMap(Collection::stream).forEach(outFlatGrid::add);
            return outFlatGrid;
        }

        @Override
        public boolean hasNext() {return reversedIterator.hasNext();}

        @Override
        public Cell next() {return reversedIterator.next();}

        @Override
        public Iterator<Cell> iterator() {return reversedIterator;}

    }

    public Iterable<Cell> getIteratorReversed() {

//        // Anonymous class fails
//        new GridIteratorBase() {
//
//            // Why the fuck does that not work? Is it the anonymous class?
//            // aGrid.stream().flatMap(x -> x.stream()).forEach(cell -> flatGrid.add(cell));
//
//            // This doesnt work eiterh...
//            // List<List<Cell>> currentGrid = aGrid;
//            // currentGrid
//
//        }

        return new GridIteratorReversed();

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
        //iterating through grid
        for (int row = 0; row < aHeight; row++){
            for (int column = 0; column < aWidth; column++){
                //for each Cell make a list of non-white neighbour colors
                List<PlayerColor> neighbourColors = new ArrayList<>();
                for (Cell aCell: getNeighbors(row,column)){
                    if(aCell.isAlive()) {
                        neighbourColors.add(aCell.getState());
                    }
                }
                //update next state of every Cell
                if(getCell(row, column).isAlive()){
                    if(neighbourColors.size() != 2 && neighbourColors.size() != 3){
                        getCell(row, column).die();
                    }
                    else{
                        getCell(row, column).arrive(getCell(row, column).getState());
                    }
                }
                else{
                    if(neighbourColors.size() == 3){
                        if(neighbourColors.get(0) == neighbourColors.get(1) || neighbourColors.get(0) == neighbourColors.get(2)){
                            getCell(row, column).arrive(neighbourColors.get(0));
                        }
                        else{
                            getCell(row, column).arrive(neighbourColors.get(1));
                        }
                    }
                    else{
                        getCell(row, column).arrive(getCell(row, column).getState());
                    }
                }

            }
        }
        //update all states
        for (int row = 0; row < aHeight; row++) {
            for (int column = 0; column < aWidth; column++) {
                aGrid.get(row).get(column).updateState();
            }
        }
    }
    /**
     * @pre valid coordinates of a cell
     * @return an ArrayList of all Neighbors of the cell
     */
    public ArrayList<Cell> getNeighbors(int aCellHeight, int aCellWidth) {
        ArrayList<Cell> aNeighbors = new ArrayList<>();
        if (aCellHeight != 0) {
            for (int column = max(0, aCellWidth - 1); column <= min(aWidth - 1, aCellWidth + 1); column++) {
                aNeighbors.add(aGrid.get(aCellHeight-1).get(column));
            }
        }
        if (aCellHeight != aHeight-1){
            for (int column = max(0, aCellWidth - 1); column <= min(aWidth - 1, aCellWidth + 1); column++) {
                aNeighbors.add(aGrid.get(aCellHeight+1).get(column));
            }
        }
        if (aCellWidth > 0){
            aNeighbors.add(aGrid.get(aCellHeight).get(aCellWidth-1));
        }
        if (aCellWidth < aWidth - 1){
            aNeighbors.add(aGrid.get(aCellHeight).get(aCellWidth+1));
        }
        return aNeighbors;
    }

    public int getNumberOfColoredCells() {
        int count = 0;
        for (Cell cell : getIterator()) {
            if (cell.getState() != PlayerColor.WHITE) {
                count++;
            }
        }
        return count;
    }
}
