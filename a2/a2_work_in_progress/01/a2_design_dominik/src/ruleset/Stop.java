package ruleset;

import die.DiceCombo;

import java.util.List;

public class Stop extends Ruleset {

    @Override
    public Stop clone(){return new Stop();}

    @Override
    public String returnName() {return "STOP";}

    @Override
    public String explainRules() {
        return "The STOP card.Card stops the turn when drawn";
    }

    /**
     * The STOP Ruleset does not contain any valid combos
     */
    @Override
    public void setValidCombos() {}

    @Override
    public int sumUpPoints(List<DiceCombo> pListDiceCombo) {return 0;}

}
