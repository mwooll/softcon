package die;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDiceCombo {
    @Test
    public void test_returnTriplet_1() {
        DiceCombo expected = DiceCombo.TRIPLET_ONE;
        DiceCombo actual = DiceCombo.returnTriplet(DieValue.ONE);

        assertEquals(expected, actual);
    }

    @Test
    public void test_returnTriplet_6() {
        DiceCombo expected = DiceCombo.TRIPLET_SIX;
        DiceCombo actual = DiceCombo.returnTriplet(DieValue.SIX);

        assertEquals(expected, actual);
    }
}
