package card;

import card.Card;

import java.util.ArrayList;
import java.util.List;

public class DiscardPile {

    /**
     * A pile of cards representing the used cards from the game
     * Is initialized empty, filled with cards one by one
     * Does respect the order in which Cards are added, like a Stack
     */

    private final List<Card> aCards = new ArrayList<>();

    /**
     * Constructor creates empty card.DiscardPile
     */
    public DiscardPile() {}


    /**
     * Return a copy of the last discarded card
     * Does not alter the discard pile
     * @pre Discard pile cannot be empty
     * @return card.Card instance last discarded card
     */
    public Card peek() {
        assert !isEmpty();

        return new Card(aCards.get(aCards.size()-1));
    }

    /**
     * Add a card.Card to the discard pile
     * Does alter the state of aCards
     * @pre pCard must be a valid card.Card instance
     */
    public void add(Card pCard) {
        assert pCard != null;

        aCards.add(pCard);

    }

    /**
     * Return how many Cards are on the discard pile
     * @return int How many cards left?
     */
    public int getSize() {
        if (isEmpty()) {
            return 0;
        } else {
            return aCards.size();
        }
    }

    /**
     * Check if the discard pile is empty
     * @return True if empty, False otherwise
     */
    private boolean isEmpty() {
        return aCards.size() == 0;
    }

}
