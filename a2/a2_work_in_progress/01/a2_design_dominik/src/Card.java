public class Card {

    /**
     * A Card represents on of the Cards which the player
     * can draw from the pile.
     * A Card has a Ruleset
     */

    private final Ruleset aRuleset;

    public Card(Ruleset pRuleset) {
        aRuleset = pRuleset;
    }

    /**
     * Copy Constructor to return a new instance which is a deep copy
     */
    public Card(Card pCard) {
        this.aRuleset = pCard.aRuleset;
    }

    public String returnName() {return aRuleset.returnName();}

}
