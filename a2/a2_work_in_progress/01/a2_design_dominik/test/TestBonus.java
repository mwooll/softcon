import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class TestBonus {

    @Test
    public void testBonus_name() {

        int pBonusPoints = 200;
        Ruleset rs = new Bonus(pBonusPoints);

        assertEquals(String.format("BONUS %s", pBonusPoints), rs.returnName());

    }

    @Test
    public void testBonus_explanation() {

        int pBonusPoints = 200;
        Ruleset rs = new Bonus(pBonusPoints);

        assertEquals(String.format("The BONUS card gives an extra %s points if a Tutto is accomplished", pBonusPoints), rs.explainRules());

    }

    @Test
    public void testBonus_handleTutto() {

        int pBonusPoints = 100;
        Ruleset rs = new Bonus(pBonusPoints);

        assertEquals(100, rs.handleTutto());

    }

    @Test
    public void testBonus_invalidConstructor() {

        int pBonusPoints = -100;

        assertThrows(AssertionError.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Ruleset rs = new Bonus(pBonusPoints);
            }
        });

    }

}