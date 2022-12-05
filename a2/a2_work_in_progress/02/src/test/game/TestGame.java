package game;

import card.DeckSpec;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class TestGame {

    DeckSpec ds_cloverleaf = new DeckSpec.DeckSpecBuilder().setCloverleaf(1).build();
    DeckSpec ds_bonus = new DeckSpec.DeckSpecBuilder().setBonus(0,0,0,0,1).build();


    @Test
    public void test_game_ending_cloverleaf_secondTutto() {

        /*
        Default Setting, always ONE
        2 - Number of players
        p1 - Name of players
        p2
        10000000 - Win Condition
        R - Roll the dice
        1 - Always remove the TRIPLET_ONE and keep on removing
        Y
        1
        Y
        1
        Y
        1
         */

        // make sure that the game ends when a player rolls the second tutto in a row
        String inputString = "2\np1\np2\n100000\nR\n1\nY\n1\nY\n1\nY\n1\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        Game game = new Game(true, ds_cloverleaf, ip);
        game.setParser(ip);

        assertTrue(game.playGame());


    }

    @Test
    public void test_game_ending_bonus() {

        /*
        Default Setting, always ONE
        2 - Number of players
        p1 - Name of players
        p2
        100 - Win Condition
        R - Roll the dice
        N - Do not stop round
        1 - Always remove the TRIPLET_ONE
        N - Do not remove any more combinations
        Y - Stop turn
        D - Show Tableau
        R - Roll the dice
        Y - Stop turn
        */

        // let p1 win the game
        String inputString = String.join("\n","2","p1","p2","100","R","N","1","N","Y","D","R","Y");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        Game game = new Game(true, ds_bonus, ip);
        game.setParser(ip);

        assertTrue(game.playGame());

    }

}