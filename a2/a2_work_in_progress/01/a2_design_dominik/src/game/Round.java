package game;

import card.CardType;
import die.*;
import ruleset.*;

public class Round {

    /**
     * Represents a part of a players turn, one fresh DiceSet and one drawn Card
     * Ends either when a Tutto happens or when a NULL is rolled
     * The drawn Card determines the Ruleset which is applied for that Round
     */

    private int aScore = 0;
    private final CardType aCurrentCard = CardType.DEFAULT;
    private final Ruleset aCurrentRuleset = new Default();
    private final DiceSet aDiceSet;

    public Round() {
        aDiceSet = DiceSet.get();
        // aDiceSet = DiceSet.getDebug();

        // Always refresh the dice set at the initialization of a new round
        aDiceSet.refresh();

    }

    public void playRound() {

        // Draw a card or not

        // Current card sets the Ruleset

        // Throw dice until Tutto or NULL

    }

    private void setRuleset() {
        // Set the current Ruleset
    }

}
