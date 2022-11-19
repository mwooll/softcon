import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    /**
     * A deck represents the pile of cards which allow
     * to draw the top card from and reshuffle if needed
     */

    private final List<Card> aCards = new ArrayList<>();

    /**
     * Constructor creates all the Cards in DeckSpec and shuffles them
     * Creates Cards specified in pDeckSpec
     * After creation shuffles the Deck
     *
     * @param pDebug If true use debug subset of cards, otherwise full game spec
     */
    public Deck(boolean pDebug) {

        DeckSpec pDeckSpec = new DeckSpec(pDebug);

        // each ct is a CardType instance
        for (CardType ct : pDeckSpec) {
            // DeckSpec specifies how many Cards of CardType ct
            Integer ctCount = pDeckSpec.getCount(ct);
            // each CardType has an associated Ruleset
            Ruleset ctRuleset = ct.getRuleset();

            // create the needed amount of Cards
            for (int i = 0; i < ctCount; i++) {
                aCards.add(new Card(ctRuleset));
            }
        }

        Collections.shuffle(aCards);
    }

    /**
     * Draw the top Card from the Deck and return it.
     * This draw acts like pop, it does remove the Card from the Deck
     * @pre Deck is not empty
     * @return A Card instance
     */
    public Card draw() {
        assert !isEmpty();
        return aCards.remove(aCards.size()-1);
    }

    /**
     * Return how many Cards are left in Deck
     * @return int How many cards left?
     */
    public int cardsLeft() {
        if (isEmpty()) {
            return 0;
        } else {
            return aCards.size();
        }
    }

    /**
     * Check if the deck is empty
     * @return True if empty, False otherwise
     */
    private boolean isEmpty() {
        if (aCards.size() == 0) {
            return true;
        }
        return false;
    }
}
