package game;

import ruleset.*;

public class Round {

    /**
     * Represents a part of a players turn, one fresh DiceSet and one drawn Card
     * Ends either when a Tutto happens or when a NULL is rolled
     * The drawn Card determines the Ruleset which is applied for that Round
     */

    private int aScore = 0;
    private Ruleset aCurrentRuleset = new Default();


}
