package parser;

import player.PlayerColor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InitializerParser implements IParser {

    @Override
    public boolean validatePlayerName(List<String> pNamesInUse, String pName) {
        assert pName != null;

        // only accept a-zA-Z
        Pattern patternLetters = Pattern.compile("^[a-zA-Z0-9\s]+$");
        Matcher matcherLetters = patternLetters.matcher(pName);
        if (!matcherLetters.find()) {return false;}

        // Check if any player has the same name yet
        return !pNamesInUse.contains(pName);
    }

    @Override
    public boolean validateColor(List<PlayerColor> pColors, PlayerColor pColor) {
        assert pColor != null;

        // pColor may not be white
        if(pColor == PlayerColor.WHITE) { return false; }

        // Check if any player has the same color yet
        return !pColors.contains(pColor);
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
    public boolean validateCellInGrid(int pHeight, int pWidth) { return false; };
}
