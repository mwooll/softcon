package die;

import java.util.Arrays;
import java.util.Random;

public class Die {

    /**
     * Represents a single Die.Die in the game
     * Can represent one of the enum values in Die.DieValue
     */

    protected final Random rand = new Random();
    private DieValue aDieValue;

    /**
     * Create a new Die.Die instance with a random Die.DieValue
     */
    public Die() {
        aDieValue = Arrays.asList(DieValue.values()).get(
                rand.nextInt(DieValue.values().length-1)
        );
    }

    /**
     * Constructor with seed for debugging
     */
    public Die(int pSeed) {

        rand.setSeed(pSeed);

        aDieValue = Arrays.asList(DieValue.values()).get(
                rand.nextInt(DieValue.values().length-1)
        );
    }

    /**
     * Roll the die and set a new Die.DieValue
     */
    public void rollDie() {
        aDieValue = Arrays.asList(DieValue.values()).get(
                rand.nextInt(DieValue.values().length-1)
        );
    }

    public DieValue getDieValue() {return aDieValue;}

    @Override
    public String toString() {
        return aDieValue.name();
    }

}
