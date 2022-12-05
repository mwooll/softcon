package die;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestDiceSet {

    DiceSet diceset_debug = DiceSet.getDebug();
    DiceSet diceset = DiceSet.get();
    HashMap<DieValue, Integer> testDieValueCount = new HashMap<>();
    {
        for (DieValue dv : DieValue.values()) {
            testDieValueCount.put(dv, 0);
        }
        testDieValueCount.put(DieValue.ONE, 6);
    }

    /**
     * FLYWEIGHT, must be refreshed before each test
     */
    @BeforeEach
    void init() {
        diceset_debug.refresh();
        diceset.refresh();
    }

    @Test
    public void testCreationSize_random() {
        assertEquals(6, diceset.getSizeLeft());
    }

    @Test
    public void testCreationSize() {
        assertEquals(6, diceset_debug.getSizeLeft());
    }

    @Test
    public void testMoveDie() {

        System.out.println(diceset_debug);

        diceset_debug.moveDie(DieValue.ONE);

        assertEquals(5, diceset_debug.getSizeLeft());

    }

    @Test
    public void testMoveDie_moveAllDice() {
        for (int i = 0; i < diceset_debug.getSize(); i++) {
            diceset_debug.moveDie(DieValue.ONE);
        }

        assertEquals(0, diceset_debug.getSizeLeft());

    }

    @Test
    public void testMoveDie_assertion_empty() {
        for (int i = 0; i < diceset_debug.getSize(); i++) {
            diceset_debug.moveDie(DieValue.ONE);
        }

        assertThrows(AssertionError.class, () -> diceset_debug.moveDie(DieValue.ONE));
    }

    @Test
    public void testMoveDie_assertion_DieValueNotFound() {
        assertThrows(AssertionError.class, () -> diceset_debug.moveDie(DieValue.TWO));
    }

    @Test
    public void testRefresh() {
        for (int i = 0; i < diceset_debug.getSize(); i++) {
            diceset_debug.moveDie(DieValue.ONE);
        }

        diceset_debug.refresh();

        assertEquals(6, diceset_debug.getSizeLeft());

    }

    @Test
    public void testRollRemaining() {

        // ! IN THEORY its possible that two DiceSet yield the same Combos possible to remove

        // do not use the debug set, instead make sure two lists of DiceCombos are not identical
        List<DiceCombo> dc1 = diceset.returnCombos();
        diceset.rollRemaining();
        List<DiceCombo> dc2 = diceset.returnCombos();

        assertNotEquals(dc1, dc2);

    }

    @Test
    public void testRollRemaining_empty() {
        for (int i = 0; i < diceset_debug.getSize(); i++) {
            diceset_debug.moveDie(DieValue.ONE);
        }

        assertThrows(AssertionError.class, () -> diceset_debug.rollRemaining());

    }

    @Test
    public void test_returnCombos() {
        List<DiceCombo> expected = Arrays.asList(DiceCombo.TRIPLET_ONE, DiceCombo.SINGLE_ONE);
        assertEquals(expected, diceset_debug.returnCombos());
    }

    @Test
    public void test_returnCombos_onlyOne() {
        List<DiceCombo> expected = Arrays.asList(DiceCombo.SINGLE_ONE);

        // move all dice to the used
        for (int i = 0; i < diceset_debug.getSize()-1; i++) {
            diceset_debug.moveDie(DieValue.ONE);
        }

        assertEquals(expected, diceset_debug.returnCombos());

    }

    @Test
    public void test_returnCombos_empty() {
        List<DiceCombo> expected = new ArrayList<>();

        // move all dice to the used
        for (int i = 0; i < diceset_debug.getSize(); i++) {
            diceset_debug.moveDie(DieValue.ONE);
        }

        assertEquals(expected, diceset_debug.returnCombos());

    }

    @Test
    public void test_returnMaximalCombos_empty() {
        List<DiceCombo> expected = new ArrayList<>();

        // move all dice to the used
        for (int i = 0; i < diceset_debug.getSize(); i++) {
            diceset_debug.moveDie(DieValue.ONE);
        }

        assertEquals(expected, diceset_debug.returnMaximalCombos());
    }

    @Test
    public void test_returnMaximalCombos_1_ONE() {
        List<DiceCombo> expected = new ArrayList<>();
        expected.add(DiceCombo.SINGLE_ONE);

        // move all but 1 dice to the used
        for (int i = 0; i < (diceset_debug.getSize() - 1); i++) {
            diceset_debug.moveDie(DieValue.ONE);
        }

        assertEquals(expected, diceset_debug.returnMaximalCombos());
    }
    @Test
    public void test_returnMaximalCombos_2_ONE() {
        List<DiceCombo> expected = new ArrayList<>();
        expected.add(DiceCombo.SINGLE_ONE);
        expected.add(DiceCombo.SINGLE_ONE);

        // move all but 2 dice to the used
        for (int i = 0; i < (diceset_debug.getSize() - 2); i++) {
            diceset_debug.moveDie(DieValue.ONE);
        }

        assertEquals(expected, diceset_debug.returnMaximalCombos());
    }
    @Test
    public void test_returnMaximalCombos_3_ONE() {
        List<DiceCombo> expected = new ArrayList<>();
        expected.add(DiceCombo.TRIPLET_ONE);

        // move 3 dice to the used
        for (int i = 0; i < (diceset_debug.getSize() - 3); i++) {
            diceset_debug.moveDie(DieValue.ONE);
        }

        assertEquals(expected, diceset_debug.returnMaximalCombos());
    }
    @Test
    public void test_returnMaximalCombos_4_ONE() {
        List<DiceCombo> expected = new ArrayList<>();
        expected.add(DiceCombo.TRIPLET_ONE);
        expected.add(DiceCombo.SINGLE_ONE);

        // move 2 dice to the used
        for (int i = 0; i < (diceset_debug.getSize() - 4); i++) {
            diceset_debug.moveDie(DieValue.ONE);
        }

        assertEquals(expected, diceset_debug.returnMaximalCombos());
    }
    @Test
    public void test_returnMaximalCombos_5_ONE() {
        List<DiceCombo> expected = new ArrayList<>();
        expected.add(DiceCombo.TRIPLET_ONE);
        expected.add(DiceCombo.SINGLE_ONE);
        expected.add(DiceCombo.SINGLE_ONE);

        // move 1 dice to the used
        diceset_debug.moveDie(DieValue.ONE);

        assertEquals(expected, diceset_debug.returnMaximalCombos());
    }
    @Test
    public void test_returnMaximalCombos_6_ONE() {
        List<DiceCombo> expected = new ArrayList<>();
        expected.add(DiceCombo.TRIPLET_ONE);
        expected.add(DiceCombo.TRIPLET_ONE);

        assertEquals(expected, diceset_debug.returnMaximalCombos());
    }
}