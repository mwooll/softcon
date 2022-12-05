package ruleset;

import die.DiceCombo;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Straight extends Ruleset {

    @Override
    public Straight clone() {return new Straight();}

    @Override
    public String returnName() {return "STRAIGHT";}

    @Override
    public String explainRules() {
        return "You have to accomplish a 'Straight', which consists of the numbers: 1, 2, 3, 4, 5, 6. "
                + "You may not end your turn prematurely and rolling a Null scores you no points. "
                + "But if you accomplish a 'Straight' you get 2000 points and may continue.";
    }

    @Override
    public void setValidCombos() {
        aValidCombos.add(DiceCombo.SINGLE_ONE);
        aValidCombos.add(DiceCombo.SINGLE_TWO);
        aValidCombos.add(DiceCombo.SINGLE_THREE);
        aValidCombos.add(DiceCombo.SINGLE_FOUR);
        aValidCombos.add(DiceCombo.SINGLE_FIVE);
        aValidCombos.add(DiceCombo.SINGLE_SIX);
    }

    /**
     * Straight needs the ability to remove once picked SINGLE combos from the list of valid combos
     * @pre pDiceCombo must be a valid combo in aValidCombos
     */
    @Override
    public void removeValidCombo(DiceCombo pDiceCombo) {
        assert pDiceCombo != null;
        assert aValidCombos.contains(pDiceCombo);

        aValidCombos.remove(pDiceCombo);

    }

    @Override
    public int sumUpPoints(List<DiceCombo> pListDiceCombo) {return 0;}

    @Override
    public int handleTutto(int pPoints) { return 2000; }
}
