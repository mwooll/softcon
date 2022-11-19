import java.util.ArrayList;
import java.util.List;

public class DiscardPile {

    /**
     * A pile of cards representing the used cards from the game
     * Is initialized empty, filled with cards one by one
     * Does have an order
     *
     * todo: Would a CardStack (aka Solitaire) be better? Easy to implement?
     */

    private final List<Card> aCards = new ArrayList<>();

    /**
     * Return a copy of the last discarded card
     * Does not alter the discard pile
     * @pre Discard pile cannot be empty
     * @return Card instance last discarded card
     */
    public Card peek() {
        assert !isEmpty();

        // todo: implement peek. Make sure aCards is not altered!
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
        if (aCards.size() == 0) {
            return true;
        }
        return false;
    }

}
