package game;

import die.DiceSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ruleset.*;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class TestRound {


    Ruleset rsBonus = new Bonus(200);
    Ruleset rsDefault = new Default();

    @Test
    public void test_playRound_Null() {

        Round roundDefault = new Round(rsDefault);

        // Default has empty validCombos, every roll is a null
        assertEquals(0, roundDefault.playRound());

    }

    @Test
    public void test_playRound_StopDirectly() {

        Round roundBonus = new Round(rsBonus);

        ByteArrayInputStream inputStream = new ByteArrayInputStream("J\n".getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        // Set debug DiceSet and Parser in the Round instance
        roundBonus.setDiceSet(DiceSet.getDebug());
        roundBonus.setParser(ip);

        assertEquals(0,roundBonus.playRound());

    }

    @Test
    public void test_playRound_StopAfterOne() {

        /*
         * don't stop
         * remove first option
         * decline removing more
         * (reroll dice)
         * stop
         */

        Round roundBonus = new Round(rsBonus);

        ByteArrayInputStream inputStream = new ByteArrayInputStream("N\n1\nN\nJ\n".getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        // Set debug DiceSet and Parser in the Round instance
        roundBonus.setDiceSet(DiceSet.getDebug());
        roundBonus.setParser(ip);

        assertEquals(100,roundBonus.playRound());

    }

    @Test
    public void test_playRound_Bonus_Tutto() {

        /*
         * N - don't stop
         * 2 - remove second option (TRIPLET_ONE)
         * J - remove more
         * 2 - remove second option (TRIPLET_ONE)
         */

        Round roundBonus = new Round(rsBonus);

        ByteArrayInputStream inputStream = new ByteArrayInputStream("N\n2\nJ\n2\n".getBytes());
        InputParser ip = new DefaultParser(inputStream, System.out);

        // Set debug DiceSet and Parser in the Round instance
        roundBonus.setDiceSet(DiceSet.getDebug());
        roundBonus.setParser(ip);

        assertEquals(2200,roundBonus.playRound());

    }

}