package game;

import die.*;
import ruleset.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Round {

    /**
     * Represents a part of a players turn, one fresh DiceSet and one current Ruleset
     * Ends either when a Tutto happens or when a NULL is rolled
     *
     * todo: Does Round need an int aPreviousScore? For the x2 card?
     */

    private boolean aNull = false;
    private InputParser aParser = new DefaultParser();
    private final Ruleset aCurrentRuleset;
    private DiceSet aDiceSet;
    private final List<DiceCombo> aRolledDiceCombos = new ArrayList<>();

    /**
     * Constructor initializes fresh DiceSet and takes a Ruleset as given
     * @pre valid Ruleset
     */
    public Round(Ruleset pRuleset) {

        assert pRuleset != null;

        // Default is the non debug DiceSet
        setDiceSet(DiceSet.get());

        // Default is the non debug Parser
        setParser(new DefaultParser());

        // Always refresh the dice set at the initialization of a new round
        aDiceSet.refresh();

        // Set the current Ruleset
        aCurrentRuleset = pRuleset;

        // Show the initial roll
        System.out.println(String.format("Fresh round initialized with ruleset %s", aCurrentRuleset.returnName()));
        System.out.println(aDiceSet);

    }

    /**
     * Set a InputParser for the Round instance
     */
    public void setParser(InputParser pInputParser) {
        aParser = pInputParser;
    }

    /**
     * Set a DiceSet for the Round instance
     */
    public void setDiceSet(DiceSet pDiceSet) {
        aDiceSet = pDiceSet;
    }

    /**
     * Play a round consisting of a Ruleset and a fresh set of Dice
     * @return An integer with the total of points
     */
    public int playRound() {

        // At the start of the round, refresh the DiceSet
        aDiceSet.refresh();

        int pointsTotal = 0;

        boolean isTutto = false;

        // Re Roll all Dice at the start of the Round
        aDiceSet.rollRemaining();

        // Determine the initial valid combinations
        List<DiceCombo> aRemovableDiceCombos = returnRemovableDiceCombos();

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

                // If there are more removable combos, show him and ask if he wants to remove more
                // Otherwise inform that there are no more combos to remove and the remaining dice are rerolled
                if (aRemovableDiceCombos.size() > 0) {
                    System.out.println("Your remaining dice:");
                    System.out.println(aDiceSet);
                    keepRemoving = aParser.askKeepRemoving();
                } else {
                    System.out.println("No more combinations to remove");
                }

            }

            // - After removing is done, check if is Tutto (if there are no remaining Dice)
            // - otherwise roll remaining Dice
            if (aDiceSet.getSizeLeft() == 0) {
                isTutto = true;
            } else {
                System.out.println("Re-rolling remaining dice ...");
                aDiceSet.rollRemaining();
                aNull = isNull();
            }

        }

        // inform if null
        if (aNull) {
            System.out.println("Your roll was a null, calculating points and ending the turn");
        }

        // independently of null or tutto, sum up the rolled points
        int pointsRoll = aCurrentRuleset.sumUpPoints(aRolledDiceCombos);
        System.out.println(String.format("Your rolls scored you %s points", pointsRoll));
        pointsTotal += pointsRoll;

        // inform if Tutto
        if (isTutto) {
            System.out.println("You accomplished a Tutto, calculating points and ending the turn");
            int pointsTutto = aCurrentRuleset.handleTutto(pointsTotal);
            System.out.println(String.format("Your Tutto scored you %s points", pointsTutto));
            pointsTotal += pointsTutto;
        }



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
