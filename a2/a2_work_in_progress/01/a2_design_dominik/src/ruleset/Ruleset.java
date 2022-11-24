package ruleset;

import die.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Ruleset {

    /**
     * A class subtyping the ruleset. Ruleset needs to
     * know how to handle game rules
     */

    // which DiceCombos are valid for the given ruleset?
    protected List<DiceCombo> aValidCombos = new ArrayList<>();

    public Ruleset(){};

    /**
     * Return the name of the ruleset.Ruleset
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
     */
    public int handleTutto(int pPoints){
        return 0;
    };

}
