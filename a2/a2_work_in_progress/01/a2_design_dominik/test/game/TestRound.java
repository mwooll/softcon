package game;

import die.DiceSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ruleset.*;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class TestRound {


    Ruleset rsPlusMinus = new PlusMinus();
    Ruleset rsCloverleaf = new Cloverleaf();
    Ruleset rsFireworks = new Fireworks();
    Ruleset rsBonus = new Bonus(200);
    Ruleset rsDefault = new Default();

    @Test
    public void test_playRound_StopDirectly() {

        Round roundBonus = new Round(rsBonus);

        ByteArrayInputStream inputStream = new ByteArrayInputStream("Y\n".getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        // Set debug DiceSet and Parser in the Round instance
        roundBonus.setDiceSet(DiceSet.getDebug());
        roundBonus.setParser(ip);

        assertEquals(2000,roundBonus.playRound());

    }

    @Test
    public void test_playRound_StopAfterOne() {

        /*
         * N - don't stop
         * 1 - remove first option = TRIPLET_ONE
         * N - decline removing more
         * (reroll dice)
         * Y - stop
         */

        Round roundBonus = new Round(rsBonus);

        ByteArrayInputStream inputStream = new ByteArrayInputStream("N\n1\nN\nY\n".getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        // Set debug DiceSet and Parser in the Round instance
        roundBonus.setDiceSet(DiceSet.getDebug());
        roundBonus.setParser(ip);

        assertEquals(1000,roundBonus.playRound());

    }

    @Test
    public void test_playRound_Bonus_Tutto() {

        /*
         * N - don't stop
         * 1 - remove first option (TRIPLET_ONE)
         * Y - remove more
         * 1 - remove first option (TRIPLET_ONE)
         */

        Round roundBonus = new Round(rsBonus);

        ByteArrayInputStream inputStream = new ByteArrayInputStream("N\n1\nY\n1\n".getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        // Set debug DiceSet and Parser in the Round instance
        roundBonus.setDiceSet(DiceSet.getDebug());
        roundBonus.setParser(ip);

        assertEquals(2200,roundBonus.playRound());

    }

    @Test
    public void test_playRound_Fireworks() {


        Round roundFireworks = new Round(rsFireworks);

        roundFireworks.setDiceSet(DiceSet.getDebug());

        assertEquals(2000,roundFireworks.playRound());

    }

    @Test
    public void test_playRound_Cloverleaf() {

        /*
         * cant stop
         * 1 - remove first option (TRIPLET_ONE)
         * Y - remove more
         * 1 - remove first option (TRIPLET_ONE)
         */

        Round roundCloverleaf = new Round(rsCloverleaf);

        ByteArrayInputStream inputStream = new ByteArrayInputStream("1\nY\n1\n".getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        roundCloverleaf.setDiceSet(DiceSet.getDebug());
        roundCloverleaf.setParser(ip);

        assertEquals(0,roundCloverleaf.playRound());

    }


    @Test
    public void test_playRound_PlusMinus() {

        /*
         * cant stop
         * 1 - remove first option (TRIPLET_ONE)
         * Y - remove more
         * 1 - remove first option (TRIPLET_ONE)
         */

        Round roundPlusMinus = new Round(rsPlusMinus);

        ByteArrayInputStream inputStream = new ByteArrayInputStream("1\nY\n1\n".getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        roundPlusMinus.setDiceSet(DiceSet.getDebug());
        roundPlusMinus.setParser(ip);

        assertEquals(1000,roundPlusMinus.playRound());

    }

}