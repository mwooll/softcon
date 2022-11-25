package game;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Tableau {

    /**
     * A Tableau collects all players, identified by their name
     * Records for each player how many points
     * The order constitutes the order of the game, if anybody wins, the Tableau knows which players are left to play
     */

    private final HashMap<String, Integer> aTableau = new HashMap<>();

    public Tableau(){};

    /**
     * Add a new player to the Tableau
     * @pre The name cannot exist already
     * @param pPlayerName unique name of the player
     */
    public void add(String pPlayerName) {

        assert !aTableau.containsKey(pPlayerName);

        aTableau.put(pPlayerName, 0);
    }

    /**
     * Increase the Score of a player in the Tableau
     * @pre The player must be present in the Tableau
     * @pre The score to update must be positive
     * @param pPlayerName The key Player name
     * @param pScore The number of points, positive, to add to the score
     */
    public void update(String pPlayerName, int pScore) {
        assert aTableau.containsKey(pPlayerName);
        assert pScore > 0;

        aTableau.put(pPlayerName, aTableau.get(pPlayerName) + pScore);

    }

    /**
     * Decrease the score of the leading player by 1000
     * If multiple players have same score, decrease all of them
     */
    public void decrease() {
        // find max
        int maxScore = Collections.max(aTableau.values());

        // decrease each entry with max by 1000
        for (Map.Entry<String, Integer> set : aTableau.entrySet()) {
            if (set.getValue() == maxScore) {
                aTableau.put(set.getKey(), Math.max(set.getValue() - 1000, 0));
            }
        }

    }

    /**
     * Check if any of the players has already reached the necessary points to win
     * @pre Tableau cannot be empty
     * @param pCondition The needed amount of points to win
     * @return True if one or more players have reached pCondition, false otherwise
     */
    public boolean aPlayerHasWon(int pCondition) {
        assert aTableau.size() > 0;

        for (Map.Entry<String, Integer> set : aTableau.entrySet()) {
            if (set.getValue() >= pCondition) {
                return true;
            }
        }
        return false;
    }

    /**
     * Show tableau
     */
    public void printTableau() {
        for (Map.Entry<String, Integer> set : aTableau.entrySet()) {
            System.out.println(set.getKey() + " : " + set.getValue());
        }
    }

    /**
     * Announce the ranking of the players
     * In case of draws put same scores on same rank
     * @pre Tableau cannot be empty
     *
     * todo: implement properly
     */
    public void announceWinner() {
        printTableau();
    }

    /**
     * Return number of entries
     */
    public int size() {
        return aTableau.size();
    }

    /**
     * Return the value of a specific player
     * @pre Key must be in tableau
     */
    public int get(String pPlayerName) {
        assert aTableau.containsKey(pPlayerName);
        return aTableau.get(pPlayerName);
    }



}
