package gamemodel;

import cell.Cell;
import cell.Grid;
import gui.ITurnObserver;
import move.Moves;
import player.Player;

import java.util.ArrayList;
import java.util.List;

public class Turn implements ICellSetterObserver, ITurnObservable {

    private int aCurrentTurnNumber;
    private Player aCurrentPlayer;
    private Grid aCurrentGrid;
    private boolean aCellDeleted;
    private boolean aCellCreated;
    private final List<ITurnObserver> aObservers = new ArrayList<>();

    public Turn(Player pPlayer, Grid pGrid, int pNumber) {
        aCurrentTurnNumber = pNumber;
        aCurrentPlayer = pPlayer;
        aCurrentGrid = pGrid;

        // Those field determine if a cell has been selected for deletion/creation yet
        aCellDeleted = false;
        aCellCreated = false;
    }

    /**
     * Refresh a turn instance to start a new turn
     * @param pPlayer
     * @param pGrid
     * @param pNumber
     */
    public void refreshTurn(Player pPlayer, Grid pGrid, int pNumber) {
        aCurrentPlayer = pPlayer;
        aCurrentGrid = pGrid;
        aCurrentTurnNumber = pNumber;

        aCellDeleted = false;
        aCellCreated = false;

        notifyObservers();

    }

    public Player returnCurrentPlayer() {
        return aCurrentPlayer;
    }
    public Grid returnCurrentGrid() {return aCurrentGrid;}
    public int returnCurrentTurnNumber() {return aCurrentTurnNumber;}

    public int countCellsAlive() {
        int nCellsAlive = 0;
        for (Cell c : aCurrentGrid.getIterator()) {
            if (c.getState() == aCurrentPlayer.getColor()) {nCellsAlive += 1;}
        }
        return nCellsAlive;
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
