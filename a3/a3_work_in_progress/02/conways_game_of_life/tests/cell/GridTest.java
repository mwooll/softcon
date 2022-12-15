package cell;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
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
}
