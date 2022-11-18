import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestRuleset {

    @Test
    public void testStop_explanation() {

        Ruleset rs = new Stop();

        assertEquals("The STOP Card stops the turn when drawn", rs.explainRules());

    }

    @Test
    public void testDefault_explanation() {

        Ruleset rs = new Default();

        assertEquals("PLACEHOLDER", rs.explainRules());

    }
}
