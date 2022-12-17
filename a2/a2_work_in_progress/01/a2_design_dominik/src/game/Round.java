package game;

import die.*;
import ruleset.*;

import java.util.ArrayList;
import java.util.List;
public class Round {

    /**
     * Represents a part of a players turn, one fresh DiceSet and one current Ruleset
     * Ends either when a Tutto happens or when a NULL is rolled
     * The Round know if the next Round needs a new Ruleset or not
     *
     * todo: Instead of boolean flags for mustDoXY, new class Decision maker/Strategy which uses an InputParser and the aCurrentRuleset to decide
     * todo: The boolean flags for returning the state are not clean. That should be cleaned up in some finishUpRound step
     */

    // Each Round instance has to be able to tell whether a new card should be drawn or not given the current events
    private boolean aNeedsNewRuleset = true;

    // Each Round instance can tell if the leading players must be deducted points
    private boolean aDecrease = false;

    // Each Round instance can tell if it was a null or not
    private boolean aIsNull = false;

    // Each Round instance can tell if it was a Tutto or not
    private boolean aIsTutto = false;

    // Depending on the Ruleset the player cannot take some decisions himself
    private boolean aMustContinue = false;
    private boolean aMustRemoveAll = false;

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

        // Clear the rolledDiceCombos
        aRolledDiceCombos.clear();

        // Determine if player can stop
        if (aCurrentRuleset.returnName().equals("FIREWORKS")) {
            aMustContinue = true;
            aMustRemoveAll = true;
        }
        if (aCurrentRuleset.returnName().equals("CLOVERLEAF") || aCurrentRuleset.returnName().equals("PLUS/MINUS") || aCurrentRuleset.returnName().equals("STRAIGHT")) {
            aMustContinue = true;
        }

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
     * At the end, the Round instance checks if it should prohibit a new Card being drawn
     * @return An integer with the total of points
     */
    public int playRound() {

        // Reset the aNeedsNewRuleset to true, that's the default
        aNeedsNewRuleset = true;

        // Reset the aDecrease to false, that's the default
        aDecrease = false;

        // Reset aIsTutto to false
        aIsTutto = false;

        // hold the points of this round
        int pointsTotal = 0;

        // isTutto lives during that round to keep track if we have a Tutto while removing combos
        boolean isTutto = false;

        // At the start of the round, refresh the DiceSet
        aDiceSet.refresh();

        // Re Roll all Dice at the start of the Round
        aDiceSet.rollRemaining();

        // Determine the initial valid combinations
        List<DiceCombo> aRemovableDiceCombos = returnRemovableDiceCombos();

        // Determine if the current roll is a null
        aIsNull = rollIsNull();

        // Flag if the player wants to stop his round
        boolean stopRound = false;

        int rollingCounter = 0;
        while(!aIsNull && !isTutto && !stopRound) {

            rollingCounter += 1;


            // - Ask player which DiceCombo to remove, only possible if there are DiceCombos to remove
            boolean keepRemoving = true;

            // - Ask player if stop or continue, if he wants to stop, add points of all remaining combnations possible to remove and never enter the while loop keepRemoving
            if (aMustContinue) {
                System.out.println("The current ruleset does not let you stop, you have to try to accomplish a Tutto");
            } else {
                if (rollingCounter > 1) {System.out.println(returnRemovableDiceCombos());}
                if (aParser.askStop()) {
                    int endingPoints = endTurn();
                    pointsTotal = pointsTotal + endingPoints;
                    keepRemoving = false;
                    stopRound = true;
                }
            }

            while (keepRemoving && !rollIsNull()) {
                System.out.println("Your remaining dice:");
                System.out.println(aDiceSet);

                // Either remove DiceCombos automatically or ask player
                DiceCombo toRemove;
                if (aMustRemoveAll) {
                    toRemove = aRemovableDiceCombos.get(0);
                    System.out.println(String.format("The current ruleset forces you to remove all possible combinations, removing %s", toRemove));
                } else {
                    toRemove = aParser.askWhichRemove(aRemovableDiceCombos);
                }

                // Remove that DiceCombo from the DiceSet, refresh the removable DiceCombos
                for (DieValue dievalue : toRemove) {
                    aDiceSet.moveDie(dievalue);
                }
                // Remove that DiceCombo from the valid combos of the Ruleset
                aCurrentRuleset.removeValidCombo(toRemove);
                aRemovableDiceCombos = returnRemovableDiceCombos();
                // Add the removed DiceSet to the aRolledDiceSet
                aRolledDiceCombos.add(toRemove);

                // If there are more removable combos, show him and ask if he wants to remove more, or remove silently depending on ruleset
                // Otherwise inform that there are no more combos to remove and the remaining dice are rerolled
                if (aRemovableDiceCombos.size() > 0) {
                    if (!aMustRemoveAll) {
                        System.out.println("Your remaining dice:");
                        System.out.println(aDiceSet);
                        keepRemoving = aParser.askKeepRemoving();
                    }
                } else {
                    System.out.println("No more combinations to remove");
                }
            }

            // - After removing is done, check if is Tutto (if there are no remaining Dice)
            // - otherwise roll remaining Dice and check removable combos
            if (aDiceSet.getSizeLeft() == 0) {
                isTutto = true;
            } else if (stopRound) {
                System.out.println("Stopping round ...");
            } else {
                System.out.println("Re-rolling remaining dice ...");
                aDiceSet.rollRemaining();
                System.out.println(aDiceSet);
                aRemovableDiceCombos = returnRemovableDiceCombos();
                aIsNull = rollIsNull();
            }

        }

        // inform if null, not if a STOP card
        if (aIsNull) {
            if (!aCurrentRuleset.returnName().equals("STOP")) {
                System.out.println("Your roll was a null, calculating points and ending the turn");
            }
        }

        // inform if Tutto
        if (isTutto) {
            System.out.println("You accomplished a Tutto, calculating points and ending the round");
            aIsTutto = true;
        }

        // inform if neither null nor Tutto
        if (!aIsNull && !aIsTutto) {
            System.out.println("You ended your turn voluntarily, calculating points and ending the turn");
        }

        // independently of null or Tutto, sum up the rolled points. They can contain points even if null
        int pointsRoll = aCurrentRuleset.sumUpPoints(aRolledDiceCombos);
        pointsTotal += pointsRoll;

        // add Tutto points
        if (aIsTutto) {
            int pointsTutto = aCurrentRuleset.handleTutto(pointsTotal);
            // If the Tutto scored extra points, inform the player
            if (pointsTutto > 0) {
                System.out.println(String.format("Your Tutto scored you %s points", pointsTutto));
            }
            pointsTotal += pointsTutto;
        }

        // determine the final points of the round, which can be nulled by a null roll
        int pointsFinal = pointsTotal;

        // handle final points if null
        if (aIsNull) {
            pointsFinal = aCurrentRuleset.handleNull(pointsTotal);
        }

        /*
        When finishing the round, determine if the constellation of Ruleset when Tutto is such that
        we don't want a new card to be drawn
         */
        if (isTutto) {
            if (aCurrentRuleset.returnName().equals("FIREWORKS")) {
                System.out.println("You scored a Tutto while FIREWORKS is uncovered. The card stays and you keep rolling!");
                aNeedsNewRuleset = false;
            }
            if (aCurrentRuleset.returnName().equals("CLOVERLEAF")) {
                System.out.println("You scored a Tutto while CLOVERLEAF is uncovered.\nThe card stays, if you roll another Tutto you win the game!");

                aNeedsNewRuleset = false;
            }
        }

        /*
        Inform the player if a PlusMinus deducts points
         */
        if (isTutto && aCurrentRuleset.returnName().equals("PLUS/MINUS")) {
            System.out.println("You scored a Tutto while PLUS/MINUS is uncovered. The leading player(s) will have 1000 points deducted right now.");

            aDecrease = true;
        }

        // return the points
        System.out.println(String.format("Your round scored you a total of %s points", pointsFinal));
        return pointsFinal;
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
     *
     * @return
     */
    private int endTurn() {
        List<DiceCombo> maximalCombos = new ArrayList<>(aDiceSet.returnMaximalCombos());
        int endingPoints = aCurrentRuleset.sumUpPoints(maximalCombos);
        return endingPoints;
    }


    /**
     * Given the current state of a DiceSet and the current Ruleset, determine if DiceSet is a NULL roll
     */
    private boolean rollIsNull() {
        return returnRemovableDiceCombos().size() == 0;
    }

    /**
     * Return based on the current state if a new Card should be drawn
     */
    public boolean drawNewCard () {return aNeedsNewRuleset;}

    /**
     * Return based on the current state if the leading players must be deducted points
     */
    public boolean decreasePoints() {return aDecrease;}

    /**
     * Return based on the current state if the Round is a null
     */
    public boolean isNull() {return aIsNull;}

    /**
     * Return based on the current state if the Round is a Tutto
     */
    public boolean isTutto() {return aIsTutto;}

}
