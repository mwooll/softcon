import die.DiceSet;
import game.Round;
import ruleset.*;

public class Main {

    public static void main(String[] args) {

//        System.out.println("Main executed.");

        // Test the Seed vs Random
//        DiceSet diceset = DiceSet.getDebug();
//        DiceSet diceset_random = DiceSet.get();

        // Test Round
//        DiceSet ds = DiceSet.getDebug();
//        List<DiceCombo> dc = ds.returnCombos();
//        dc.forEach(System.out::println);
//
//        List<DiceCombo> smaller = new ArrayList<>();
//        smaller.add(DiceCombo.SINGLE_ONE);
//
//        List<DiceCombo> tmp = new ArrayList<>(ds.returnCombos());
//        tmp.retainAll(smaller);
//
//        tmp.forEach(System.out::println);

//        Ruleset rs = new Bonus(200);
//        Round round = new Round(rs);
//        round.setDiceSet(DiceSet.getDebug());
//
//        int mypoints = round.playRound();

        Ruleset rs = new Straight();


    }
}
