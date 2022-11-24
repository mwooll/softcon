package ruleset;

public class Cloverleaf extends Ruleset{
    private int numAchievedTuttos;

    @Override
    public String returnName() {return "CLOVERLEAF";}

    @Override
    public String explainRules() {
        return "Getting two Tuttos back to back will result in an immediate win. " +
                "You may not end your turn prematurely and rolling a Null scores you no points.";
    }

    public void getDrawn() {numAchievedTuttos = 0;}

    /**
     * This needs to be handled by Round/Turn and end the game.
     * @return 100_000_000 for now to guarantee a win.
     */
    @Override
    public int handleTutto(int pPoints) {
        numAchievedTuttos += 1;
        if (numAchievedTuttos >= 2) {
            System.out.println("The current player wins.");
            return 100_000_000;
        }
        return 0;
    }
}
