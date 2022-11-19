public enum CardType {

    /**
     * CardTypes lists all possible Cards with a Ruleset attached
     *
     * todo: BONUS Types could be enum (200, 300, 400, 500, 600) to make
     *  absolutely sure only those are created (and not e.g. 100)
     */

    BONUS200(new Bonus(200)),
    BONUS300(new Bonus(300)),
    BONUS400(new Bonus(400)),
    BONUS500(new Bonus(500)),
    BONUS600(new Bonus(600)),

    STOP(new Stop());

    private Ruleset aRuleset;

    CardType(Ruleset pRuleset) {
        aRuleset = pRuleset;
    }

    public Ruleset getRuleset() {return aRuleset;}

}
