package die;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class DiceSet {

    /**
     * A DiceSet contains six Dice which can be rerolled
     * It remembers which Dice are still available to roll and which ones are already rolled
     * It DOES NOT remember the DiceCombos rolled, that's the rounds task
     * There will only be one DiceSet instance for the entire game, implements FLYWEIGHT
     *
     * todo: Remove toString method, only there to visually debug
     */

    private final List<Die> aDiceLeft = new ArrayList<>();
    private final List<Die> aDiceUsed = new ArrayList<>();
    private final int N_DIE = 6;

    // Flyweight Store
    private static final DiceSet[] DICE_SETS = new DiceSet[1];
    private static final DiceSet[] DICE_SETS_DEBUG = new DiceSet[1];

    // Static constructor
    static {
        DICE_SETS[0] = new DiceSet(false);
        DICE_SETS_DEBUG[0] = new DiceSet(true);
    }

    /**
     * Private Constructor to create a DiceSet
     */
    private DiceSet(boolean pDebug) {
        for (int i = 0; i < N_DIE; i++) {
            aDiceLeft.add(new Die(pDebug));
        }
    }


    /**
     * Public static getter for the one DiceSet, either with or without debug
     * @returns DiceSet instance
     */
    public static DiceSet get() {return DICE_SETS[0];}
    public static DiceSet getDebug() {return DICE_SETS_DEBUG[0];}

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
                break;
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
        aDiceLeft.forEach(Die::rollDie);

        // clear all used dice
        aDiceUsed.clear();
    }

    /**
     * Return a list of all possible DiceCombos the remaining dice can form
     */
    public List<DiceCombo> returnCombos() {

        List<DiceCombo> possibleCombos = new ArrayList<>();

        // get the counts that are currently possible
        HashMap<DieValue, Integer> available = getDieValueCount();


        // for each possible DiceComb
        for (DiceCombo dicecombo : DiceCombo.values()) {
            // get the counts how many of which DieValue are necessary
            HashMap<DieValue, Integer> needed = dicecombo.returnCounts();

            // For all the DieValues in needed, the count in available must be at least as big as in needed
            boolean tmpCombo = true;
            for (DieValue dievalue : needed.keySet()) {
                if (available.get(dievalue) < needed.get(dievalue)) {
                    tmpCombo = false;
                }
            }

            // if available has all the counts, add it to the output list
            if (tmpCombo) {
                possibleCombos.add(dicecombo);
            }
        }

        return possibleCombos;
    }

    /**
     * Return a HashMap with DieValue,count specifying how many DieValues there are in aDiceLeft
     */
    private HashMap<DieValue, Integer> getDieValueCount() {

        HashMap<DieValue, Integer> counts = new HashMap<>();

        for (DieValue dievalue : DieValue.values()) {
            counts.put(dievalue, 0);
        }

        for (Die die : aDiceLeft) {
            counts.put(die.getDieValue(), counts.get(die.getDieValue()) + 1);
        }

        return counts;
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
