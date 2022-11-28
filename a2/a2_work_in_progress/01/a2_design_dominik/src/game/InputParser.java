package game;

import die.DiceCombo;

import java.util.List;

public interface InputParser {

    /**
     * A class implementing this interface must be able to handle all communication with the human players
     */

    /**
     * At the beginning of the game, ask how many players should play
     */
    int askNumberPlayers();

    /**
     * At the beginning of the game, ask for one player name, make sure its not yet in the list supplied
     * only accept alphanumeric characters
     */
    String askPlayerName(int pNumber, List<String> pForbidden);

    /**
     * At the beginning of the game, ask for the winning condition points
     */
    int askWinCondition();

    /**
     * At the beginning of the turn, ask if the player wants to see the Score or play a round
     */
    boolean askDisplayScore();

    /**
     * Asking the player if he wants to start another round after a tutto
     */
    boolean askStopAfterTutto();

    /**
     * Asking the player if he wants to start removing combos or stop his round
     */
    boolean askStop();

    /**
     * Asking the player if he wants to remove another combo
     */
    boolean askKeepRemoving();

    /**
     * Asking the player which combo he wants to remove
     * @pre pList cannot be empty
     * @param pList A list with all available DiceCombos from which the user chooses
     * @return A DiceCombo object which was chosen
     */
    DiceCombo askWhichRemove(List<DiceCombo> pList);


}
