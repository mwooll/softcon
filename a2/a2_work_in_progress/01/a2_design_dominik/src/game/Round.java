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
     * todo: Fireworks: Player cannot chose to stop or only remove one combo
     * todo: PlusMinus, Cloverleaf: Player cannot chose to stop, but can remove single combos
     */

    // Each Round instance has to be able to tell whether a new card should be drawn or not given the current events
    private boolean aNeedsNewRuleset = true;
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

        // Determine if player can stop
        if (aCurrentRuleset.returnName().equals("FIREWORKS")) {
            aMustContinue = true;
            aMustRemoveAll = true;
        }
        if (aCurrentRuleset.returnName().equals("CLOVERLEAF") || aCurrentRuleset.returnName().equals("PLUS/MINUS")) {
            aMustContinue = true;
        }

        // Show the initial roll
        System.out.println(String.format("Fresh round initialized with ruleset %s", aCurrentRuleset.returnName()));

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

        int pointsTotal = 0;
        boolean isTutto = false;

        // At the start of the round, refresh the DiceSet
        aDiceSet.refresh();

        // Re Roll all Dice at the start of the Round
        aDiceSet.rollRemaining();

        // Determine the initial valid combinations
        List<DiceCombo> aRemovableDiceCombos = returnRemovableDiceCombos();

        // Determine if the current roll is a null
        boolean aNull = isNull();

        while(!aNull && !isTutto) {

            // - Tell player that he has not rolled a null and show him all possible DiceCombos
            System.out.println("Your remaining dice:");
            System.out.println(aDiceSet);

            // - Ask player if stop or continue
            if (aMustContinue) {
                System.out.println("The current ruleset does not let you stop, you have to try to accomplish a Tutto");
            } else {
                if (aParser.askStop()) {break;}
            }

            // - Ask player which DiceCombo to remove, only possible if there are DiceCombos to remove
            boolean keepRemoving = true;
            while (keepRemoving && !isNull()) {
                System.out.println("Your remaining dice:");
                System.out.println(aDiceSet);

                // Either remove DiceCombos automatically or ask player
                // First show all removable combos
                System.out.println("Combinations currently possible to remove:");
                for (DiceCombo dicecombo : aRemovableDiceCombos) {
                    System.out.println(String.format("%s", dicecombo));
                }

                DiceCombo toRemove;
                if (aMustRemoveAll) {
                    if (aRemovableDiceCombos.size() == 0) {
                        System.out.println("debug");
                    }
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
            } else {
                System.out.println("Re-rolling remaining dice ...");
                aDiceSet.rollRemaining();
                System.out.println(aDiceSet);
                aRemovableDiceCombos = returnRemovableDiceCombos();
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
            System.out.println("You accomplished a Tutto, calculating points and ending the round");
            int pointsTutto = aCurrentRuleset.handleTutto(pointsTotal);
            System.out.println(String.format("Your Tutto scored you %s points", pointsTutto));
            pointsTotal += pointsTutto;
        }

        /*
        When finishing the round, determine if the constellation of Ruleset when Tutto is such that
        we don't want a new card to be drawn
         */
        if (isTutto) {
            if (aCurrentRuleset.returnName().equals("FIREWORKS")) {
                System.out.println("You scored a Tutto while FIREWORKS is uncovered. The Card stays and you keep rolling!");
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
            System.out.println("You socred a Tutto while PLUS/MINUS is uncovered. The leading player will have 1000 points deducted.");
        }

        // return the points
        System.out.println(String.format("Your total round scored you %s points", pointsTotal));
        return pointsTotal;
    }

    /**
     * Ask
     */

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

    /**
     * Return based on the current state if a new Card should be drawn
     */
    public boolean drawNewCard () {return aNeedsNewRuleset;}

}
