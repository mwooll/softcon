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

    public String returnName() {return aRuleset.returnName();}

}
