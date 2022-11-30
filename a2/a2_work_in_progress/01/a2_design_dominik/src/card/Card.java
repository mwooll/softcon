package card;

import ruleset.Ruleset;

public class Card {

    /**
     * A Card represents on of the Cards which the player
     * can draw from the pile.
     * A Card has a CardType attached to it
     */

    private final CardType aCardType;

    public Card(CardType pCardType) {
        aCardType = pCardType;
    }

    /**
     * Copy Constructor to return a new instance which is a deep copy
     */
    public Card(Card pCard) {
        aCardType = pCard.aCardType;
    }

    public CardType returnCardType() {return aCardType;}

}
