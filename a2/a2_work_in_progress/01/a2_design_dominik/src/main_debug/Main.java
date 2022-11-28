package main_debug;

import card.*;
import game.*;
import ruleset.*;

public class Main {

    public static void main(String[] args) {

//        System.out.println("main_debug.Main executed.");

        // Test the Seed vs Random
//        DiceSet diceset = DiceSet.getDebug();
//        DiceSet diceset_random = DiceSet.getPoints();

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

////        Ruleset rs = new Bonus(200);
////        Ruleset rs = new Fireworks();
////        Ruleset rs = new Cloverleaf();
//        Ruleset rs = new PlusMinus();
//        Round round = new Round(rs);
////        round.setDiceSet(DiceSet.getDebug());
//        round.setDiceSet(DiceSet.getPoints());
//
//        int mypoints = round.playRound();



//        // Test out Rulesets in enum, are they different?
//        DeckSpec ds = new DeckSpec.DeckSpecBuilder().setCloverleaf(2).setBonus(1,0,0,0,0).build();
//        Deck deck = new Deck(ds);
//        Card card1 = deck.draw();
//        Card card2 = deck.draw();
//        Card card3 = deck.draw();
//        Ruleset rs1 = card1.returnCardType().getFreshRuleset();
//        Ruleset rs2 = card2.returnCardType().getFreshRuleset();
//        Ruleset rs3 = card3.returnCardType().getFreshRuleset();




        // init a game
//        DeckSpec ds = new DeckSpec.DeckSpecBuilder().setDefault().build();
        DeckSpec ds = new DeckSpec.DeckSpecBuilder().setCloverleaf(5).build();
        Game g = new Game(true, ds);
        g.playGame();


    }
}
