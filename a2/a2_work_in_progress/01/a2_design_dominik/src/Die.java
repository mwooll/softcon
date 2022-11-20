import java.util.Random;

public class Die {

    /**
     * Represents a single Die in the game
     * Can represent one of the enum values in DieValue
     * It is not possible to roll the Die, instead a new instance is created
     */

    private final Random rand = new Random();
    private DieValue faceValue;

    /**
     * Create a new Die instance with a random faceValue
     */
    public Die() {
        // random generation of 0 to size(DieValue)
        // set faceValue
    }

}
