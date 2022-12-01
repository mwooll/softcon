package die;

import java.util.Arrays;
import java.util.Random;

public class Die implements Comparable<Die> {

    /**
     * Represents a single Die.Die in the game
     * Can represent one of the enum values in Die.DieValue
     */

    protected final Random rand = new Random();
    private DieValue aDieValue;
    private boolean aDebug;

    /**
     * Create a new Die.Die instance with a random Die.DieValue
     */
    public Die(boolean pDebug) {

        // set Debug Parameter
        aDebug = pDebug;

        // roll the die
        rollDie();

    }

    /**
     * Roll the die and set a new Die.DieValue
     */
    public void rollDie() {

        // pick value at random
        int randIndex = rand.nextInt(DieValue.values().length);

        // if debug active, always set same index
        if (aDebug) {
            randIndex = 0;
        }

        aDieValue = Arrays.asList(DieValue.values()).get(randIndex);
    }

    public DieValue getDieValue() {return aDieValue;}

    @Override
    public String toString() {
        return aDieValue.name();
    }

    public int compareTo(Die pDie) {
        return aDieValue.ordinal() - pDie.aDieValue.ordinal();
    }

}
