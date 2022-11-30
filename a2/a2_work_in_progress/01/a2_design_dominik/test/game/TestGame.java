package game;

import card.DeckSpec;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class TestGame {

    DeckSpec ds = new DeckSpec.DeckSpecBuilder().setCloverleaf(10).build();


    @Test
    public void test_game_ending_cloverleaf_secondTutto() {

        // todo: Input Parser does not work... Cannot test game functions

        /*
        Default Setting, always ONE
        1 - Number of players
        p1 - Name of player
        10000000 - Win Condition
        R - Roll the dice
        1 - Always remove the TRIPLET_ONE
        1
        1
        1
         */

        // make sure that the game ends when a player rolls the second tutto in a row
        String inputString = "1\np1\n10000000\nR\n1\n1\n1\n1\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        Game game = new Game(true, ds, ip);
        game.setParser(ip);

        assertTrue(game.playGame());


    }

}