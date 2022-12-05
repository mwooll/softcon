package game;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestTableau {

    Tableau tableau = new Tableau();

    @Test
    public void test_add() {
        tableau.add("p1");
        assertEquals(1, tableau.size());
    }

    @Test
    public void test_update() {
        tableau.add("p1");
        tableau.update("p1", 20);
        assertEquals(20, tableau.getPoints("p1"));
    }

    @Test
    public void test_aPlayerHasWon_true() {
        tableau.add("p1");
        tableau.update("p1", 20);
        assertTrue(tableau.aPlayerHasWon(20));
    }

    @Test
    public void test_aPlayerHasWon_false() {
        tableau.add("p1");
        tableau.update("p1", 20);
        assertFalse(tableau.aPlayerHasWon(21));
    }

    @Test
    public void test_decrease_greater0() {
        tableau.add("p1");
        tableau.add("p2");
        tableau.update("p1", 1100);
        tableau.update("p2", 5000);

        HashMap<String, List<Integer>> expected = new HashMap<>();
        expected.put("p2", new ArrayList<>(Arrays.asList(5000,4000)));

        HashMap<String, List<Integer>> actual = new HashMap<>();
        actual = tableau.decrease("p1");

        assertEquals(expected, actual);
        assertEquals(1100, tableau.getPoints("p1"));
    }

    @Test
    public void test_decrease_greater0_twice() {
        tableau.add("p1");
        tableau.add("p2");
        tableau.update("p1", 1100);
        tableau.update("p2", 5000);

        HashMap<String, List<Integer>> expected = new HashMap<>();
        expected.put("p2", new ArrayList<>(Arrays.asList(4000, 3000)));

        HashMap<String, List<Integer>> actual = new HashMap<>();
        actual = tableau.decrease("p1");
        actual = tableau.decrease("p1");

        assertTrue(expected.equals(actual));
        assertEquals(1100, tableau.getPoints("p1"));
    }
//
    @Test
    public void test_decrease_below0() {
        tableau.add("p1");
        tableau.add("p2");
        tableau.update("p1", 100);
        tableau.update("p2", 200);

        HashMap<String, List<Integer>> expected = new HashMap<>();
        expected.put("p2", new ArrayList<>(Arrays.asList(200, 0)));

        HashMap<String, List<Integer>> actual = new HashMap<>();
        actual = tableau.decrease("p1");

        assertTrue(expected.equals(actual));
        assertEquals(100, tableau.getPoints("p1"));
    }
//
    @Test
    public void test_decrease_all0() {
        tableau.add("p1");
        tableau.add("p2");

        HashMap<String, List<Integer>> expected = new HashMap<>();

        HashMap<String, List<Integer>> actual = tableau.decrease("p1");

        assertTrue(expected.equals(actual));
    }
//
    @Test
    public void test_decrease_only_leading_player() {
        tableau.add("p1");
        tableau.add("p2");
        tableau.update("p1", 1100);
        tableau.update("p2", 3600);

        HashMap<String, List<Integer>> expected = new HashMap<>();

        HashMap<String, List<Integer>> actual = tableau.decrease("p2");

        assertTrue(expected.equals(actual));
        assertEquals(3600, tableau.getPoints("p2"));
        assertEquals(1100, tableau.getPoints("p1"));
    }
//
    @Test
    public void test_decrease_only_leading_player_tie() {
        tableau.add("p1");
        tableau.add("p2");
        tableau.add("p3");
        tableau.update("p1", 4500);
        tableau.update("p2", 4500);
        tableau.update("p3", 2600);

        HashMap<String, List<Integer>> expected = new HashMap<>();
        expected.put("p1", new ArrayList<>(Arrays.asList(4500, 3500)));

        HashMap<String, List<Integer>> actual = tableau.decrease("p2");

        assertTrue(expected.equals(actual));
    }




}