package card;

import static org.junit.jupiter.api.Assertions.*;

import card.Card;
import card.DiscardPile;
import org.junit.jupiter.api.Test;
import ruleset.Default;

class TestDiscardPile {

    DiscardPile discardPile = new DiscardPile();
    Card card = new Card(CardType.DEFAULT);

    @Test
    public void testCreation() {
        assertEquals(0, discardPile.getSize());
    }

    @Test
    public void testAdd() {
        discardPile.add(card);
        assertEquals(1, discardPile.getSize());
    }

    @Test
    public void testPeek() {
        discardPile.add(card);
        Card cardPeek = discardPile.peek();
        assertEquals(card.returnCardType(), cardPeek.returnCardType());
    }

    @Test
    public void testPeek_copy() {
        discardPile.add(card);
        Card cardPeek = discardPile.peek();
        assertFalse(cardPeek.equals(card));

    }
}