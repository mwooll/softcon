package cell;

import java.util.ArrayList;
import java.util.Iterator;

public class Grid implements Iterator<Cell> {
    private final int aWidth;
    private final int aHeight;

    public Grid(int pWidth, int pHeight) {
        aWidth = pWidth;
        aHeight = pHeight;
    }

    public int getWidth() { return aWidth; }
    public int getHeight() { return aHeight; }

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
