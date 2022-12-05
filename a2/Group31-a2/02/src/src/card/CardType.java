package card;
import ruleset.*;

public enum CardType {

    /**
     * CardTypes lists all possible Cards with a ruleset.Ruleset attached
     */


    DEFAULT(new Default()),
    BONUS200(new Bonus(200)),
    BONUS300(new Bonus(300)),
    BONUS400(new Bonus(400)),
    BONUS500(new Bonus(500)),
    BONUS600(new Bonus(600)),

    STOP(new Stop()),

    CLOVERLEAF(new Cloverleaf()),

    FIREWORKS(new Fireworks()),

    PLUSMINUS(new PlusMinus()),

    STRAIGHT(new Straight()),

    X2(new X2());



    private final Ruleset aRuleset;

    CardType(Ruleset pRuleset) {
        aRuleset = pRuleset;
    }

    /**
     * Return a fresh copy of the ruleset, a new instance
     * @return A fresh instance of the ruleset associated with that CardType
     */
    public Ruleset getFreshRuleset() {
        return aRuleset.clone();
    }

}
