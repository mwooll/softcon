package game;

import die.DiceCombo;

import java.util.List;

public interface InputParser {

    /**
     * A class implementing this interface must be able to handle all communication with the human players
     */

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
