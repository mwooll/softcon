package ruleset;

import die.DiceCombo;

import java.util.List;

public class PlusMinus extends Ruleset {

    @Override
    public PlusMinus clone(){return new PlusMinus();}

    @Override
    public String returnName() {return "PLUS/MINUS";}

    @Override
    public String explainRules() {
        return "You have to try to accomplish a Tutto, you can't stop. If you succeed, you receive exactly 1000 points" +
                "and the leading player has 1000 points deducted. The rolled combinations do not score you any points," +
                "if you roll a null you score 0 points.";
    };

    @Override
    public int sumUpPoints(List<DiceCombo> pListDiceCombo) {return 0;}

    @Override
    public int handleTutto(int pPoints){return 1000;}

}
