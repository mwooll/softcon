package ruleset;

import die.DiceCombo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
    public void test_handleTutto() {
        assertEquals(2000, straight.handleTutto(100));
    }

    @Test
    public void test_sumPoints() {
        List<DiceCombo> rolledCombos = new ArrayList<>();
        rolledCombos.add(DiceCombo.SINGLE_ONE);
        rolledCombos.add(DiceCombo.TRIPLET_FIVE);
        assertEquals(0, straight.sumUpPoints(rolledCombos));
    }

    @Test
    public void test_removeValidCombos() {

        // Get all valid combos after init of Straight
        List<DiceCombo> combos = straight.returnValidCombos();

        // Then remove one combo
        straight.removeValidCombo(DiceCombo.SINGLE_ONE);

        List<DiceCombo> remainingCombos = new ArrayList<>(Arrays.asList(
             DiceCombo.SINGLE_TWO, DiceCombo.SINGLE_THREE, DiceCombo.SINGLE_FOUR,
             DiceCombo.SINGLE_FIVE, DiceCombo.SINGLE_SIX
        ));

        assertEquals(remainingCombos, straight.returnValidCombos());

    }

    @Test
    public void test_invalid_removeValidCombos() {

        assertThrows(AssertionError.class, () -> straight.removeValidCombo(DiceCombo.TRIPLET_ONE));

    }

    @Test
    public void test_empty_removeValidCombos() {

        // remove all valid combos
        straight.removeValidCombo(DiceCombo.SINGLE_ONE);
        straight.removeValidCombo(DiceCombo.SINGLE_TWO);
        straight.removeValidCombo(DiceCombo.SINGLE_THREE);
        straight.removeValidCombo(DiceCombo.SINGLE_FOUR);
        straight.removeValidCombo(DiceCombo.SINGLE_FIVE);
        straight.removeValidCombo(DiceCombo.SINGLE_SIX);

        assertThrows(AssertionError.class, () -> straight.removeValidCombo(DiceCombo.SINGLE_ONE));

    }

}