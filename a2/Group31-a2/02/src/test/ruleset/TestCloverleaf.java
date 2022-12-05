package ruleset;

import die.DiceCombo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestCloverleaf {

    Ruleset cloverleaf = new Cloverleaf();

    @Test
    public void test_name() {

        assertEquals("CLOVERLEAF", cloverleaf.returnName());

    }

    @Test
    public void test_explanation() {

        String expected = "Getting two Tuttos back to back will result in an immediate win. You may not end your turn prematurely and rolling a null scores you no points.";

        assertEquals(expected, cloverleaf.explainRules());

    }

    @Test
    public void test_handle_FirstTutto() {

        // No matter the rolled combos, a Cloverleaf should never count points from a roll when the first tutto happens
        Ruleset rs = new Cloverleaf();
        List<DiceCombo> tmpCombos = new ArrayList<>();
        tmpCombos.add(DiceCombo.SINGLE_ONE);

        int tmpPoints = rs.sumUpPoints(tmpCombos);

        assertEquals(0, rs.handleTutto(tmpPoints));
    }

    @Test
    public void test_handle_SecondTutto() {

        // No matter the rolled combos, a Cloverleaf should never count points from a roll when the second tutto happens
        Ruleset rs = new Cloverleaf();
        List<DiceCombo> tmpCombos = new ArrayList<>();
        tmpCombos.add(DiceCombo.SINGLE_ONE);

        List<DiceCombo> tmpCombos2 = new ArrayList<>();
        tmpCombos.add(DiceCombo.TRIPLET_ONE);

        int tmpPoints = rs.sumUpPoints(tmpCombos);
        tmpPoints += rs.handleTutto(tmpPoints);
        tmpPoints += rs.sumUpPoints(tmpCombos2);

        assertEquals(0, rs.handleTutto(tmpPoints));

    }

    @Test
    public void test_handle_sumUpPoints() {

        // No matter the rolled combos, a Cloverleaf should never count points from a roll

        Ruleset rs = new Cloverleaf();
        List<DiceCombo> tmpCombos = new ArrayList<>();
        tmpCombos.add(DiceCombo.SINGLE_ONE);

        // sum up points
        int tmpPoints = rs.sumUpPoints(tmpCombos);

        assertEquals(0, tmpPoints);
    }



}