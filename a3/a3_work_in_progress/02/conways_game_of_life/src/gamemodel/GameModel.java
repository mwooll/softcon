package gamemodel;

import cell.Cell;
import cell.Grid;
import gui.IGameModelObserver;
import initializer.GUIInitializer;
import move.Moves;
import parser.IParser;
import player.Player;
import player.PlayerColor;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements IGameModelObservable, ICellSetterObserver {

    public final int aHeight;
    public final int aWidth;
    private List<Player> aPlayers = new ArrayList<>();
    private Player aCurrentPlayer;
    private int aTurnNumber;
    private final Grid aGrid;

    private boolean aCellDeleted;
    private boolean aCellCreated;
    private final IParser aParser;

    private final List<IGameModelObserver> aObservers = new ArrayList<>();

    public GameModel(GUIInitializer pInitializer, IParser pParser) {

        // Get information from the initializer regarding ...

        // A List<Player> with the Players with the names and colours set, sort by name
        aPlayers.addAll(pInitializer.getPlayers());
        aPlayers.sort(Player.nameComparator());

        // A Grid of the chosen dimensions with the defined cells activated (symmetric for both players)
        aGrid = pInitializer.getGrid();

        // A Parser which validates input during the game
        aParser = pParser;

        // From the received Grid, set the dimension, needed for the GUI drawing
        aHeight = aGrid.getHeight();
        aWidth = aGrid.getWidth();

        // Initialize the turn count to 0
        aTurnNumber = 0;

        // Initialize the flags if cell has been deleted/created to false
        aCellDeleted = false;
        aCellCreated = false;

    }

    /**
     * Check if any player has lost
     * @return True if any number of players have no cells left, false otherwise
     */
    public boolean hasAPlayerLost() {
        for (Player p : aPlayers) {
            if (playerHasLost(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param pPlayer for which player to check if he has lost
     * @return True if Player pPlayer has lost, false otherwise
     */
    private boolean playerHasLost(Player pPlayer) {
        PlayerColor playerColor = pPlayer.getColor();
        return aGrid.getNumberOfMatchingCells(playerColor) == 0;
    }

    /**
     * Determine the winner of the game. Either one or both player have no cells left
     */
    public String determineWinner() {
        int firstPlayerCellsLeft = aGrid.getNumberOfMatchingCells(aPlayers.get(0).getColor());
        int secondPlayerCellsLeft = aGrid.getNumberOfMatchingCells(aPlayers.get(1).getColor());

        if (firstPlayerCellsLeft == 0 && secondPlayerCellsLeft == 0) {
            return "It's a tie, both players lost.";
        } else if (secondPlayerCellsLeft > 0) {
            Player firstPlayer = aPlayers.get(0);
            return String.format("Player 1 - %s with Color %s - lost.", firstPlayer.getName(), firstPlayer.getColor().getColorName());
        } else {
            Player secondPlayer = aPlayers.get(1);
            return String.format("Player 2 - %s with Color %s - lost.", secondPlayer.getName(), secondPlayer.getColor().getColorName());
        }
    }



    public Grid returnGrid() {return aGrid;}

    @Override
    public Player returnCurrentPlayer() {
        return aCurrentPlayer;
    }
    public int returnCurrentTurnNumber() {return aTurnNumber;}



    public void playTurn() {

        // determine the current player, by turn number
        aCurrentPlayer = aPlayers.get(aTurnNumber%2);

        // reset the flags
        aCellDeleted = false;
        aCellCreated = false;

        // notify the observers
        notifyObservers();

        aTurnNumber += 1;

    }




    @Override
    public void addObserver(IGameModelObserver pObserver) {
        aObservers.add(pObserver);
    }

    @Override
    public boolean getCurrentPlayerHasChanged() {
        return false;
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
            for (IGameModelObserver observer : aObservers) {
                observer.currentPlayerChanged();
                observer.stateCanDeleteChanged();
                observer.stateCanCreateChanged();
            }
        }
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
}
