package die;

import java.util.List;
import java.util.ArrayList;

public class DiceSet {

    /**
     * A DiceSet contains six Dice which can be rerolled
     * It remembers which Dice are still available to roll and which ones are already rolled
     * There will only be one DiceSet instance for the entire game
     *
     * todo: Remove toString method, only there to check seed
     */

    private final List<Die> aDiceLeft = new ArrayList<>();
    private final List<Die> aDiceUsed = new ArrayList<>();
    private final int N_DIE = 6;

    /**
     * Constructor to create a DiceSet
     */
    public DiceSet() {
        for (int i = 0; i < N_DIE; i++) {
            aDiceLeft.add(new Die());
        }
    }

    /**
     * Constructor with seed for debugging
     */
    public DiceSet(int pSeed) {
        for (int i = 0; i < N_DIE; i++) {
            aDiceLeft.add(new Die(pSeed));
        }
    }

    /**
     * Move a single Die with the given DieValue from aDiceLeft to aDiceUsed
     * @pre aDiceLeft is not empty
     * @pre At least one Die in aDiceLeft must have the DieValue pDieValue
     */
    public void moveDie(DieValue pDieValue) {
        assert !isEmpty();
        // check that any Die in aDiceLeft has DieValue pDieValue
        boolean tmpAssert = false;
        for (Die die : aDiceLeft) {
            if (die.getDieValue() == pDieValue) {
                tmpAssert = true;
                break;
            }
        }
        assert tmpAssert;

        // find first Die that has DieValue pDieValue
        int dieIndex = -1;
        for (Die die : aDiceLeft) {
            if (die.getDieValue() == pDieValue) {
                dieIndex = aDiceLeft.indexOf(die);
            }
        }

        // remove that Die from aDiceLeft and add it to aDiceUsed
        aDiceUsed.add(aDiceLeft.remove(dieIndex));

    }

    /**
     * Refresh the DiceSet. Clear used Dice and roll all Dice
     * @pre There must be exactly six dice left in the DiceSet
     */
    public void refresh() {

        assert aDiceUsed.size() + aDiceLeft.size() == N_DIE;

        // copy all objects from aDiceUsed to aDiceLeft
        aDiceLeft.addAll(aDiceUsed);

        // roll all Dice in aDiceLeft
        aDiceLeft.forEach((d) -> d.rollDie());

        // clear all used dice
        aDiceUsed.clear();
    }

    /**
     * Check if all Dice are used
     */
    private boolean isEmpty() {
        return aDiceLeft.size() == 0;
    }

    /**
     * Return how many Dice are left to roll
     */
    public int getLeftSize() {
        return aDiceLeft.size();
    }

    /**
     * Return the immutable total size of the DiceSet
     */
    public int getSize() {return N_DIE;}



    @Override
    public String toString() {
        return String.format("Dice Left : %s\nDice Used : %s", aDiceLeft, aDiceUsed);
    }



}
