import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCard {

    @Test
    public void testBonus600() {
        Ruleset rs = new Bonus(600);
        Card card = new Card(rs);

        assertEquals("BONUS 600", card.returnName());

    }

    @Test
    public void testStop() {
        Ruleset rs = new Stop();
        Card card = new Card(rs);

        assertEquals("STOP", card.returnName());

    }

}
