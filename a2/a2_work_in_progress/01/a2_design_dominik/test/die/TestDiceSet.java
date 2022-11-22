package die;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestDiceSet {

    /*
    create DiceSet where seed of each Die is set to 1, yielding
    ONE
    FOUR
     */

    DiceSet diceset = new DiceSet(1);
    DiceSet dicesetRandom = new DiceSet();

    @Test
    public void testCreationSize_random() {
        assertEquals(6, dicesetRandom.getLeftSize());
    }

    @Test
    public void testCreationSize() {
        assertEquals(6, diceset.getLeftSize());
    }

    @Test
    public void testMoveDie() {
        diceset.moveDie(DieValue.ONE);

        assertEquals(5, diceset.getLeftSize());

    }

    @Test
    public void testMoveDie_moveAllDice() {
        for (int i = 0; i < diceset.getSize(); i++) {
            diceset.moveDie(DieValue.ONE);
        }

        assertEquals(0, diceset.getLeftSize());

    }

    @Test
    public void testMoveDie_assertion_empty() {
        for (int i = 0; i < diceset.getSize(); i++) {
            diceset.moveDie(DieValue.ONE);
        }

        assertThrows(AssertionError.class, () -> diceset.moveDie(DieValue.ONE));
    }

    @Test
    public void testMoveDie_assertion_DieValueNotFound() {
        assertThrows(AssertionError.class, () -> diceset.moveDie(DieValue.TWO));
    }

    @Test
    public void testRefresh() {
        for (int i = 0; i < diceset.getSize(); i++) {
            diceset.moveDie(DieValue.ONE);
        }

        diceset.refresh();

        assertEquals(6, diceset.getLeftSize());

    }


}