package gamemodel;

import cell.Cell;
import cell.Grid;
import gui.ICellObserver;
import gui.ITurnObserver;
import move.Moves;
import player.Player;
import player.PlayerColor;

import java.util.ArrayList;
import java.util.List;

public class Turn implements ICellSetterObserver, ITurnObservable {

    private final Player aCurrentPlayer;
    private final Grid aCurrentGrid;
    private boolean aCellDeleted;
    private boolean aCellCreated;
    private final List<ITurnObserver> aObservers = new ArrayList<>();

    public Turn(Player pPlayer, Grid pGrid) {
        aCurrentPlayer = pPlayer;
        aCurrentGrid = pGrid;

        // Those field determine if a cell has been selected for deletion/creation yet
        aCellDeleted = false;
        aCellCreated = false;
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
        aCellCreated = true;
        Moves.createCell(aCurrentPlayer, pCell);
        notifyObservers();
    }

    @Override
    public void makeDeleteMove(Cell pCell) {
        aCellDeleted = true;
        Moves.deleteCell(pCell);
        notifyObservers();
    }

    @Override
    public void addObserver(ITurnObserver pObserver) {
        aObservers.add(pObserver);
    }

    @Override
    public boolean getStatusCellDeleted() {
        return aCellDeleted;
    }

    @Override
    public boolean getStatusCellCreated() {
        return aCellCreated;
    }

    @Override
    public void notifyObservers() {
        if (aObservers.size() > 0) {
            for (ITurnObserver observer : aObservers) {
                observer.stateCanDeleteChanged();
                observer.stateCanCreateChanged();
            }
        }
    }
}
