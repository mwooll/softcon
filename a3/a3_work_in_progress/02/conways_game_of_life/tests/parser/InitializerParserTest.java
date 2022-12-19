package parser;

import org.junit.jupiter.api.Test;
import player.PlayerColor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InitializerParserTest {

    @Test
    public void testValidatePlayerNameValid() {
        List<String> usedNames = new ArrayList<String>();
        String newName = "new Name";

        InitializerParser nameParser = new InitializerParser();
        assertTrue(nameParser.validatePlayerName(usedNames, newName));
    }

    @Test
    public void testValidatePlayerNameDuplicate() {
        List<String> usedNames = new ArrayList<String>();
        usedNames.add("Name");
        String newName = "Name";

        InitializerParser nameParser = new InitializerParser();
        assertFalse(nameParser.validatePlayerName(usedNames, newName));
    }

    @Test
    public void testValidatePlayerNameForbiddenSymbol() {
        List<String> usedNames = new ArrayList<String>();
        String newName = "!+*ç";

        InitializerParser nameParser = new InitializerParser();
        assertFalse(nameParser.validatePlayerName(usedNames, newName));
    }

    @Test
    public void testValidateColorWhite() {
        List<PlayerColor> pColors = new ArrayList<PlayerColor>();
        PlayerColor pColor = PlayerColor.WHITE;

        InitializerParser colorParser = new InitializerParser();
        assertFalse(colorParser.validateColor(pColors, pColor));
    }

    @Test
    public void testValidateColorValid() {
        List<PlayerColor> pColors = new ArrayList<PlayerColor>();
        PlayerColor pColor = PlayerColor.ORANGE;

        InitializerParser colorParser = new InitializerParser();
        assertTrue(colorParser.validateColor(pColors, pColor));
    }

    @Test
    public void testValidateColorDuplicate() {
        List<PlayerColor> pColors = new ArrayList<PlayerColor>();
        pColors.add(PlayerColor.GREEN);
        PlayerColor pColor = PlayerColor.GREEN;

        InitializerParser colorParser = new InitializerParser();
        assertFalse(colorParser.validateColor(pColors, pColor));
    }
}
