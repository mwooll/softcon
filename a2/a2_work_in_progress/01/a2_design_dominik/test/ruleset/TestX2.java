package ruleset;

import die.DiceCombo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestX2 {

    Ruleset rs = new X2();

    @Test
    public void test_name() {
        assertEquals("X2", rs.returnName());
    }

    @Test
    public void test_explanation() {
        assertEquals("The X2 card doubles your current rolls points if you roll a tutto.", rs.explainRules());
    }

    @Test
    public void test_handleTutto() {

        int tmpPoints = 100;

        assertEquals(200, rs.handleTutto(tmpPoints));

    }

    @Test
    public void test_handleTutto2() {

        int tmpPoints = 0;

        assertEquals(0, rs.handleTutto(tmpPoints));

    }

    @Test
    public void test_rolling_tutto() {

        // simulate a list with rolled combos, sum them up and then simulate tutto
        List<DiceCombo> rolledCombos = new ArrayList<>(Arrays.asList(DiceCombo.SINGLE_ONE, DiceCombo.TRIPLET_ONE));

        // should yield 1100
        int pointsRolled = rs.sumUpPoints(rolledCombos);

        assertEquals(1100, pointsRolled);
        assertEquals(2200, rs.handleTutto(pointsRolled));

    }

}