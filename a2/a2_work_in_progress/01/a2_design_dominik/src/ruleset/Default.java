package ruleset;


import die.DiceCombo;

import java.util.List;

public class Default extends Ruleset {

    /**
     * Default Ruleset for testing
     */

    /**
     * Return empty, always generate NULL roll
     */
    public void setValidCombos() {}

    /**
     * Always return 0 points
     */
    public int sumUpPoints(List<DiceCombo> pListDiceCombo){return 0;};

}
