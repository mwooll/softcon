package ruleset;

import die.DiceCombo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestFireworks {

    Ruleset fireworks = new Fireworks();

    @Test
    public void test_name() {

        assertEquals("FIREWORKS", fireworks.returnName());

    }

    @Test
    public void test_explanation() {

        String expected = "You have to remove all valid combinations and you keep rolling until you roll a null." +
                "If you achieve a Tutto you leave the card and keep rolling. The Tutto does not score you any points, " +
                "but you keep all the points you rolled.";

        assertEquals(expected, fireworks.explainRules());

    }

    @Test
    public void test_sumUpPoints_null() {

        // simulate a list with rolled combos, sum them up and then simulate tutto
        List<DiceCombo> rolledCombos = new ArrayList<>(Arrays.asList(DiceCombo.SINGLE_ONE, DiceCombo.TRIPLET_THREE));

        // should yield 400
        int pointsRolled = fireworks.sumUpPoints(rolledCombos);

        assertEquals(400, pointsRolled);

    }

    @Test
    public void test_Tutto() {

        // simulate a list with rolled combos, sum them up and then simulate tutto
        List<DiceCombo> rolledCombos = new ArrayList<>(Arrays.asList(
                DiceCombo.SINGLE_ONE, DiceCombo.TRIPLET_THREE, DiceCombo.TRIPLET_SIX
        ));

        // should yield 1000
        int pointsRolled = fireworks.sumUpPoints(rolledCombos);

        // The tutto should yield 0 nonetheless
        assertEquals(0, fireworks.handleTutto(pointsRolled));

    }


}