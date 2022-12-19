package initializer;

import cell.Grid;
import player.PlayerColor;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
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
        Pattern patternExpected = Pattern.compile("^[a-zA-Z0-9]+$");

        while(true) {
            aPrintStream.println("Please enter the name of the player ");
            aPrintStream.println("Only use a-z A-Z and numbers");
            String answer = aScanner.nextLine();
            Matcher matcher = patternExpected.matcher(answer);

            if (matcher.find()) {
                return answer;
            }
            System.out.println("Unrecognised symbol was used, please try again.");
        }
    }

    @Override
    public PlayerColor choosePlayerColor() {
        return null;
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
