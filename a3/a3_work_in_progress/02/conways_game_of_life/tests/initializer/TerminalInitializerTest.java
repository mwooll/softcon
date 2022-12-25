package initializer;

import cell.Cell;
import cell.Grid;
import player.Player;
import player.PlayerColor;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;


public class TerminalInitializerTest {
    private final int maxHeight = 20;
    private final int minHeight = 1;
    private final int maxWidth = 20;
    private final int minWidth = 1;

    @Test
    public void testChoosePlayerName() {
        String testName = "Tester";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testName.getBytes());
        TerminalInitializer nameInitializer = new TerminalInitializer(inputStream, System.out);
        String playerName = nameInitializer.choosePlayerName();

        assertEquals(testName, playerName);
    }

    @Test
    public void testChoosePlayerColorCamelCasing() {
        String colorString = "Red";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(colorString.getBytes());
        TerminalInitializer colorInitializer = new TerminalInitializer(inputStream, System.out);
        PlayerColor chosenColor = colorInitializer.choosePlayerColor();

        assertEquals(PlayerColor.RED, chosenColor);
    }

    @Test
    public void testChoosePlayerColorAllLowerCase() {
        String colorString = "blue";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(colorString.getBytes());
        TerminalInitializer colorInitializer = new TerminalInitializer(inputStream, System.out);
        PlayerColor chosenColor = colorInitializer.choosePlayerColor();

        assertEquals(PlayerColor.BLUE, chosenColor);
    }

    @Test
    public void testChoosePlayerColorAllUpperCase() {
        String colorString = "GREEN";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(colorString.getBytes());
        TerminalInitializer colorInitializer = new TerminalInitializer(inputStream, System.out);
        PlayerColor chosenColor = colorInitializer.choosePlayerColor();

        assertEquals(PlayerColor.GREEN, chosenColor);
    }

    @Test
    public void testChoosePlayerColorCapslockCamel() {
        String colorString = "oRANGE";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(colorString.getBytes());
        TerminalInitializer colorInitializer = new TerminalInitializer(inputStream, System.out);
        PlayerColor chosenColor = colorInitializer.choosePlayerColor();

        assertEquals(PlayerColor.ORANGE, chosenColor);
    }

    @Test
    public void testChoosePlayerColorReversedCamel() {
        String colorString = "magentA";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(colorString.getBytes());
        TerminalInitializer colorInitializer = new TerminalInitializer(inputStream, System.out);
        PlayerColor chosenColor = colorInitializer.choosePlayerColor();

        assertEquals(PlayerColor.MAGENTA, chosenColor);
    }

    @Test
    public void testChoosePlayerColorStrokeCasing() {
        String colorString = "yElLoW";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(colorString.getBytes());
        TerminalInitializer colorInitializer = new TerminalInitializer(inputStream, System.out);
        PlayerColor chosenColor = colorInitializer.choosePlayerColor();

        assertEquals(PlayerColor.YELLOW, chosenColor);
    }

    @Test
    public void testChoosePlayerColorRejectWhite() {
        String colorString = "white\nOrange";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(colorString.getBytes());
        TerminalInitializer colorInitializer = new TerminalInitializer(inputStream, System.out);
        PlayerColor chosenColor = colorInitializer.choosePlayerColor();

        assertEquals(PlayerColor.ORANGE, chosenColor);
    }

    @Test
    public void testChoosePlayerColorRejectBlack() {
        String colorString = "BLACK\ngreen";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(colorString.getBytes());
        TerminalInitializer colorInitializer = new TerminalInitializer(inputStream, System.out);
        PlayerColor chosenColor = colorInitializer.choosePlayerColor();

        assertEquals(PlayerColor.GREEN, chosenColor);
    }

    @Test
    public void testChoosePlayerColorRejectNonSense() {
        String colorString = "uisuasudf\nMAGENTA";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(colorString.getBytes());
        TerminalInitializer colorInitializer = new TerminalInitializer(inputStream, System.out);
        PlayerColor chosenColor = colorInitializer.choosePlayerColor();

        assertEquals(PlayerColor.MAGENTA, chosenColor);
    }

    @Test
    public void testChoosePlayerColorChooseFirst() {
        String colorString = "Orange\nBlue";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(colorString.getBytes());
        TerminalInitializer colorInitializer = new TerminalInitializer(inputStream, System.out);
        PlayerColor chosenColor = colorInitializer.choosePlayerColor();

        assertEquals(PlayerColor.ORANGE, chosenColor);
    }

    @Test
    public void testChooseGridDimensionHeightValid() {
        String testHeight = "10";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testHeight.getBytes());
        TerminalInitializer dimensionInitializer = new TerminalInitializer(inputStream, System.out);
        int GridHeight = dimensionInitializer.chooseGridDimensionHeight(maxHeight, minHeight);

        assertEquals(10, GridHeight);
    }

    @Test
    public void testChooseGridDimensionHeightRejectOutOfRange() {
        String testHeight = "100\n10";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testHeight.getBytes());
        TerminalInitializer dimensionInitializer = new TerminalInitializer(inputStream, System.out);
        int GridHeight = dimensionInitializer.chooseGridDimensionHeight(maxHeight, minHeight);

        assertEquals(10, GridHeight);
    }


    @Test
    public void testChooseGridDimensionHeightRejectFloat() {
        String testHeight = "2.5\n10";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testHeight.getBytes());
        TerminalInitializer dimensionInitializer = new TerminalInitializer(inputStream, System.out);
        int GridHeight = dimensionInitializer.chooseGridDimensionHeight(maxHeight, minHeight);

        assertEquals(10, GridHeight);
    }

    @Test
    public void testChooseGridDimensionHeightRejectWord() {
        String testHeight = "dog\n10";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testHeight.getBytes());
        TerminalInitializer dimensionInitializer = new TerminalInitializer(inputStream, System.out);
        int GridHeight = dimensionInitializer.chooseGridDimensionHeight(maxHeight, minHeight);

        assertEquals(10, GridHeight);
    }

    @Test
    public void testChooseGridDimensionHeightRejectMixed() {
        String testHeight = "10dogs\n10";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testHeight.getBytes());
        TerminalInitializer dimensionInitializer = new TerminalInitializer(inputStream, System.out);
        int GridHeight = dimensionInitializer.chooseGridDimensionHeight(maxHeight, minHeight);

        assertEquals(10, GridHeight);
    }

    @Test
    public void testChooseGridDimensionWidthPositive() {
        String testWidth = "10";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testWidth.getBytes());
        TerminalInitializer dimensionInitializer = new TerminalInitializer(inputStream, System.out);
        int GridWidth = dimensionInitializer.chooseGridDimensionWidth(maxWidth, minWidth);

        assertEquals(10, GridWidth);
    }


    @Test
    public void testChooseGridDimensionWidthRejectFloat() {
        String testWidth = "2.5\n10";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testWidth.getBytes());
        TerminalInitializer dimensionInitializer = new TerminalInitializer(inputStream, System.out);
        int GridWidth = dimensionInitializer.chooseGridDimensionWidth(maxWidth, minWidth);

        assertEquals(10, GridWidth);
    }

    @Test
    public void testChooseGridDimensionWidthRejectWord() {
        String testWidth = "dog\n10";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testWidth.getBytes());
        TerminalInitializer dimensionInitializer = new TerminalInitializer(inputStream, System.out);
        int GridWidth = dimensionInitializer.chooseGridDimensionWidth(maxWidth, minWidth);

        assertEquals(10, GridWidth);
    }

    @Test
    public void testChooseGridDimensionWidthRejectMixed() {
        String testWidth = "10dogs\n10";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testWidth.getBytes());
        TerminalInitializer dimensionInitializer = new TerminalInitializer(inputStream, System.out);
        int GridWidth = dimensionInitializer.chooseGridDimensionWidth(maxWidth, minWidth);

        assertEquals(10, GridWidth);
    }

    @Test
    public void testChooseCellValid() {
        Grid pGrid = new Grid(100, 100);
        Cell testActual = pGrid.getCell(7, 5);
        String testCell = "7,5";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testCell.getBytes());
        TerminalInitializer cellInitializer = new TerminalInitializer(inputStream, System.out);
        Cell cellCoordinates = cellInitializer.chooseCell(pGrid);

        assertEquals(testActual, cellCoordinates);

    }

    @Test
    public void testChooseCellInvalidSymbol() {
        Grid pGrid = new Grid(100, 100);
        Cell testActual = pGrid.getCell(4, 44);
        String testCell = "2.5\n4,44";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testCell.getBytes());
        TerminalInitializer cellInitializer = new TerminalInitializer(inputStream, System.out);
        Cell cellCoordinates = cellInitializer.chooseCell(pGrid);

        assertEquals(testActual, cellCoordinates);
    }

    @Test
    public void testChooseCellInvalidType() {
        Grid pGrid = new Grid(100, 100);
        Cell testActual = pGrid.getCell(25, 8);
        String testCell = "A,10\n 25,8";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testCell.getBytes());
        TerminalInitializer cellInitializer = new TerminalInitializer(inputStream, System.out);
        Cell cellCoordinates = cellInitializer.chooseCell(pGrid);

        assertEquals(testActual, cellCoordinates);

    }

    @Test
    public void testChooseCellMixedType() {
        Grid pGrid = new Grid(100, 100);
        Cell testActual = pGrid.getCell(45, 0);
        String testCell = "A15,28\n 45,0";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testCell.getBytes());
        TerminalInitializer cellInitializer = new TerminalInitializer(inputStream, System.out);
        Cell cellCoordinates = cellInitializer.chooseCell(pGrid);

        assertEquals(testActual, cellCoordinates);

    }

    @Test
    public void testChooseCellLongInput() {
        Grid pGrid = new Grid(100, 100);
        Cell testActual = pGrid.getCell(90, 34);
        String testCell = "15,500\n90,34";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testCell.getBytes());
        TerminalInitializer cellInitializer = new TerminalInitializer(inputStream, System.out);
        Cell cellCoordinates = cellInitializer.chooseCell(pGrid);

        assertEquals(testActual, cellCoordinates);

    }

    @Test
    public void testTacticalStartingConfigurationTerminate() {
        String inputString = "quit";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        TerminalInitializer gridInitializer = new TerminalInitializer(inputStream, System.out);

        Player playerA = new Player("player a", PlayerColor.YELLOW);
        Player playerB = new Player("player b", PlayerColor.MAGENTA);
        Grid configuredGrid = gridInitializer.tacticalStartingConfiguration(2, 2, playerA, playerB);

        assertEquals(0, configuredGrid.getNumberOfColoredCells());
    }

    @Test
    public void testTacticalStartingConfigurationTerminateValidCell() {
        String inputString = """
                0,0
                quit
                """;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        TerminalInitializer gridInitializer = new TerminalInitializer(inputStream, System.out);

        Player playerA = new Player("player a", PlayerColor.YELLOW);
        Player playerB = new Player("player b", PlayerColor.MAGENTA);
        Grid configuredGrid = gridInitializer.tacticalStartingConfiguration(2, 2, playerA, playerB);

        assertEquals(2, configuredGrid.getNumberOfColoredCells());
        assertEquals(PlayerColor.YELLOW, configuredGrid.getCell(0, 0).getState());
        assertEquals(PlayerColor.MAGENTA, configuredGrid.getCell(1, 1).getState());
    }

    @Test
    public void testTacticalStartingConfigurationTerminateInvalidInput() {
        String inputString = """
                cool
                quit
                """;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        TerminalInitializer gridInitializer = new TerminalInitializer(inputStream, System.out);

        Player playerA = new Player("player a", PlayerColor.YELLOW);
        Player playerB = new Player("player b", PlayerColor.MAGENTA);
        Grid configuredGrid = gridInitializer.tacticalStartingConfiguration(2, 2, playerA, playerB);

        assertEquals(0, configuredGrid.getNumberOfColoredCells());
    }

    @Test
    public void testTacticalStartingConfigurationTerminateDuplicateCell() {
        String inputString = """
                0,0
                1,1
                quit
                """;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        TerminalInitializer gridInitializer = new TerminalInitializer(inputStream, System.out);

        Player playerA = new Player("player a", PlayerColor.YELLOW);
        Player playerB = new Player("player b", PlayerColor.MAGENTA);
        Grid configuredGrid = gridInitializer.tacticalStartingConfiguration(2, 2, playerA, playerB);

        assertEquals(2, configuredGrid.getNumberOfColoredCells());
        assertEquals(PlayerColor.YELLOW, configuredGrid.getCell(0, 0).getState());
        assertEquals(PlayerColor.MAGENTA, configuredGrid.getCell(1, 1).getState());
    }

    @Test
    public void testTacticalStartingConfigurationTerminateFull() {
        String inputString = """
                0,0
                0,1
                """;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        TerminalInitializer gridInitializer = new TerminalInitializer(inputStream, System.out);

        Player playerA = new Player("player a", PlayerColor.YELLOW);
        Player playerB = new Player("player b", PlayerColor.MAGENTA);
        Grid configuredGrid = gridInitializer.tacticalStartingConfiguration(2, 2, playerA, playerB);

        assertEquals(4, configuredGrid.getNumberOfColoredCells());
        assertEquals(PlayerColor.YELLOW, configuredGrid.getCell(0, 0).getState());
        assertEquals(PlayerColor.MAGENTA, configuredGrid.getCell(1, 1).getState());
        assertEquals(PlayerColor.YELLOW, configuredGrid.getCell(0, 1).getState());
        assertEquals(PlayerColor.MAGENTA, configuredGrid.getCell(1, 0).getState());
    }
}
