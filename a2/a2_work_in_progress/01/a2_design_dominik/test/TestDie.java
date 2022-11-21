import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestDie {

    @Test
    public void testCreation() {
        Die die = new Die(1);

        assertEquals(DieValue.ONE, die.getDieValue());
    }
}