package die;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestDie {

    @Test
    public void testCreation() {

        // Test with seed 1 -> yields DieValue.ONE
        Die die = new Die(1);

        assertEquals(DieValue.ONE, die.getDieValue());
    }
}