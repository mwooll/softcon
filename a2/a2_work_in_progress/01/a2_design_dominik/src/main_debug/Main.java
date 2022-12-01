package main_debug;

import card.*;
import die.*;
import game.*;
import ruleset.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Main {

    public static void main(String[] args) {

//        System.out.println("main_debug.Main executed.");

        // Test sorting of List in DiceSet
        DiceSet ds = DiceSet.get();

        System.out.println(ds);




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
//        DeckSpec ds = new DeckSpec.DeckSpecBuilder().setCloverleaf(1).build();
//        DeckSpec ds = new DeckSpec.DeckSpecBuilder().setStraight(1).build();
//        DeckSpec ds = new DeckSpec.DeckSpecBuilder().setFireworks(2).build();
//        Game g = new Game(false, ds, new DefaultParser());
//        g.playGame();

//        DeckSpec ds = new DeckSpec.DeckSpecBuilder().setCloverleaf(1).build();
//        ByteArrayInputStream inputStream = new ByteArrayInputStream("1\np1\n10000000\nR\n".getBytes());
//        InputParser ip = new DefaultParser(inputStream, System.out);
//
//        Game game = new Game(true, ds, ip);
//        game.playGame();

    }
}
