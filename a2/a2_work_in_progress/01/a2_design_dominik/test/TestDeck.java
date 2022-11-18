import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestDeck {

    @Test
    public void testCreation() {

        // use debug deckspec
        Deck deck = new Deck(true);

        assertEquals(2, deck.cardsLeft());

    }

    @Test
    public void testDrawNotEmpty() {

        // use debug deckspec
        Deck deck = new Deck(true);

        // draw one card, there should be one left
        Card card = deck.draw();

        assertEquals(1, deck.cardsLeft());

    }

    @Test
    public void testDrawEmpty() {

        // use debug deckspec
        Deck deck = new Deck(true);

        // draw two cards, there should be no card left
        Card card1 = deck.draw();
        Card card2 = deck.draw();

        assertEquals(0, deck.cardsLeft());

    }

}
