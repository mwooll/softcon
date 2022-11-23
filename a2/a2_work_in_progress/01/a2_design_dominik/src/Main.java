import die.DiceCombo;
import die.DiceSet;
import die.DieValue;
import game.DebugParser;
import game.InputParser;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

//        System.out.println("Main executed.");

        // Test the Seed vs Random
        DiceSet diceset = DiceSet.getDebug();
        DiceSet diceset_random = DiceSet.get();

        List<DiceCombo> input = new ArrayList<>();
        input.add(DiceCombo.SINGLE_ONE);
        input.add(DiceCombo.SINGLE_TWO);

        InputParser ip = new DebugParser();
        System.out.println(ip.askWhichRemove(input));

    }
}
