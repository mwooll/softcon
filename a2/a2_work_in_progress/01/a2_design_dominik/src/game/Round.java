package game;

import die.*;
import ruleset.*;

import java.util.ArrayList;
import java.util.List;

public class Round {

    /**
     * Represents a part of a players turn, one fresh DiceSet and one current Ruleset
     * Ends either when a Tutto happens or when a NULL is rolled
     */

    private boolean aNull = false;
    private int aScore = 0;
    private final InputParser aParser = new DebugParser();
    private final Ruleset aCurrentRuleset;
    private final DiceSet aDiceSet;
    private List<DiceCombo> aRolledDiceCombos;
    private List<DiceCombo> aRemovableDiceCombos;

    /**
     * Constructor initializes fresh DiceSet and takes a Ruleset as given
     * @pre valid Ruleset
     */
    public Round(Ruleset pRuleset) {

        assert pRuleset != null;

//        aDiceSet = DiceSet.get();
        aDiceSet = DiceSet.getDebug();

        // Always refresh the dice set at the initialization of a new round
        aDiceSet.refresh();

        // Set the current Ruleset
        aCurrentRuleset = pRuleset;

        // Calculate all initially removable DiceCombos from the refreshed DiceSet
        aRemovableDiceCombos = returnRemovableDiceCombos();

        // Determine if first throw was a null roll
        aNull = isNull();

    }

    /**
     *
     * @return An integer with the total of points
     */
    public int playRound() {

        int roundSum = 0;
        boolean isTutto = false;

        // Determine if the current roll is a null
        aNull = isNull();

        while(!aNull && !isTutto) {

            // - Tell player that he has not rolled a null and show him all possible DiceCombos
            System.out.println("Your remaining dice:");
            System.out.println(aDiceSet);

            // - Ask player if stop or continue
            boolean doContinue = aParser.askStop();
            if (!doContinue) {break;}

            // - Ask player which DiceCombo to remove, only possible if there are DiceCombos to remove
            boolean keepRemoving = true;
            while (keepRemoving && !isNull()) {
                DiceCombo toRemove = aParser.askWhichRemove(aRemovableDiceCombos);
                // Remove that DiceCombo from the DiceSet, refresh the removable DiceCombos
                for (DieValue dievalue : toRemove) {
                    aDiceSet.moveDie(dievalue);
                }
                aRemovableDiceCombos = returnRemovableDiceCombos();
                // Add the removed DiceSet to the aRolledDiceSet
                aRolledDiceCombos.add(toRemove);
                // Ask player if he wants to remove another DiceCombo
                keepRemoving = aParser.askKeepRemoving();
            }

            // - After removing is done, check if is Tutto (if there are no remaining Dice)
            // - otherwise roll remaining Dice
            if (aDiceSet.getSizeLeft() == 0) {
                isTutto = true;
                aCurrentRuleset.handleTutto();
            } else {
                aDiceSet.rollRemaining();
                aNull = isNull();
            }

        }

        // either when null or when tutto sum up the points
        roundSum += aCurrentRuleset.sumUpPoints(aRolledDiceCombos);

        // return the points
        return roundSum;
    }

    /**
     * Given the current state of the DiceSet and the Ruleset, return all possible DiceCombos which can be removed
     * @return a list with DiceCombos currently removable
     */
    private List<DiceCombo> returnRemovableDiceCombos() {
        List<DiceCombo> tmp = new ArrayList<>(aDiceSet.returnCombos());
        tmp.retainAll(aCurrentRuleset.returnValidCombos());

        return tmp;
    }

    /**
     * Given the current state of a DiceSet and the current Ruleset, determine if DiceSet is a NULL roll
     */
    private boolean isNull() {
        return returnRemovableDiceCombos().size() == 0;
    }

}
