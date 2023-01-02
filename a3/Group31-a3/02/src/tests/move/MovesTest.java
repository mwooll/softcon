package move;

import cell.Cell;
import player.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MovesTest {
    Player redPlayer = new Player("redPLayer", PlayerColor.RED);
    Player bluePlayer = new Player("bluePlayer", PlayerColor.BLUE);

    @Test
    public void testCanCreateCellWhite() {
        Cell testCell = new Cell();
        assertTrue(Moves.canCreateCell(testCell));
    }

    @Test
    public void testCreateCell() {
        Cell testCell = new Cell();
        Moves.createCell(redPlayer, testCell);
        assertEquals(PlayerColor.RED, testCell.getState());
    }

    @Test
    public void testCanCreateCellNotWhite() {
        Cell testCell = new Cell();
        Moves.createCell(redPlayer, testCell);
        assertFalse(Moves.canCreateCell(testCell));
    }

    @Test
    public void testCanDeleteCellWhite() {
        Cell testCell = new Cell();
        assertFalse(Moves.canDeleteCell(redPlayer, testCell));
    }

    @Test
    public void testCanDeleteCellSameColor() {
        Cell testCell = new Cell();
        Moves.createCell(redPlayer, testCell);
        assertFalse(Moves.canDeleteCell(redPlayer, testCell));
    }

    @Test
    public void testCanDeleteCellDifferentColor() {
        Cell testCell = new Cell();
        Moves.createCell(redPlayer, testCell);
        assertTrue(Moves.canDeleteCell(bluePlayer, testCell));
    }

    @Test
    public void testDeleteCell() {
        Cell testCell = new Cell();
        Moves.createCell(redPlayer, testCell);
        Moves.deleteCell(testCell);
        assertEquals(PlayerColor.WHITE, testCell.getState());
    }

}
