package ruleset;

import die.DiceCombo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ruleset.Bonus;
import ruleset.Ruleset;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestBonus {

    int bp = 200;
    Bonus rs = new Bonus(bp);

    @Test
    public void testBonus_name() {
        assertEquals(String.format("BONUS %s", bp), rs.returnName());
    }

    @Test
    public void testBonus_explanation() {
        assertEquals(String.format("The BONUS card gives an extra %s points if a Tutto is accomplished", bp), rs.explainRules());
    }

    @Test
    public void testBonus_handleTutto() {

        int bp = 100;
        Ruleset rs = new Bonus(bp);

        assertEquals(100, rs.handleTutto(0));

    }

    @Test
    public void testBonus_handleTutto2() {

        int bp = 100;
        Ruleset rs = new Bonus(bp);

        assertEquals(100, rs.handleTutto(1000));

    }

    @Test
    public void testBonus_handleNull() {

        // No matter the rolled combos, when a null occurs should return 0

        int bp = 100;
        Ruleset rs = new Bonus(bp);
        List<DiceCombo> tmpCombos = new ArrayList<>();
        tmpCombos.add(DiceCombo.SINGLE_ONE);

        // sum up points
        int tmpPoints = rs.sumUpPoints(tmpCombos);

        // apply tutto
        tmpPoints += rs.handleTutto(tmpPoints);

        // apply null
        int finalPoints = rs.handleNull(tmpPoints);

        assertEquals(0, finalPoints);

    }

    @Test
    public void testBonus_invalidConstructor() {

        int bp = -100;

        assertThrows(AssertionError.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Ruleset rs = new Bonus(bp);
            }
        });

    }

    @Test
    public void test_removal() {

        // the list of valid combos should remain unchanged
        List<DiceCombo> preList = rs.returnValidCombos();
        rs.removeValidCombo(DiceCombo.SINGLE_ONE);
        List<DiceCombo> postList = rs.returnValidCombos();

        assertEquals(preList, postList);

    }

}