package gamemodel;

import cell.Cell;
import cell.Grid;
import move.Moves;
import player.Player;

public class Turn implements ITurnObserver {

    private final Player aCurrentPlayer;
    private final Grid aCurrentGrid;

    public Turn(Player pPlayer, Grid pGrid) {
        aCurrentPlayer = pPlayer;
        aCurrentGrid = pGrid;
    }

    public boolean hasWon() {

        // Check with grid of aCurrentPlayer if he has any cells left

        return false;
    }

    public void updateGrid() {
        // is this needed? The moves operations operate directly on the Cells
    }

    @Override
    public Player returnCurrentPlayer() {
        return aCurrentPlayer;
    }

    @Override
    public void makeBirthMove(Cell pCell) {
        Moves.createCell(aCurrentPlayer, pCell);
    }

    @Override
    public void makeDeleteMove(Cell pCell) {
        Moves.deleteCell(pCell);
    }

}
