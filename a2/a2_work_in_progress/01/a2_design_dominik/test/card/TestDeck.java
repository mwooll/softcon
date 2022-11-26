package card;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestDeck {

    DeckSpec deckspec56 = new DeckSpec.DeckSpecBuilder().setDefault().build();
    Deck deck56 = new Deck(deckspec56);

    @Test
    public void testCreationDebug() {
        assertEquals(56, deck56.cardsLeft());
    }

    @Test
    public void testDrawNotEmpty() {

        // use debug deckspec with two cards
        Deck deck = new Deck(new DeckSpec.DeckSpecBuilder().setStop(2).build());

        // draw one card, there should be one left
        Card card = deck.draw();

        assertEquals(1, deck.cardsLeft());

    }

    @Test
    public void testDrawEmpty() {

        // use debug deckspec with one card
        Deck deck = new Deck(new DeckSpec.DeckSpecBuilder().setFireworks(1).build());

        // draw one card, there should be no card left
        Card card1 = deck.draw();

        assertEquals(0, deck.cardsLeft());

    }

    @Test
    public void testShuffle() {

        // todo: how to do that...

    }

}
