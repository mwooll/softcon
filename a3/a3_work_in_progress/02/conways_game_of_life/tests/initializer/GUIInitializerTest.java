package initializer;

import cell.Grid;
import gamemodel.GameModelTestInitializer;
import gui.IInitializerObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parser.IParser;
import parser.InitializerParser;
import player.Player;
import player.PlayerColor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GUIInitializerTest {

    GUIInitializer gi;

    @BeforeEach
    public void setup() {
        IParser parser = new InitializerParser();
        gi = new GUITestInitializer(parser);
        gi.createEmptyStartingGrid();

        IInitializerObserver observer = new GUIObserverTest(gi);
        gi.addObserver(observer);
    }

    @Test
    public void testCreationPlayers() {
        List<Player> p = gi.getPlayers();

        // no ordering has taken place yet
        assertEquals(PlayerColor.BLUE, p.get(0).getColor());
        assertEquals("b", p.get(0).getName());
        assertEquals(PlayerColor.RED, p.get(1).getColor());
        assertEquals("a", p.get(1).getName());
    }

    @Test
    public void testNamesSet() {

        assertTrue(gi.playerNamesSet());

        gi.setPlayerName("", 0);

        assertFalse(gi.playerNamesSet());
    }

    @Test
    public void testColorsSet() {

        assertTrue(gi.playerColorSet());

        gi.setPlayerColor(PlayerColor.WHITE, "a");

        assertFalse(gi.playerColorSet());
    }

    @Test
    public void testGridSet() {

        gi.setGridDimension(gi.getMinGridSize(), "H");
        gi.setGridDimension(gi.getMinGridSize(), "W");

        assertTrue(gi.gridSizeSet());

        gi.setGridDimension(-1, "H");
        gi.setGridDimension(-1, "W");

        assertFalse(gi.gridSizeSet());

        gi.setGridDimension(gi.getMaxGridSize()+1, "H");
        gi.setGridDimension(gi.getMaxGridSize()+1, "W");

        assertFalse(gi.gridSizeSet());
    }

    @Test
    public void testChooseCell() {

        Grid g = gi.getGrid();

        gi.chooseCell(g.getCell(0,0));

        assertFalse(gi.cellsAreSet());
        assertTrue(gi.maxCellsReached());
    }

    @Test
    public void testChooseCellValid() {

        Grid g = gi.getGrid();

        gi.chooseCell(g.getCell(0,0));
        gi.chooseCell(g.getCell(0,1));
        gi.chooseCell(g.getCell(0,2));
        gi.chooseCell(g.getCell(1,0));

        assertTrue(gi.cellsAreSet());

    }

    @Test
    public void testChooseCellMax() {

        Grid g = gi.getGrid();

        gi.chooseCell(g.getCell(0,0));
        gi.chooseCell(g.getCell(0,1));
        gi.chooseCell(g.getCell(0,2));
        gi.chooseCell(g.getCell(1,0));
        gi.chooseCell(g.getCell(1,1));
        gi.chooseCell(g.getCell(1,2));
        gi.chooseCell(g.getCell(2,0));
        gi.chooseCell(g.getCell(2,1));

        assertTrue(gi.cellsAreSet());
        assertFalse(gi.maxCellsReached());

    }

    @Test
    public void testValidateName() {
        assertTrue(gi.validatePlayerName("c"));
        assertFalse(gi.validatePlayerName("a"));
    }

    @Test
    public void testValidateColor() {
        assertTrue(gi.validateColorName(PlayerColor.YELLOW));
        assertFalse(gi.validateColorName(PlayerColor.BLUE));
    }

    @Test
    public void testValidateSizes() {
        assertTrue(gi.validateWidth(String.valueOf(gi.getMinGridSize()+1)));
        assertFalse(gi.validateWidth(String.valueOf(-1)));

        assertTrue(gi.validateHeight(String.valueOf(gi.getMinGridSize()+1)));
        assertFalse(gi.validateHeight(String.valueOf(-1)));

    }

    @Test
    public void testEmptyGridCreation() {
        Grid tmpGrid = gi.getGrid();

        assertEquals(3, tmpGrid.getWidth());
        assertEquals(3, tmpGrid.getHeight());
    }
}