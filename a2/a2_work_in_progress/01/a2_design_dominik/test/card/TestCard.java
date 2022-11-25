package card;

import card.Card;
import org.junit.jupiter.api.Test;
import ruleset.Bonus;
import ruleset.Ruleset;
import ruleset.Stop;

import static org.junit.jupiter.api.Assertions.*;

public class TestCard {

    @Test
    public void testBonus600() {
        CardType ct = CardType.BONUS600;
        Card card = new Card(ct);

        assertEquals(CardType.BONUS600, card.returnCardType());

    }

}
