package ruleset;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestCloverleaf {

    Cloverleaf cloverleaf = new Cloverleaf();

    @Test
    public void test_name() {

        assertEquals("CLOVERLEAF", cloverleaf.returnName());

    }

    @Test
    public void test_explanation() {

        String expected = "Getting two Tuttos back to back will result in an immediate win. You may not end your turn prematurely and rolling a Null scores you no points.";

        assertEquals(expected, cloverleaf.explainRules());

    }

    @Test
    public void testBonus_handleFirstTutto() {
        assertEquals(0, cloverleaf.handleTutto(100));
    }

    @Test
    public void testBonus_handleSecondTutto() {
        cloverleaf.handleTutto(50);
        assertEquals(100_000_000, cloverleaf.handleTutto(200));
    }

}