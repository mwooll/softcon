package player;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerColorTest {
    @Test
    public void testWhite() {
        PlayerColor white = PlayerColor.WHITE;
        assertEquals("White", white.getColorName());
        assertEquals(Color.WHITE, white.getColorHex());
        assertEquals(' ', white.getColorSymbol());
    }

    @Test
    public void testBlue() {
        PlayerColor blue = PlayerColor.BLUE;
        assertEquals("Blue", blue.getColorName());
        assertEquals(Color.BLUE, blue.getColorHex());
        assertEquals('B', blue.getColorSymbol());
    }

    @Test
    public void testRed() {
        PlayerColor red = PlayerColor.RED;
        assertEquals("Red", red.getColorName());
        assertEquals(Color.RED, red.getColorHex());
        assertEquals('R', red.getColorSymbol());
    }

    @Test
    public void testGreen() {
        PlayerColor green = PlayerColor.GREEN;
        assertEquals("Green", green.getColorName());
        assertEquals(Color.GREEN, green.getColorHex());
        assertEquals('G', green.getColorSymbol());
    }

    @Test
    public void testOrange() {
        PlayerColor orange = PlayerColor.ORANGE;
        assertEquals("Orange", orange.getColorName());
        assertEquals(Color.ORANGE, orange.getColorHex());
        assertEquals('O', orange.getColorSymbol());
    }

    @Test
    public void testMagenta() {
        PlayerColor magenta = PlayerColor.MAGENTA;
        assertEquals("Magenta", magenta.getColorName());
        assertEquals(Color.MAGENTA, magenta.getColorHex());
        assertEquals('M', magenta.getColorSymbol());
    }

    @Test
    public void testYellow() {
        PlayerColor yellow = PlayerColor.YELLOW;
        assertEquals("Yellow", yellow.getColorName());
        assertEquals(Color.YELLOW, yellow.getColorHex());
        assertEquals('Y', yellow.getColorSymbol());
    }

    @Test
    public void testBlack() {
        PlayerColor black = PlayerColor.BLACK;
        assertEquals("Black", black.getColorName());
        assertEquals(Color.BLACK, black.getColorHex());
        assertEquals('X', black.getColorSymbol());
    }

    @Test
    public void testGetAllColors() {
        List<PlayerColor> expectedColors = new ArrayList<>(
                Arrays.asList(
                        PlayerColor.BLUE, PlayerColor.RED, PlayerColor.GREEN,
                        PlayerColor.ORANGE, PlayerColor.MAGENTA, PlayerColor.YELLOW,
                        PlayerColor.WHITE, PlayerColor.BLACK
                )
        );
        List<PlayerColor> allColors = PlayerColor.getAllColors();
        assertEquals(expectedColors, allColors);
    }

    @Test
    public void testGetAvailableColors() {
        List<PlayerColor> expectedColors = new ArrayList<>(
                Arrays.asList(
                        PlayerColor.BLUE, PlayerColor.RED, PlayerColor.GREEN,
                        PlayerColor.ORANGE, PlayerColor.MAGENTA, PlayerColor.YELLOW
                )
        );
        List<PlayerColor> availableColors = PlayerColor.getAvailableColors();
        assertEquals(expectedColors, availableColors);
    }

    @Test
    public void testGetAvailableColorsAsString() {
        // if we wanted to change it
        //String expectedString = "[Blue, Red, Green, Orange, Magenta, Yellow]";
        String expectedString = "[BLUE, RED, GREEN, ORANGE, MAGENTA, YELLOW]";
        assertEquals(expectedString, PlayerColor.getAvailableColorsAsString());
    }
}
