package card;

import ruleset.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    /**
     * A deck represents the pile of cards which allow
     * to draw the top card from and reshuffle if needed
     * There is no discard pile, the Deck keeps track of the drawn cards and is able to reshuffle them again
     */

    private final List<Card> aCards = new ArrayList<>();
    private final List<Card> aDiscardedCards = new ArrayList<>();

    /**
     * Constructor creates all the Cards specified in the supplied DeckSpec
     */
    public Deck(DeckSpec pDeckSpec) {

        // each ct is a card.CardType instance
        for (CardType ct : pDeckSpec) {
            // card.DeckSpec specifies how many Cards of card.CardType ct
            Integer ctCount = pDeckSpec.getCount(ct);

            // create the needed amount of Cards with CardType ct
            for (int i = 0; i < ctCount; i++) {
                aCards.add(new Card(ct));
            }
        }
    }


    /**
     * Shuffle the Deck, inplace
     */
    public void shuffle() {
        Collections.shuffle(aCards);
    }

    /**
     * Return all Cards from the drawn pile to the deck and shuffle all cards
     */
    public void refresh() {
        for (Card c : aDiscardedCards) {
            aCards.add(c);
        }
        shuffle();
    }


    /**
     * Draw the top card.Card from the card.Deck and return it.
     * This draw acts like pop, it does remove the card.Card from the card.Deck
     * Adds it to aDiscardedCards
     * @pre card.Deck is not empty
     * @return A card.Card instance
     */
    public Card draw() {
        assert !isEmpty();
        Card drawnCard = aCards.remove(aCards.size()-1);
        aDiscardedCards.add(drawnCard);
        return drawnCard;
    }

    /**
     * Return how many Cards are left in card.Deck
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
    public boolean isEmpty() {
        return aCards.size() == 0;
    }
}
