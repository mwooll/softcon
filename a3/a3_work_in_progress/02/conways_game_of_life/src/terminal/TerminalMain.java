package terminal;

import initializer.Initializer;
import initializer.TerminalInitializer;
import parser.InitializerParser;
import player.Player;
import player.PlayerColor;
import parser.IParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintStream;

public class TerminalMain {
    private int maxHeight;
    private int minHeight;
    private int maxWidth;
    private int minWidth;

    private final Scanner aScanner;
    private final PrintStream aPrintStream;


    private final Player[] aPlayers = {new Player(), new Player()};

    public static void main(String[] args) {
        TerminalMain aTerminal = new TerminalMain();
        aTerminal.initializeGame();
    }
    public TerminalMain() {
        aScanner = new Scanner(System.in);
        aPrintStream = System.out;
    }

    public TerminalMain(InputStream pInputStream, PrintStream pPrintStream) {
        aScanner = new Scanner(pInputStream);
        aPrintStream = pPrintStream;
    }

    public Player[] getPlayers() { return aPlayers; }

    public boolean setGridDimensionBounds(int pMaxHeight, int pMinHeight, int pMaxWidth, int pMinWidth) {
        if (pMaxHeight < pMinHeight || pMaxWidth < pMinWidth) return false;
        maxHeight = pMaxHeight;
        minHeight = pMinHeight;
        maxWidth = pMaxWidth;
        minWidth = pMinWidth;
        return true;
    }

    public void initializeGame() {
        Initializer aInitializer = new TerminalInitializer();
        IParser aParser = new InitializerParser();

        initializePlayers(aInitializer, aParser);
    }

    public void initializePlayers(Initializer pInitializer, IParser pParser){
        System.out.println("Creating the two players.");
        System.out.println("Turn order is determined by alphabetical order.");
        List<String> aPlayerNames = new ArrayList<>();
        List<PlayerColor> aPlayerColors = new ArrayList<>();

        aPrintStream.println("\nCreating player a:");
        while (true) {
            String firstName = pInitializer.choosePlayerName();
            if (pParser.validatePlayerName(aPlayerNames, firstName)) {
                aPlayerNames.add(firstName);
                aPlayers[0].setName(firstName);
                break;
            } else { aPrintStream.println("Invalid name! Please try again.");
            }
        }
        while (true) {
            PlayerColor firstColor = pInitializer.choosePlayerColor();
            if (pParser.validateColor(aPlayerColors, firstColor)) {
                aPlayerColors.add(firstColor);
                aPlayers[0].setColor(firstColor);
                break;
            }
            else { aPrintStream.println("Invalid color! Please try again.");
            }
        }
        aPrintStream.println("\nCreating player b:");
        while (true) {
            String secondName = pInitializer.choosePlayerName();
            if (pParser.validatePlayerName(aPlayerNames, secondName)) {
                aPlayerNames.add(secondName);
                aPlayers[1].setName(secondName);
                break;
            } else { aPrintStream.println("Invalid name! Please try again.");
            }
        }
        while (true) {
            PlayerColor secondColor = pInitializer.choosePlayerColor();
            if (pParser.validateColor(aPlayerColors, secondColor)) {
                aPlayerColors.add(secondColor);
                aPlayers[1].setColor(secondColor);
                break;
            }
            else { aPrintStream.println("Invalid color! Please try again.");
            }
        }
        aPrintStream.println("\nThe players have been created.");
    }
}


