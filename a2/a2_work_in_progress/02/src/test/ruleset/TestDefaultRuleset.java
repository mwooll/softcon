package ruleset;

import die.DiceCombo;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestDefaultRuleset {

    /**
     * Tests the base implementations of Ruleset.
     */
    Default rs = new Default();

    @Test
    public void testDefault_clone() {
        try {
            Default clonedDefault = rs.clone();
            assertNotNull(clonedDefault);
            assertTrue(clonedDefault instanceof Default); //always true
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void testDefault_returnName() {
        assertEquals("DEFAULT", rs.returnName());
    }

    @Test
    public void testDefault_explainRules() {
        assertEquals("No special rules are active.", rs.explainRules());
    }

    @Test
    public void testDefault_ValidCombos() {
        List<DiceCombo> validCombos = rs.returnValidCombos();

        List<DiceCombo> expectedCombos = new ArrayList<>();
        expectedCombos.add(DiceCombo.SINGLE_FIVE);
        expectedCombos.add(DiceCombo.SINGLE_ONE);
        expectedCombos.add(DiceCombo.TRIPLET_ONE);
        expectedCombos.add(DiceCombo.TRIPLET_TWO);
        expectedCombos.add(DiceCombo.TRIPLET_THREE);
        expectedCombos.add(DiceCombo.TRIPLET_FOUR);
        expectedCombos.add(DiceCombo.TRIPLET_FIVE);
        expectedCombos.add(DiceCombo.TRIPLET_SIX);

        assertEquals(expectedCombos, validCombos);
    }

    @Test
    public void testDefault_removeValidCombo() {

        // the list of valid combos should remain unchanged
        List<DiceCombo> preList = rs.returnValidCombos();
        rs.removeValidCombo(DiceCombo.SINGLE_ONE);
        List<DiceCombo> postList = rs.returnValidCombos();

        assertEquals(preList, postList);
    }


    @Test
    public void testDefault_sumUpPoints() {
        List<DiceCombo> combos = new ArrayList<>();
        assertEquals(0, rs.sumUpPoints(combos));

        combos.add(DiceCombo.SINGLE_FIVE);
        combos.add(DiceCombo.SINGLE_ONE);
        combos.add(DiceCombo.TRIPLET_SIX);
        assertEquals(750, rs.sumUpPoints(combos));
    }

    @Test
    public void testDefault_handleTutto() {
        assertEquals(0, rs.handleTutto(0));
    }

    @Test
    public void testDefault_handleTutto2() {
        assertEquals(0, rs.handleTutto(1000));
    }

    @Test
    public void testDefault_handleNull() {

        // No matter the rolled combos, when a null occurs should return 0

        Ruleset rs = new Default();
        List<DiceCombo> tmpCombos = new ArrayList<>();
        tmpCombos.add(DiceCombo.SINGLE_ONE);

        // sum up points
        int tmpPoints = rs.sumUpPoints(tmpCombos);

        // apply null
        int finalPoints = rs.handleNull(tmpPoints);

        assertEquals(0, finalPoints);
    }
}
