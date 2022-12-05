package game;

import die.DiceCombo;
import game.DefaultParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;


class TestDefaultParser {

    @Test
    public void test_askStop_true() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("Y\n".getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        assertTrue(ip.askStop());

    }

    @Test
    public void test_askStop_false() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("N\n".getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        assertFalse(ip.askStop());

    }

    @Test
    public void test_askStop_wrong_right() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("invalidInput\nY\n".getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        assertTrue(ip.askStop());

    }

    @Test
    public void test_askKeepRemoving_true() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("Y\n".getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        assertTrue(ip.askKeepRemoving());

    }

    @Test
    public void test_askKeepRemoving_false() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("N\n".getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        assertFalse(ip.askKeepRemoving());

    }

    @Test
    public void test_askKeepRemoving_wrong_right() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("invalidInput\nY\n".getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        assertTrue(ip.askKeepRemoving());

    }

    @Test
    public void test_askDisplayScore_Display() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("D\n".getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        assertTrue(ip.askDisplayScore());

    }

    @Test
    public void test_askDisplayScore_Roll() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("R\n".getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        assertFalse(ip.askDisplayScore());

    }

    @Test
    public void test_askDisplayScore_invalid_Display() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("Invalid\nR\n".getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        assertFalse(ip.askDisplayScore());

    }

    @Test
    public void test_askWhichRemove_assertion_empty() {

        InputParser ip = new DefaultParser();
        List<DiceCombo> invalidInput = new ArrayList<>();

        assertThrows(AssertionError.class, () -> ip.askWhichRemove(invalidInput));
    }

    @Test
    public void test_askWhichRemove_assertion_null() {

        InputParser ip = new DefaultParser();
        List<DiceCombo> invalidInput = null;

        assertThrows(AssertionError.class, () -> ip.askWhichRemove(invalidInput));
    }

    @Test
    public void test_askWhichRemove_valid_first() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        List<DiceCombo> input = new ArrayList<>();
        input.add(DiceCombo.SINGLE_ONE);

        assertEquals(DiceCombo.SINGLE_ONE, ip.askWhichRemove(input));

    }

    @Test
    public void test_askWhichRemove_valid_second() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("2\n".getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        List<DiceCombo> input = new ArrayList<>();
        input.add(DiceCombo.SINGLE_ONE);
        input.add(DiceCombo.SINGLE_TWO);

        assertEquals(DiceCombo.SINGLE_TWO, ip.askWhichRemove(input));

    }

    @Test
    public void test_askWhichRemove_outofbounds_invalid_valid() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("-1\na\n1\n".getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        List<DiceCombo> input = new ArrayList<>();
        input.add(DiceCombo.SINGLE_ONE);
        input.add(DiceCombo.SINGLE_TWO);

        assertEquals(DiceCombo.SINGLE_ONE, ip.askWhichRemove(input));

    }

    @Test
    public void test_askNumberPlayers() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("invalid\n0\n-1\n3\n".getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        assertEquals(3, ip.askNumberPlayers());

    }

    @Test
    public void test_askWinCondition() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("invalid\n-1\n \n3\n".getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        assertEquals(3, ip.askWinCondition());

    }

    @Test
    public void test_askPlayerName() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream("in_valid\n \nplayer2\n".getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        List<String> tmpPlayers = new ArrayList<>();
        tmpPlayers.add("player1");

        assertEquals("player2", ip.askPlayerName(2, tmpPlayers));

    }

}