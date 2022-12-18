package parser;

import cell.Cell;
import initializer.GUIInitializer;
import initializer.Initializer;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InitializerParser implements IParser {

    @Override
    public boolean validatePlayerName(List<String> pPlayers, String pPlayerName) {
        assert pPlayerName != null;

        // only accept a-zA-Z
        Pattern patternLetters = Pattern.compile("^[a-zA-Z0-9\s]+$");
        Matcher matcherLetters = patternLetters.matcher(pPlayerName);
        if (!matcherLetters.find()) {return false;}

        // Check if any player has the same name yet
        return !pPlayers.contains(pPlayerName);
    }

    @Override
    public boolean validateColor(List<Color> pColors, Color pColor) {
        return false;
    }

    @Override
    public boolean validateHeight(int pMaxHeight, int pMinHeight, String pHeight) {

        int outInt;

        // only accept integers
        try {
            outInt = Integer.parseInt(pHeight);
        } catch (NumberFormatException e) {
            return false;
        }

        return outInt <= pMaxHeight && outInt >= pMinHeight;
    }

    @Override
    public boolean validateWidth(int pMaxWidth, int pMinWidth, String pWidth) {

        int outInt;

        // only accept integers
        try {
            outInt = Integer.parseInt(pWidth);
        } catch (NumberFormatException e) {
            return false;
        }

        return outInt <= pMaxWidth && outInt >= pMinWidth;
    }


    @Override
    public boolean validateCell(Cell pCell) {
        return false;
    }
}