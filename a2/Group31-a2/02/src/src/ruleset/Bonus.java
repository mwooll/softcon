package ruleset;

public class Bonus extends Ruleset {

    private final int aBonusPoints;

    /**
     * Construct a ruleset.Bonus ruleset.Ruleset
     * @pre pBonusPoints must be a positive integer
     * @param pBonusPoints integer specifying how many bonus points
     */
    public Bonus(int pBonusPoints) {
        super();
        assert pBonusPoints > 0;
        aBonusPoints = pBonusPoints;
    }

    @Override
    public Bonus clone() {return new Bonus(aBonusPoints);}

    @Override
    public String returnName() {return String.format("BONUS %s", aBonusPoints);}

    @Override
    public String explainRules() {
        return String.format("The BONUS card gives an extra %s points if a Tutto is accomplished", aBonusPoints);
    };

    @Override
    public int handleTutto(int pPoints) {return aBonusPoints;}

}
