package ruleset;

import die.DiceCombo;

import java.util.List;

public class Cloverleaf extends Ruleset{
    public int numAchievedTuttos;

    public Cloverleaf() {
        super();
        numAchievedTuttos = 0;
    }

    @Override
    public Cloverleaf clone() {return new Cloverleaf();}

    @Override
    public String returnName() {return "CLOVERLEAF";}

    @Override
    public String explainRules() {
        return "Getting two Tuttos back to back will result in an immediate win. " +
                "You may not end your turn prematurely and rolling a null scores you no points.";
    }

    @Override
    public int sumUpPoints(List<DiceCombo> pListDiceCombo) {return 0;}

    /**
     * This needs to be handled by Round/Turn and end the game.
     * @return 100_000_000 for now to guarantee a win.
     */
    @Override
    public int handleTutto(int pPoints) {
        numAchievedTuttos += 1;
        return 0;
    }
}
