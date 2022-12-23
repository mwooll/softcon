package player;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public enum PlayerColor {
    BLUE("Blue", Color.BLUE, 'B'),
    RED("Red", Color.RED, 'R'),
    GREEN("Green", Color.GREEN, 'G'),
    ORANGE("Orange", Color.ORANGE, 'O'),
    MAGENTA("Magenta", Color.MAGENTA, 'M'),
    YELLOW("Yellow", Color.YELLOW, 'Y'),
    WHITE("White", Color.WHITE, ' '),
    BLACK("Black", Color.BLACK, 'X');

    private final String aColorName;
    private final Color aColorHex;
    private final char aColorSymbol;


    PlayerColor(String pColorName, Color pColorHex, char pColorSymbol) {
        aColorName = pColorName;
        aColorHex = pColorHex;
        aColorSymbol = pColorSymbol;
    }

    public String getColorName() { return aColorName; }
    public Color getColorHex() { return aColorHex; }
    public char getColorSymbol() { return aColorSymbol; }

    public static List<PlayerColor> getAvailableColors() {
        List<PlayerColor> availableColors = new ArrayList<>();
        for(PlayerColor color: PlayerColor.values()) {
            availableColors.add(color);
        }
        return availableColors;
    }
    public static String getAvailableColorsAsString() {
        return getAvailableColors().toString();
    }
}
