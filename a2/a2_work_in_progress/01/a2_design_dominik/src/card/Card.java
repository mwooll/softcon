package card;

import ruleset.Ruleset;

public class Card {

    /**
     * A card.Card represents on of the Cards which the player
     * can draw from the pile.
     * A card.Card has a ruleset.Ruleset
     */

    private final Ruleset aRuleset;

    public Card(Ruleset pRuleset) {
        aRuleset = pRuleset;
    }

    /**
     * Copy Constructor to return a new instance which is a deep copy
     */
    public Card(Card pCard) {
        aRuleset = pCard.aRuleset;
    }

    public String returnName() {return aRuleset.returnName();}

}
