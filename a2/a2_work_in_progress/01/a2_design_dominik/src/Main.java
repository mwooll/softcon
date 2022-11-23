import die.DiceCombo;
import die.DiceSet;
import die.DieValue;

import java.util.List;

public class Main {

    public static void main(String[] args) {

//        System.out.println("Main executed.");

        // Test the Seed vs Random
        DiceSet diceset = DiceSet.getDebug();
        DiceSet diceset_random = DiceSet.get();

        List<DiceCombo> dc1 = diceset.returnCombos();
        diceset.rollRemaining();
        List<DiceCombo> dc2 = diceset.returnCombos();

    }
}
