package terminal;

import cell.Cell;
import cell.Grid;
import initializer.Initializer;
import initializer.TerminalInitializer;
import move.Moves;
import parser.IParser;
import parser.InitializerParser;
import player.Player;
import player.PlayerColor;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.lang.Math.max;

public class TerminalMain {
    private int maxHeight = 20;
    private int minHeight = 2;
    private int maxWidth = 20;
    private int minWidth = 2;

    private Player[] sortedPlayers;
    private Player aCurrentPlayer;
    private Grid aGrid;
    private int aTurnNumber;

    private final Scanner aScanner;
    private final PrintStream aPrintStream;
    private final Initializer aInitializer;
    private final IParser aParser;

    public static void main(String[] args) {
        Initializer playerInitializer = new TerminalInitializer();
        IParser playerParser = new InitializerParser();
        TerminalMain aTerminal = new TerminalMain(playerInitializer, playerParser);

        aTerminal.setup();
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
        Player firstPlayer = new Player("A", PlayerColor.ORANGE);
        Player secondPlayer = new Player("Z", PlayerColor.GREEN);
        Player[] pPlayers = {secondPlayer, firstPlayer};
        //Player[] pPlayers = initializePlayers();

        sortedPlayers = orderPlayers(pPlayers);
        aGrid = initializeGrid(sortedPlayers);
        printGrid(aGrid);
        startGame(0);
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
        aGrid = aInitializer.tacticalStartingConfiguration(gridWidth, gridHeight, pPlayers[0], pPlayers[1]);
        return aGrid;
    }

    public void printGrid(Grid pGrid) {
        int pWidth = pGrid.getWidth();
        int pHeight = pGrid.getHeight();
        //String title = "Game of Life";
        // Strings for the Coordinate System
        List<Integer> rangeHeight = IntStream.range(0,pHeight).boxed().toList();
        int numOfDigitsOfMaxHeightNumber = max(String.valueOf(pHeight-1).length(), String.valueOf(pWidth-1).length());
        List<Integer> rangeWidth = IntStream.range(0,pWidth).boxed().toList();
        int numOfDigitsOfMaxWidthNumber = String.valueOf(pWidth-1).length();
        int fullWidth = (rangeWidth.size()+1)*(numOfDigitsOfMaxWidthNumber+1);
        String divider = String.format("%" + fullWidth + "s", "").replace(" ", "-");

        // Printing
        aPrintStream.println(divider);
        //aPrintStream.printf("%" + (fullWidth+title.length())/2 + "s%n", title);

        // First row
        aPrintStream.print(String.format("%1$" + numOfDigitsOfMaxHeightNumber + "s", "") + "|");
        for (int i = 0; i < aGrid.getWidth(); i++) {
            aPrintStream.printf("%1$-" + numOfDigitsOfMaxHeightNumber + "s", rangeWidth.get(i));
            aPrintStream.print("|");
        }
        aPrintStream.print("\n");
        // Rest
        for (int i = 0; i < pHeight; i++) {
            aPrintStream.print(String.format("%1$" + numOfDigitsOfMaxHeightNumber + "s", rangeHeight.get(i)) +"|");
            for (int j = 0; j < pWidth; j++){
                aPrintStream.print(String.format("%1$" + numOfDigitsOfMaxHeightNumber + "s", aGrid.getCell(i, j).getState().getColorSymbol()) + "|");
            }
            aPrintStream.print("\n");
        }
    }

    public PlayerColor hasAPlayerLost() {
        int firstCells = aGrid.getNumberOfMatchingCells(sortedPlayers[0].getColor());
        int secondCells = aGrid.getNumberOfMatchingCells(sortedPlayers[1].getColor());
        if (firstCells == 0 && secondCells == 0) {
            return PlayerColor.BLACK;
        }
        if (firstCells == 0) {
            return sortedPlayers[0].getColor();
        }
        if (secondCells == 0) {
            return sortedPlayers[1].getColor();
        }
        return PlayerColor.WHITE;
    }

    public void determineWinner(PlayerColor loserColor) {
        aPrintStream.println();
        if (loserColor == PlayerColor.BLACK) {
            aPrintStream.println("It's a tie, both players have no living cells left.");
        }
        else if (loserColor == sortedPlayers[0].getColor()) {
            aPrintStream.println(sortedPlayers[0].getName() + " has no living cells left.\n"
                    + sortedPlayers[1].getName() + " has won.");
        }
        else if (loserColor == sortedPlayers[1].getColor()) {
            aPrintStream.println(sortedPlayers[1].getName() + " has no living cells left.\n"
                    + sortedPlayers[0].getName() + " has won.");
        }
        else aPrintStream.println("Nobody has lost yet.");

    }
    public void startGame(int pIndex) {
        aTurnNumber = 0;
        while (hasAPlayerLost() == PlayerColor.WHITE) {
            playTurn(pIndex);
            aPrintStream.println("\nGrid after the moves:");
            printGrid(aGrid);
            aPrintStream.println("\nNext generation:");
            aGrid.generateNextGeneration();
            printGrid(aGrid);
            aTurnNumber++;
            getStatistics();
        }

        PlayerColor loserColor = hasAPlayerLost();
        determineWinner(loserColor);
    }
    public void playTurn(int pIndex) {
        aCurrentPlayer = sortedPlayers[(pIndex + aTurnNumber)%2];
        aPrintStream.println(aCurrentPlayer.getName() + " is the next to make a move.");
        killEnemyCell();
        birthFriendlyCell();
    }

    public void killEnemyCell() {
        aPrintStream.println("Choose an enemy cell to kill it.");
        while (true) {
            Cell aKillCell = getCell();
            if (!Moves.canDeleteCell(aCurrentPlayer, aKillCell)) {
                aPrintStream.println("You can not kill that cell, please try again.");
            }
            else {
                Moves.deleteCell(aKillCell);
                aPrintStream.println("The chosen cell was killed.");
                break;
            }
        }
    }
    public void birthFriendlyCell() {
        aPrintStream.println("Choose a white cell to give birth to.");
        while (true) {
            Cell aBirthCell = getCell();
            if (!Moves.canCreateCell(aBirthCell)) {
                aPrintStream.println("You can not kill that cell, please try again.");
            }
            else {
                Moves.createCell(aCurrentPlayer, aBirthCell);
                aPrintStream.println("The chosen cell is now " + aCurrentPlayer.getColor().getColorName());
                break;
            }
        }
    }
    public Cell getCell() {
        while (true) {
            String input = aScanner.nextLine();
            String[] answerList = input.replaceAll(" ", "").split(",");
            try {
                int row = Integer.parseInt(answerList[0]);
                int column = Integer.parseInt(answerList[1]);
                if (answerList.length == 2 && aParser.validateCellInGrid(aGrid, column, row)) {
                    return aGrid.getCell(row, column);
                }
            } catch (Exception ignored) {
            }
            aPrintStream.println("Please type in the coordinates in the form 'row,column' with row in the range [0, "
                    + (aGrid.getWidth() - 1) + "] and column in the range [0, " + (aGrid.getHeight() - 1));
        }
    }
    public void getStatistics() {
        aPrintStream.println("\nNumber of past generations: " + aTurnNumber);
        aPrintStream.println(sortedPlayers[0].getName() + " has " +
                aGrid.getNumberOfMatchingCells(sortedPlayers[0].getColor()) + " living cells.");
        aPrintStream.println(sortedPlayers[1].getName() + " has " +
                aGrid.getNumberOfMatchingCells(sortedPlayers[1].getColor()) + " living cells.");
    }
}