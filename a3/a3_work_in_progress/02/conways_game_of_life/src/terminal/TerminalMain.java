package terminal;

import initializer.Initializer;
import initializer.TerminalInitializer;
import parser.InitializerParser;
import player.Player;
import player.PlayerColor;
import parser.IParser;
import cell.Grid;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintStream;
import java.util.stream.IntStream;

public class TerminalMain {
    private int maxHeight = 20;
    private int minHeight = 2;
    private int maxWidth = 20;
    private int minWidth = 2;

    private final Scanner aScanner;
    private final PrintStream aPrintStream;
    private final Initializer aInitializer;
    private final IParser aParser;

    public static void main(String[] args) {
        Initializer playerInitializer = new TerminalInitializer();
        IParser playerParser = new InitializerParser();
        TerminalMain aTerminal = new TerminalMain(playerInitializer, playerParser);

        Player firstPlayer = new Player("A", PlayerColor.ORANGE);
        Player secondPlayer = new Player("Z", PlayerColor.GREEN);
        Player[] aPlayers = {secondPlayer, firstPlayer};

        Grid aGrid = aTerminal.initializeGrid(aPlayers);
    }
    public TerminalMain(Initializer pInitializer, IParser pParser) {
        aInitializer = pInitializer;
        aParser = pParser;
        aScanner = new Scanner(System.in);
        aPrintStream = System.out;
    }

    public TerminalMain(Initializer pInitializer, IParser pParser, InputStream pInputStream, PrintStream pPrintStream) {
        aInitializer = pInitializer;
        aParser = pParser;
        aScanner = new Scanner(pInputStream);
        aPrintStream = pPrintStream;
    }

    public boolean setGridDimensionBounds(int pMaxHeight, int pMinHeight, int pMaxWidth, int pMinWidth) {
        if (pMaxHeight < pMinHeight || pMaxWidth < pMinWidth) return false;
        maxHeight = pMaxHeight;
        minHeight = pMinHeight;
        maxWidth = pMaxWidth;
        minWidth = pMinWidth;
        return true;
    }

    public void setup() {
        Player[] pPlayers = initializePlayers();
        Player[] sortedPlayers = orderPlayers(pPlayers);
        initializeGrid(sortedPlayers);
    }

    public Player[] initializePlayers(){
        aPrintStream.println("Creating the two players.");
        aPrintStream.println("Turn order is determined by alphabetical order.");
        List<String> aPlayerNames = new ArrayList<>();
        List<PlayerColor> aPlayerColors = new ArrayList<>();
        Player[] aPlayers = {new Player(), new Player()};

        aPrintStream.println("\nCreating player a:");
        while (true) {
            String firstName = aInitializer.choosePlayerName();
            if (aParser.validatePlayerName(aPlayerNames, firstName)) {
                aPlayerNames.add(firstName);
                aPlayers[0].setName(firstName);
                break;
            } else { aPrintStream.println("Invalid name! Please try again.");
            }
        }
        while (true) {
            PlayerColor firstColor = aInitializer.choosePlayerColor();
            if (aParser.validateColor(aPlayerColors, firstColor)) {
                aPlayerColors.add(firstColor);
                aPlayers[0].setColor(firstColor);
                break;
            }
            else { aPrintStream.println("Invalid color! Please try again.");
            }
        }
        aPrintStream.println("\nCreating player b:");
        while (true) {
            String secondName = aInitializer.choosePlayerName();
            if (aParser.validatePlayerName(aPlayerNames, secondName)) {
                aPlayerNames.add(secondName);
                aPlayers[1].setName(secondName);
                break;
            } else { aPrintStream.println("Invalid name! Please try again.");
            }
        }
        while (true) {
            PlayerColor secondColor = aInitializer.choosePlayerColor();
            if (aParser.validateColor(aPlayerColors, secondColor)) {
                aPlayerColors.add(secondColor);
                aPlayers[1].setColor(secondColor);
                break;
            }
            else { aPrintStream.println("Invalid color! Please try again.");
            }
        }
        aPrintStream.println("\nThe players have been created.");
        return aPlayers;
    }

    public static Player[] orderPlayers(Player[] pPlayers) {
        int compareValue = pPlayers[0].getName().compareToIgnoreCase(pPlayers[1].getName());
        if (compareValue > 0) {
            Player tmp = pPlayers[0];
            pPlayers[0] = pPlayers[1];
            pPlayers[1] = tmp;
        }
        return pPlayers;
    }
    public Grid initializeGrid(Player[] pPlayers) {
        aPrintStream.println("Setting up the grid.");
        int gridWidth = aInitializer.chooseGridDimensionWidth(maxWidth, minWidth);
        int gridHeight = aInitializer.chooseGridDimensionHeight(maxHeight, minHeight);
        Grid aGrid = aInitializer.tacticalStartingConfiguration(gridWidth, gridHeight, pPlayers[0], pPlayers[1]);
        return aGrid;
    }

    public void printGrid(int pWidth, int pHeight) {
        Grid aGrid = new Grid(pWidth, pHeight);
        String title = "Game of Life";
        // Strings for the Coordinate System
        List<Integer> rangeHeight = IntStream.range(0,pHeight).boxed().toList();
        int numOfDigitsOfMaxHeightNumber = String.valueOf(pHeight-1).length();
        List<Integer> rangeWidth = IntStream.range(0,pWidth).boxed().toList();
        int numOfDigitsOfMaxWidthNumber = String.valueOf(pWidth-1).length();
        int fullWidth = (rangeWidth.size()+1)*(numOfDigitsOfMaxWidthNumber+1);
        String divider = String.format("%" + fullWidth + "s", "").replace(" ", "-");

        // Printing
        System.out.println(divider);
        System.out.printf("%" + (fullWidth+title.length())/2 + "s%n", title);

        // First row
        System.out.print(String.format("%1$" + numOfDigitsOfMaxHeightNumber + "s", "") + "|");
        for (int i = 0; i < aGrid.getWidth(); i++) {
            System.out.printf("%1$-" + numOfDigitsOfMaxHeightNumber + "s", rangeWidth.get(i));
            System.out.print("|");
        }
        System.out.print("\n");
        // Rest
        for (int i = 0; i < pHeight; i++) {
            System.out.print(String.format("%1$" + numOfDigitsOfMaxHeightNumber + "s", rangeHeight.get(i)) +"|");
            for (int j = 0; j < pWidth; j++){
                System.out.print(String.format("%1$" + numOfDigitsOfMaxHeightNumber + "s", aGrid.getCell(i, j).getState().getColorSymbol()) + "|");
            }
            System.out.print("\n");
        }
    }
}