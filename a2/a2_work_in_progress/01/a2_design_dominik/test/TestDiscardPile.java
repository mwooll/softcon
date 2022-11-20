import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestDiscardPile {

    DiscardPile discardPile = new DiscardPile();
    Card card = new Card(new Default());

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
        assertEquals(card.returnName(), cardPeek.returnName());
    }

    @Test
    public void testPeek_copy() {
        discardPile.add(card);
        Card cardPeek = discardPile.peek();
        assertFalse(cardPeek.equals(card));

    }
}