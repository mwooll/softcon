import card.Card;
import die.DiceSet;
import die.DieValue;
import ruleset.Default;

public class Main {

    public static void main(String[] args) {

//        System.out.println("Main executed.");

        // Test the Seed
        DiceSet diceset = new DiceSet(1);
//        System.out.println(diceset);

        for (int i = 0; i < diceset.getSize(); i++) {
            diceset.moveDie(DieValue.ONE);
        }

        diceset.refresh();

    }
}
