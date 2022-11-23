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
    private final List<DiceCombo> aRolledDiceCombos = new ArrayList<>();
    private List<DiceCombo> aRemovableDiceCombos = new ArrayList<>();

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

        // Show the initial roll
        System.out.println(aDiceSet);

    }

    /**
     *
     * @return An integer with the total of points
     */
    public int playRound() {

        int pointsTotal = 0;

        boolean isTutto = false;

        // Determine if the current roll is a null
        aNull = isNull();

        while(!aNull && !isTutto) {

            // - Tell player that he has not rolled a null and show him all possible DiceCombos
            System.out.println("Your remaining dice:");
            System.out.println(aDiceSet);

            // - Ask player if stop or continue
            if (aParser.askStop()) {break;}

            // - Ask player which DiceCombo to remove, only possible if there are DiceCombos to remove
            boolean keepRemoving = true;
            while (keepRemoving && !isNull()) {
                System.out.println("Your remaining dice:");
                System.out.println(aDiceSet);
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
            } else {
                aDiceSet.rollRemaining();
                aNull = isNull();
            }

        }

        // inform if null
        if (aNull) {
            System.out.println("Your roll was a null, calculating points and ending the turn");
        }

        // inform if Tutto
        if (isTutto) {
            System.out.println("You accomplished a Tutto, calculating points and ending the turn");
            int pointsTutto = aCurrentRuleset.handleTutto();
            System.out.println(String.format("Your Tutto scored you %s points", pointsTutto));
            pointsTotal += pointsTutto;
        }

        // either when null or when tutto sum up the points
        int pointsRound = aCurrentRuleset.sumUpPoints(aRolledDiceCombos);
        System.out.println(String.format("Your rolls scored you %s points", pointsRound));
        pointsTotal += pointsRound;

        // return the points
        System.out.println(String.format("Your total round scored you %s points", pointsTotal));
        return pointsTotal;
    }

    /**
     * Given the current state of the DiceSet and the Ruleset, return all possible DiceCombos which can be removed
     * @return a list with DiceCombos currently removable
     */
    private List<DiceCombo> returnRemovableDiceCombos() {
        List<DiceCombo> allPossibleDiceCombos = new ArrayList<>(aDiceSet.returnCombos());
        List<DiceCombo> allValidDiceCombos = aCurrentRuleset.returnValidCombos();
        List<DiceCombo> tmp = new ArrayList<>();

        for (DiceCombo dicecombo : allPossibleDiceCombos) {
            if (allValidDiceCombos.contains(dicecombo)) {
                tmp.add(dicecombo);
            }
        }

        return tmp;
    }

    /**
     * Given the current state of a DiceSet and the current Ruleset, determine if DiceSet is a NULL roll
     */
    private boolean isNull() {
        return returnRemovableDiceCombos().size() == 0;
    }

}
