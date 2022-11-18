public class Bonus extends Ruleset {

    private final int aBonusPoints;

    /**
     * Construct a Bonus Ruleset
     * @pre pBonusPoints must be a positive integer
     * @param pBonusPoints
     */
    public Bonus(int pBonusPoints) {

        super();

        assert pBonusPoints > 0;

        aBonusPoints = pBonusPoints;
    }

    String returnName() {return String.format("BONUS %s", aBonusPoints);}
    String explainRules() {
        return String.format("The BONUS card gives an extra %s points if a Tutto is accomplished", aBonusPoints);
    };

}
