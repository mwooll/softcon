package game;

import die.DiceCombo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;


class TestDebugParser {

    @Test
    public void test_askStop_true() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("J\n".getBytes());
        InputParser ip = new DebugParser(inputStream, System.out);

        assertTrue(ip.askStop());

    }

    @Test
    public void test_askStop_false() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("N\n".getBytes());
        InputParser ip = new DebugParser(inputStream, System.out);

        assertFalse(ip.askStop());

    }

    @Test
    public void test_askStop_wrong_right() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("invalidInput\nJ\n".getBytes());
        InputParser ip = new DebugParser(inputStream, System.out);

        assertTrue(ip.askStop());

    }

    @Test
    public void test_askKeepRemoving_true() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("J\n".getBytes());
        InputParser ip = new DebugParser(inputStream, System.out);

        assertTrue(ip.askKeepRemoving());

    }

    @Test
    public void test_askKeepRemoving_false() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("N\n".getBytes());
        InputParser ip = new DebugParser(inputStream, System.out);

        assertFalse(ip.askKeepRemoving());

    }

    @Test
    public void test_askKeepRemoving_wrong_right() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("invalidInput\nJ\n".getBytes());
        InputParser ip = new DebugParser(inputStream, System.out);

        assertTrue(ip.askKeepRemoving());

    }

    @Test
    public void test_askWhichRemove_assertion_empty() {

        InputParser ip = new DebugParser();
        List<DiceCombo> invalidInput = new ArrayList<>();

        assertThrows(AssertionError.class, () -> ip.askWhichRemove(invalidInput));
    }

    @Test
    public void test_askWhichRemove_assertion_null() {

        InputParser ip = new DebugParser();
        List<DiceCombo> invalidInput = null;

        assertThrows(AssertionError.class, () -> ip.askWhichRemove(invalidInput));
    }

    @Test
    public void test_askWhichRemove_valid_first() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        InputParser ip = new DebugParser(inputStream, System.out);

        List<DiceCombo> input = new ArrayList<>();
        input.add(DiceCombo.SINGLE_ONE);

        assertEquals(DiceCombo.SINGLE_ONE, ip.askWhichRemove(input));

    }

    @Test
    public void test_askWhichRemove_valid_second() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("2\n".getBytes());
        InputParser ip = new DebugParser(inputStream, System.out);

        List<DiceCombo> input = new ArrayList<>();
        input.add(DiceCombo.SINGLE_ONE);
        input.add(DiceCombo.SINGLE_TWO);

        assertEquals(DiceCombo.SINGLE_TWO, ip.askWhichRemove(input));

    }

    @Test
    public void test_askWhichRemove_outofbounds_invalid_valid() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("-1\na\n1\n".getBytes());
        InputParser ip = new DebugParser(inputStream, System.out);

        List<DiceCombo> input = new ArrayList<>();
        input.add(DiceCombo.SINGLE_ONE);
        input.add(DiceCombo.SINGLE_TWO);

        assertEquals(DiceCombo.SINGLE_ONE, ip.askWhichRemove(input));

    }

}