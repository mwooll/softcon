import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestRuleset {

    @Test
    public void testDefault_name() {

        Ruleset rs = new Default();

        assertEquals("PLACEHOLDER", rs.returnName());

    }

    @Test
    public void testDefault_explanation() {

        Ruleset rs = new Default();

        assertEquals("PLACEHOLDER", rs.explainRules());

    }
}
