import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Deck {

    /**
     * A deck represents the pile of cards which allow
     * to draw the top card from and reshuffle if needed
     */

    private List<Card> aCards = new ArrayList<>();

    /**
     * Constructor creates all the Cards in DeckSpec and shuffles them
     * @param pDebug If true create only small subset of Cardtypes for debugging, else create
     *               original set of Cards
     */
    public Deck(boolean pDebug) {

        DeckSpec tmpDeckSpec = new DeckSpec(pDebug);

        for (CardType ct : tmpDeckSpec.keySet()) {

        }

        aCards.add(new Card(cardtype.getRuleset()))
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
