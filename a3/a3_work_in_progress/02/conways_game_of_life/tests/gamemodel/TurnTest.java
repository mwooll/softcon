package gamemodel;

import cell.Grid;
import cell.Cell;
import player.Player;
import player.PlayerColor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TurnTest {
    @Test
    public void testConstructorAndGetters() {
        Player testPlayer = new Player("TestName", PlayerColor.MAGENTA);
        Grid testGrid = new Grid(5, 5);
        Turn testTurn = new Turn(testPlayer, testGrid);

        assertEquals(testPlayer, testTurn.returnCurrentPlayer());
    }

    @Test
    public void testMakeBirthMove() {
        Player testPlayer = new Player("TestName", PlayerColor.MAGENTA);
        Grid testGrid = new Grid(5, 5);
        Turn testTurn = new Turn(testPlayer, testGrid);
        Cell testCell = testGrid.getCell(2, 2);

        testTurn.makeBirthMove(testCell);
        assertEquals(PlayerColor.MAGENTA, testCell.getState());
    }

    @Test
    public void testMakeDeleteMove() {
        Player testPlayer = new Player("TestName", PlayerColor.MAGENTA);
        Grid testGrid = new Grid(5, 5);
        Turn testTurn = new Turn(testPlayer, testGrid);
        Cell testCell = testGrid.getCell(2, 2);

        testTurn.makeBirthMove(testCell);
        testTurn.makeDeleteMove(testCell);
        assertEquals(PlayerColor.WHITE, testCell.getState());
    }
}
