import die.DiceSet;
import die.DieValue;

public class Main {

    public static void main(String[] args) {

//        System.out.println("Main executed.");

        // Test the Seed vs Random
        DiceSet diceset = DiceSet.getDebug();
        DiceSet diceset_random = DiceSet.get();
        System.out.println(diceset);
        System.out.println(diceset_random);

        diceset.moveDie(DieValue.ONE);

        System.out.println(diceset.getLeftSize());
        System.out.println(diceset);

    }
}
