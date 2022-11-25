package ruleset;

import die.DiceCombo;
import org.junit.jupiter.api.Test;
import ruleset.Ruleset;
import ruleset.Stop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestStop {

    @Test
    public void testStop_name() {

        Ruleset rs = new Stop();

        assertEquals("STOP", rs.returnName());

    }

    @Test
    public void testStop_explanation() {

        Ruleset rs = new Stop();

        assertEquals("The STOP card.Card stops the turn when drawn", rs.explainRules());

    }

    @Test
    public void test_creation() {
        Stop stop = new Stop();
        List<DiceCombo> combos = new ArrayList<>();
        assertEquals(combos, stop.returnValidCombos());
    }

}