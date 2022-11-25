package ruleset;

import die.DiceCombo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestPlusMinus {

    Ruleset rs = new PlusMinus();

    @Test
    public void test_name() {
        assertEquals("PLUS/MINUS", rs.returnName());
    }

    @Test
    public void test_explanation() {
        assertEquals("You have to try to accomplish a Tutto, you can't stop. If you succeed, you receive exactly 1000 points" +
        "and the leading player has 1000 points deducted. The rolled combinations do not score you any points," +
                "if you roll a null you score 0 points.", rs.explainRules());
    }

    @Test
    public void test_handleTutto() {

        int tmpPoints = 100;

        assertEquals(1000, rs.handleTutto(tmpPoints));

    }

    @Test
    public void test_handleTutto2() {

        int tmpPoints = 0;

        assertEquals(1000, rs.handleTutto(tmpPoints));

    }

}