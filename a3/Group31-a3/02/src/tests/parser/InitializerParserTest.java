package parser;

import org.junit.jupiter.api.Test;
import player.PlayerColor;
import cell.Grid;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InitializerParserTest {

    @Test
    public void testValidatePlayerNameValid() {
        List<String> usedNames = new ArrayList<>();
        String newName = "new Name";

        InitializerParser nameParser = new InitializerParser();
        assertTrue(nameParser.validatePlayerName(usedNames, newName));
    }

    @Test
    public void testValidatePlayerNameDuplicate() {
        List<String> usedNames = new ArrayList<>();
        usedNames.add("Name");
        String newName = "Name";

        InitializerParser nameParser = new InitializerParser();
        assertFalse(nameParser.validatePlayerName(usedNames, newName));
    }

    @Test
    public void testValidatePlayerNameForbiddenSymbol() {
        List<String> usedNames = new ArrayList<>();
        String newName = "!+*ç";

        InitializerParser nameParser = new InitializerParser();
        assertFalse(nameParser.validatePlayerName(usedNames, newName));
    }

    @Test
    public void testValidateColorWhite() {
        List<PlayerColor> pColors = new ArrayList<>();
        PlayerColor pColor = PlayerColor.WHITE;

        InitializerParser colorParser = new InitializerParser();
        assertFalse(colorParser.validateColor(pColors, pColor));
    }

    @Test
    public void testValidateColorValid() {
        List<PlayerColor> pColors = new ArrayList<>();
        PlayerColor pColor = PlayerColor.ORANGE;

        InitializerParser colorParser = new InitializerParser();
        assertTrue(colorParser.validateColor(pColors, pColor));
    }

    @Test
    public void testValidateColorDuplicate() {
        List<PlayerColor> pColors = new ArrayList<>();
        pColors.add(PlayerColor.GREEN);
        PlayerColor pColor = PlayerColor.GREEN;

        InitializerParser colorParser = new InitializerParser();
        assertFalse(colorParser.validateColor(pColors, pColor));
    }

    @Test
    public void testValidateHeightValid() {
        int maxHeight = 100;
        int minHeight = 10;
        InitializerParser heightParser = new InitializerParser();

        assertTrue(heightParser.validateHeight(maxHeight, minHeight, "10"));
        assertTrue(heightParser.validateHeight(maxHeight, minHeight, "50"));
        assertTrue(heightParser.validateHeight(maxHeight, minHeight, "100"));
    }

    @Test
    public void testValidateHeightInvalid() {
        int maxHeight = 200;
        int minHeight = 0;
        InitializerParser heightParser = new InitializerParser();

        assertFalse(heightParser.validateHeight(maxHeight, minHeight, "-1"));
        assertFalse(heightParser.validateHeight(maxHeight, minHeight, "2000"));
    }

    @Test
    public void testValidateHeightFormatError() {
        int maxHeight = 200;
        int minHeight = 0;
        InitializerParser heightParser = new InitializerParser();

        assertFalse(heightParser.validateHeight(maxHeight, minHeight, "abc"));
        assertFalse(heightParser.validateHeight(maxHeight, minHeight, "3."));
    }

    @Test
    public void testValidateWidthValid() {
        int maxWidth = 300;
        int minWidth = 30;
        InitializerParser widthParser = new InitializerParser();

        assertTrue(widthParser.validateWidth(maxWidth, minWidth, "30"));
        assertTrue(widthParser.validateWidth(maxWidth, minWidth, "150"));
        assertTrue(widthParser.validateWidth(maxWidth, minWidth, "300"));
    }

    @Test
    public void testValidateWidthInvalid() {
        int maxWidth = 400;
        int minWidth = 40;
        InitializerParser widthParser = new InitializerParser();

        assertFalse(widthParser.validateWidth(maxWidth, minWidth, "-27"));
        assertFalse(widthParser.validateWidth(maxWidth, minWidth, "39"));
        assertFalse(widthParser.validateWidth(maxWidth, minWidth, "401"));
    }

    @Test
    public void testValidateWidthFormatError() {
        int maxWidth = 400;
        int minWidth = 40;
        InitializerParser widthParser = new InitializerParser();

        assertFalse(widthParser.validateWidth(maxWidth, minWidth, "dog"));
        assertFalse(widthParser.validateWidth(maxWidth, minWidth, "4."));
    }

    @Test
    public void testValidateCellInGridValid() {
        int width = 101;
        int height = 101;
        Grid testGrid = new Grid(width, height);
        InitializerParser cellInGridParser = new InitializerParser();

        assertTrue(cellInGridParser.validateCellInGrid(testGrid, 10, 100));
        assertTrue(cellInGridParser.validateCellInGrid(testGrid, 2, 30));
        assertTrue(cellInGridParser.validateCellInGrid(testGrid, 0, 0));
    }

    @Test
    public void testValidateCellInGridInvalid() {
        int width = 200;
        int height = 10;
        Grid testGrid = new Grid(width, height);
        InitializerParser cellInGridParser = new InitializerParser();

        assertFalse(cellInGridParser.validateCellInGrid(testGrid, 10, 100));
        assertFalse(cellInGridParser.validateCellInGrid(testGrid, 2, 30));
        assertFalse(cellInGridParser.validateCellInGrid(testGrid, 0, -5));
    }


}
