package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestTableau {

    Tableau tableau = new Tableau();

    @Test
    public void test_add() {
        tableau.add("p1");
        tableau.printTableau();
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
        tableau.decrease();
        assertEquals(100, tableau.getPoints("p1"));
    }

    @Test
    public void test_decrease_below0() {
        tableau.add("p1");
        tableau.add("p2");
        tableau.update("p1", 50);
        tableau.decrease();
        assertEquals(0, tableau.getPoints("p1"));
    }




}