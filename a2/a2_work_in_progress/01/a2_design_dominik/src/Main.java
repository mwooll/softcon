import card.Card;
import die.DiceCombo;
import die.DiceSet;
import die.DieValue;
import ruleset.Default;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

//        System.out.println("Main executed.");

        // Test the Seed
        DiceSet diceset = new DiceSet(1);
//        System.out.println(diceset);

        diceset.returnCombos();

    }
}
