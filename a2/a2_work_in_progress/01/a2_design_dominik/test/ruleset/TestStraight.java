package ruleset;

import die.DiceCombo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestStraight {

    Straight straight = new Straight();

    @Test
    public void test_name() {

        assertEquals("STRAIGHT", straight.returnName());

    }

    @Test
    public void test_explanation() {

        String expected = "You have to accomplish a 'Straight', which consists of the numbers: 1, 2, 3, 4, 5, 6. "
                + "You may not end your turn prematurely and rolling a Null scores you no points. "
                + "But if you accomplish a 'Straight' you get 2000 points and may continue.";

        assertEquals(expected, straight.explainRules());

    }

    @Test
    public void testBonus_handleTutto() {
        assertEquals(2000, straight.handleTutto(100));
    }

    @Test
    public void testBonus_sumPoints() {
        List<DiceCombo> rolledCombos = new ArrayList<>();
        rolledCombos.add(DiceCombo.SINGLE_ONE);
        rolledCombos.add(DiceCombo.TRIPLET_FIVE);
        assertEquals(0, straight.sumUpPoints(rolledCombos));
    }

}