package player;

import javafx.scene.paint.Color;

public enum PlayerColor {
    BLUE("Blue", Color.BLUE, 'B'),
    RED("Red", Color.RED, 'R'),
    GREEN("Green", Color.GREEN, 'G'),
    ORANGE("Orange", Color.ORANGE, 'O'),
    MAGENTA("Magenta", Color.MAGENTA, 'M'),
    YELLOW("Yellow", Color.YELLOW, 'Y');

    private final String aColorName;
    private final Color aColorPaint;
    private final char aColorSymbol;


    PlayerColor(String pColorName, Color pColorPaint, char pColorSymbol) {
        aColorName = pColorName;
        aColorPaint = pColorPaint;
        aColorSymbol = pColorSymbol;
    }

    public String getColorName() { return aColorName; }
    public Color getColorPaint() { return aColorPaint; }
    public char getColorSymbol() { return aColorSymbol; }
}
