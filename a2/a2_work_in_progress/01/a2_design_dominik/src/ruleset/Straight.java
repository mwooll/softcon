package ruleset;

import die.DiceCombo;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Straight {

    protected List<DiceCombo> aValidCombos = new ArrayList<>();

    public String returnName() {return "STRAIGHT";}

    public String explainRules() {
        return "You have to accomplish a 'Straight', which consists of the numbers: 1, 2, 3, 4, 5, 6. "
                + "You may not end your turn prematurely and rolling a Null scores you no points. "
                + "But if you accomplish a 'Straight' you get 2000 points and may continue.";
    }

    public void setValidCombos() {
        aValidCombos.add(DiceCombo.SINGLE_ONE);
        aValidCombos.add(DiceCombo.SINGLE_TWO);
        aValidCombos.add(DiceCombo.SINGLE_THREE);
        aValidCombos.add(DiceCombo.SINGLE_FOUR);
        aValidCombos.add(DiceCombo.SINGLE_FIVE);
        aValidCombos.add(DiceCombo.SINGLE_SIX);
    }

    public void getDrawn() {
        aValidCombos.clear();
        setValidCombos();
    }

    public int sumUpPoints(List<DiceCombo> pListDiceCombo) {
        if (aValidCombos.size() != 0) {return 0;}

        // else: every single combo was taken
        return 2000;
    }

    // 0 bonus points for a Tutto
    public int handleTutto() { return 0; }
}
