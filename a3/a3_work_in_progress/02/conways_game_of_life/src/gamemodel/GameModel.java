package gamemodel;

import cell.Grid;
import gui.ICellObserver;
import initializer.GUIInitializer;
import parser.IParser;
import player.Player;
import player.PlayerColor;

import java.util.ArrayList;
import java.util.List;

public class GameModel {


    public final int aHeight;
    public final int aWidth;
    private List<Player> aPlayers = new ArrayList<>();
    private int aTurnNumber = 0;
    private final Grid aGrid;
    private final IParser aParser;

    private int aCurrentTurnIndexPlayer;
    private Turn aCurrentTurn;

    public GameModel(GUIInitializer pInitializer, IParser pParser) {

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

    /**
     * @param pPlayer for which player to check if he has lost
     * @return True if Player pPlayer has lost, false otherwise
     */
    public boolean playerHasLost(Player pPlayer) {
        PlayerColor playerColor = pPlayer.getColor();
        return aGrid.getNumberOfMatchingCells(playerColor) == 0;
    }

    /**
     * Return a list of all Players
     * @return AS A LIST
     */
    public List<Player> getPlayers() {
        List<Player> tmpList = new ArrayList<>();
        aPlayers.stream().forEach(p -> tmpList.add(p));
        return tmpList;
    }

    public void playTurn() {
        // whenever CurrentTurnIndex is 0, increase by 1
        if (aCurrentTurnIndexPlayer == 0) {aTurnNumber += 1;}
        aCurrentTurn = new Turn(aPlayers.get(aCurrentTurnIndexPlayer), aGrid, aTurnNumber);
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
