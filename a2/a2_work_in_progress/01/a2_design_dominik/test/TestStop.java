import org.junit.jupiter.api.Test;

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

        assertEquals("The STOP Card stops the turn when drawn", rs.explainRules());

    }

}