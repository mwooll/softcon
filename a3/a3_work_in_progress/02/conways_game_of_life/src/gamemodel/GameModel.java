package gamemodel;

import cell.*;

import gui.IContinue;
import gui.IGridObserver;
import gui.IPlayerObserver;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class GameModel implements GameModelView {

    private final int MIN_SIZE = 10;
    private final int MAX_SIZE = 50;
    private final int N_PLAYERS = 2;
    private final List<String> aPlayers = Arrays.asList(new String[N_PLAYERS]);
    private final List<Color> aColors = Arrays.asList(new Color[N_PLAYERS]);
    private Grid aGrid;
    private Grid aInitialGrid;
    private int aGridH = -1;
    private int aGridW = -1;
    private final List<IPlayerObserver> aPlayersObservers = new ArrayList<>();
    private final List<IGridObserver> aGridObservers = new ArrayList<>();
    private final List<IContinue> aContinueObservers = new ArrayList<>();

    public GameModel() {

        for (int i = 0; i < N_PLAYERS; i++) {aPlayers.set(i, "");}
    }

    /**
     * todo: Remove that, only for debugging
     * Assign a grid to aGrid
     */
    public void setInitialGrid(Grid pGrid) {

        aInitialGrid = pGrid;

        // Set an observer for each cell


    }

    public void addPlayersObserver(IPlayerObserver pObserver) {
        aPlayersObservers.add(pObserver);
    }

    public void addGridObserver(IGridObserver pObserver) {
        aGridObservers.add(pObserver);
    }

    public void addContinueObserver(IContinue pObserver) {
        aContinueObservers.add(pObserver);
    }

    /**
     * Check if the name pPlayerName for player at pIndex can be set
     * @param pPlayerName The string of the player name to be set
     * @param pIndex Which of the players the new name is for
     * @return True if the name can be set at pIndex, false otherwise
     */
    public boolean checkPlayerName(String pPlayerName, int pIndex) {

        assert pPlayerName != null;

        // if index out of bounds, return false
        if (pIndex < 0 || pIndex > N_PLAYERS) {return false;}

        // for each playerName not the one at index pIndex, check if its equal to the new playerName
        for (int i = 0; i < N_PLAYERS; i++) {
            if (pIndex != i) {
                if (aPlayers.get(i) != null && aPlayers.get(i).equals(pPlayerName)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Set the name of a player at pIndex
     * Assumes that no other player uses that exact name already
     * Inform aPlayersObservers and aContinueObservers
     * @param pPlayerName The string of the player name to be set
     * @param pIndex Which of the players the new name is for
     */
    @Override
    public void setPlayerName(String pPlayerName, int pIndex) {

        assert pIndex >= 0 && pIndex <= N_PLAYERS;
        assert checkPlayerName(pPlayerName, pIndex);

        System.out.println(String.format("Setting name Player %s to %s", pIndex+1, pPlayerName));

        aPlayers.set(pIndex, pPlayerName);
        for (IPlayerObserver observer : aPlayersObservers) {
            observer.nameIsSet(pPlayerName, pIndex);
        }
        for (IContinue observer : aContinueObservers) {
            observer.setVisibility();
        }

        System.out.println(aPlayers);
    }

    /**
     * Set the color of a player with name pPlayerName
     * @pre Assumes the String pPlayerName to be present exactly once in the list aPlayers
     * Inform aPlayersObservers
     */
    public void setPlayerColor(Color pColor, String pPlayerName) {

        assert aPlayers.contains(pPlayerName) && aPlayers.stream().filter(name -> name.equals(pPlayerName)).count() == 1;

        int tmpIndex = aPlayers.indexOf(pPlayerName);
        aColors.set(tmpIndex, pColor);
        for (IPlayerObserver observer : aPlayersObservers) {
            observer.colorIsSet(null, pPlayerName);
        }
        for (IContinue observer : aContinueObservers) {
            observer.setVisibility();
        }

        System.out.println(String.format("Setting color Player %s to %s", pPlayerName, pColor));

        System.out.println(aColors);

    }

    /**
     * Set the grids height and width, depending on the identifier
     * Inform aGridObservers
     * @param pValue The Value to be set
     * @param pIdentifier The String that identifies which type of grid parameter is set
     */
    @Override
    public void setGridDimension(int pValue, String pIdentifier) {
        if (pIdentifier.equals("H")) {
            aGridH = pValue;
            for (IGridObserver observer : aGridObservers) {
                observer.heightIsSet(pValue);
            }
            for (IContinue observer : aContinueObservers) {
                observer.setVisibility();
            }

            System.out.println(String.format("Set height to %s", aGridH));

        }

        if (pIdentifier.equals("W")) {
            aGridW = pValue;
            for (IGridObserver observer : aGridObservers) {
                observer.widthIsSet(pValue);
            }
            for (IContinue observer : aContinueObservers) {
                observer.setVisibility();
            }

            System.out.println(String.format("Set width to %s", aGridW));

        }

    }

    /**
     * Check if all players have a distinct name
     * @return True if all players have a name different from "", false otherwise
     */
    @Override
    public boolean playerNamesSet() {
        return !(aPlayers.contains(null) || aPlayers.contains(""));
    }

    /**
     * Check if all players have a distinct color
     * @return True if all players have a distinct color not null, false otherwise
     */
    @Override
    public boolean playerColorSet() {
        HashSet<Color> tmpColorHashSet = new HashSet<>(aColors);

        return tmpColorHashSet.size() == aColors.size() && !aColors.contains(null) && !aColors.contains(Color.WHITE);

    }

    /**
     * Check if both height and width are set to valid values
     * @return True if both height and width are set valid, false otherwise
     */
    @Override
    public boolean gridSizeSet() {
        return (aGridH >= MIN_SIZE && aGridH <= MAX_SIZE) && (aGridW >= MIN_SIZE && aGridW <= MAX_SIZE);
    }

    /**
     * Return an unmodifiable list of the current player names
     * @return Unmodifiable list of strings
     */
    public List<String> getPlayerNames() {
        return aPlayers.stream().collect(Collectors.toUnmodifiableList());
    }

    /**
     * Return the current MIN and MAX GridSize
     * @return pValue Integer either MIN or MAX
     */
    public int getMinGridSize() {return MIN_SIZE;}
    public int getMaxGridSize() {return MAX_SIZE;}

}
