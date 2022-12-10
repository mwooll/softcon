package gamemodel;

import gui.IPlayerObserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class GameModel implements GameModelView {

    public final int N_PLAYERS = 2;
    private final List<String> aPlayers = Arrays.asList(new String[N_PLAYERS]);
    private final List<IPlayerObserver> aPlayersObservers = new ArrayList<>();

    public GameModel() {

        for (int i = 0; i < N_PLAYERS; i++) {aPlayers.set(i, "");}
    }

    public void addPlayersObservers(IPlayerObserver pObserver) {
        aPlayersObservers.add(pObserver);
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

        System.out.println(aPlayers);
    }

    /**
     * Check if all players have a distinct name
     * @return True if all players have a name different from "", false otherwise
     */
    @Override
    public boolean allPlayersSet() {
        return !(aPlayers.contains(null) || aPlayers.contains(""));
    }

}
