package initializer;

import cell.Grid;
import player.PlayerColor;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TerminalInitializer implements Initializer{

    private final Scanner aScanner;
    private final PrintStream aPrintStream;

    /**
     * Default Constructor which uses System.in and System.out
     */
    public TerminalInitializer() {
        aScanner = new Scanner(System.in);
        aPrintStream = System.out;
    }

    /**
     * Constructor where input and output stream can be specified for tests
     */
    public TerminalInitializer(InputStream pInputStream, PrintStream pPrintStream) {
        aScanner = new Scanner(pInputStream);
        aPrintStream = pPrintStream;
    }
    @Override
    public String choosePlayerName() {

        aPrintStream.println("Please enter the name of the player ");
        aPrintStream.println("Only use a-z A-Z and numbers");
        return aScanner.nextLine();
    }

    @Override
    public PlayerColor choosePlayerColor() {

        String colorList = PlayerColor.getAvailableColorsAsString();
        Pattern patternExpected = Pattern.compile(colorList, Pattern.CASE_INSENSITIVE);

        while(true) {
            aPrintStream.println("Please choose a color for this player");
            aPrintStream.println("Available colors are " + colorList);
            String answer = aScanner.nextLine().toUpperCase();
            Matcher matcher = patternExpected.matcher(answer);

            if (matcher.find()) {
                String shortenedColorList = colorList.substring(1, colorList.length()-1);
                String[] stringList = shortenedColorList.split(", ");
                for (int index = 0; index < stringList.length; index++) {
                    if (Objects.equals(stringList[index], answer)) {
                        return PlayerColor.getAvailableColors().get(index);
                    }
                }
            }
            aPrintStream.println("Color is not one of the available colors: " + colorList + ". Please choose another one.");
        }
    }

    @Override
    public ArrayList<Integer> chooseGridDimensions() {
        return null;
    }

    @Override
    public Grid createStartingConfiguration() {
        return null;
    }
}
