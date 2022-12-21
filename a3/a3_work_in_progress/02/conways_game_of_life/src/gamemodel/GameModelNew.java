package gamemodel;

import cell.Cell;
import cell.Grid;
import gui.ICellObserver;
import initializer.GUIInitializer;
import parser.IParser;
import player.Player;

import java.util.ArrayList;
import java.util.List;

public class GameModelNew {

    public final int aHeight;
    public final int aWidth;
    private List<Player> aPlayers = new ArrayList<>();
    public final Grid aGrid;
    private final IParser aParser;

    private int aCurrentTurnIndexPlayer;
    private Turn aCurrentTurn;

    private List<ICellObserver> aCellObservers;

    public GameModelNew (GUIInitializer pInitializer, IParser pParser) {

        // Get information from the initializer regarding ...

        // A List<Player> with the Players with the names and colours set
        aPlayers.addAll(pInitializer.getPlayers());

        // A Grid of the chosen dimensions with the defined cells activated (symmetric for both players)
        aGrid = pInitializer.getGrid();

        // A Parser which validates input during the game
        aParser = pParser;

        // From the received Grid, set the dimension (needed for cell calls maybe?)
        aHeight = aGrid.getHeight();
        aWidth = aGrid.getWidth();

        // Determine the turn of which player it is. Default the first one
        // todo: Must be in alphabetical order
        aCurrentTurnIndexPlayer = 0;

    }

    public void startGame() {

    }

    public void playTurn() {
        aCurrentTurn = new Turn(aPlayers.get(aCurrentTurnIndexPlayer), aGrid);
        if (aCurrentTurnIndexPlayer == 0) {
            aCurrentTurnIndexPlayer = 1;
        } else {
            aCurrentTurnIndexPlayer = 0;
        }
    }

    public Turn getCurrentTurn() {
        return aCurrentTurn;
    }

}
