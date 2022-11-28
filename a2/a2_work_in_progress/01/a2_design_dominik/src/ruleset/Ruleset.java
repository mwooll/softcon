package ruleset;

import die.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Ruleset {

    /**
     * A class subtyping the ruleset. Ruleset needs to
     * know how to handle game rules
     *
     * todo: returnName and explainRules must be abstract classes
     * todo: Use Default (rename to TestAbstract?) to test all methods that have a default
     * todo: Subclasses only test the methods they override, everything else is covered by Default
     */

    // which DiceCombos are valid for the given ruleset?
    protected List<DiceCombo> aValidCombos = new ArrayList<>();

    /**
     * The default Constructor sets the valid combos for the ruleset
     */
    public Ruleset(){
        setValidCombos();
    };

    /**
     * A clone function returns a new instance of the subclass instance
     */
    public abstract Ruleset clone();

    /**
     * Return the name of the ruleset
     */
    public String returnName() {return "PLACEHOLDER";}

    /**
     * Explain the rules which make up the ruleset.Ruleset
     * @return The rules as a printable string
     */
    public String explainRules() {
        return "PLACEHOLDER";
    }

    /**
     * Given the ruleset, set the list with all possible valid combos
     * The default adds the most commonly used
     * The subclasses can override this method to accommodate a specific ruleset
     */
    public void setValidCombos() {
        aValidCombos.add(DiceCombo.SINGLE_FIVE);
        aValidCombos.add(DiceCombo.SINGLE_ONE);
        aValidCombos.add(DiceCombo.TRIPLET_ONE);
        aValidCombos.add(DiceCombo.TRIPLET_TWO);
        aValidCombos.add(DiceCombo.TRIPLET_THREE);
        aValidCombos.add(DiceCombo.TRIPLET_FOUR);
        aValidCombos.add(DiceCombo.TRIPLET_FIVE);
        aValidCombos.add(DiceCombo.TRIPLET_SIX);
    };

    /**
     * The default is that nothing happens since only Straight wants to remove valid combos
     */
    public void removeValidCombo(DiceCombo pDiceCombo) {}

    /**
     * Return a list with all valid DiceCombos for the Ruleset
     */
    public List<DiceCombo> returnValidCombos() {return Collections.unmodifiableList(aValidCombos);}

    /**
     * Given a list of dice combos, sum up the points according to the rules
     * Accepts also empty lists
     * Default is applicable to majority (Bonus, x2), must be overridden for other Rulesets
     *
     * @return Integer value of summed points the input generated
     */
    public int sumUpPoints(List<DiceCombo> pListDiceCombo){

        int outPoints = 0;

        for (DiceCombo dicecombo : pListDiceCombo) {
            outPoints += dicecombo.returnPoints();
        }

        return outPoints;

    };

    /**
     * Specify the amounts of points to add when a Tutto happens
     * Receives the current points in case an operation on those points is needed
     * @param pPoints The points scored so far in this round
     */
    public int handleTutto(int pPoints){
        return 0;
    };


    /**
     * Specify what happens to the points from sumUpPoints when a NULL happens
     * Only FIREWORKS returns the points, all other rulesets return 0 points
     * @param pPoints The points scored so far in this round
     */
    public int handleNull(int pPoints) {
        return 0;
    }

}
