package initializer;

import cell.Cell;
import cell.Grid;
import parser.IParser;
import parser.InitializerParser;
import player.Player;
import player.PlayerColor;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TerminalInitializer implements Initializer{

    private final Scanner aScanner;
    private final PrintStream aPrintStream;
    private final IParser aParser = new InitializerParser();

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

        aPrintStream.println("Please enter the name of the player");
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
            aPrintStream.println("Color is not one of the available colors: " + colorList + ". Please try again.");
        }
    }

    @Override
    public int chooseGridDimensionHeight(int pMaxHeight, int pMinHeight) {
        while(true) {
            aPrintStream.println("Please choose the height for the grid in the range: ["
                    + pMinHeight + ", " + pMaxHeight + "]");
            String answer = aScanner.nextLine();
            if (aParser.validateHeight(pMaxHeight, pMinHeight, answer)){
                return Integer.parseInt(answer);
            }
            aPrintStream.println(answer + " is either not a number or not in range. Please try again.");
        }
    }

    @Override
    public int chooseGridDimensionWidth(int pMaxWidth, int pMinWidth) {
        while(true) {
            aPrintStream.println("Please choose the width for the grid in the range: ["
                    + pMinWidth + ", " + pMaxWidth + "]");
            String answer = aScanner.nextLine();
            if (aParser.validateWidth(pMaxWidth, pMinWidth, answer)){
                return Integer.parseInt(answer);
            }
            aPrintStream.println(answer + " is either not a number or not in range. Please try again.");
        }
    }

    @Override
    public Grid createStartingConfiguration() { return null; }

    @Override
    public Grid tacticalStartingConfiguration(int gridWidth, int gridHeight, Player choosingPlayer, Player otherPlayer) {
        aPrintStream.println(choosingPlayer.getName() + " gets to choose the starting configuration.");
        Grid aGrid = new Grid(gridWidth, gridHeight);
        int numCells = gridWidth*gridHeight;

        aPrintStream.println("Enter the coordinates of the cells you want to give your color to.");
        aPrintStream.println("If you choose 'x,y', your opponent will get '-x,-y'.");
        aPrintStream.println("Coordinates have the form '4,2'. The first number is for the row and the second for the column.");
        aPrintStream.println("Type 'quit' to stop selecting cells.");

        int coloredCells = 0;
        while (true) {
            String input = aScanner.nextLine();
            if (Objects.equals(input, "quit")) break;

            int[] coordinates = getCoordinates(aGrid, input);
            if (coordinates == null) {
                aPrintStream.println("This is not a valid cell, try again.");
                continue;
            }

            int row = coordinates[0];
            int column = coordinates[1];
            Cell selectedCell = aGrid.getCell(row, column);
            if (selectedCell.getState() != PlayerColor.WHITE) {
                aPrintStream.println("This cell has already been marked.");
                continue;
            }

            int otherRow = gridHeight - row - 1;
            int otherColumn = gridWidth - column - 1;
            if (!aParser.validateCellInGrid(aGrid, otherColumn, otherRow) || (otherRow == row && otherColumn == column)) {
                aPrintStream.println("This cell can not be chosen.");
                continue;
            }

            Cell otherCell = aGrid.getCell(otherRow, otherColumn);
            selectedCell.instantBirth(choosingPlayer.getColor());
            otherCell.instantBirth(otherPlayer.getColor());
            coloredCells = coloredCells + 2;

            aPrintStream.println("Cell at " + row + "," + column + " was marked "
                    + choosingPlayer.getColor().getColorName() + ".");
            aPrintStream.println("Cell at " + otherRow + "," + otherColumn + " was marked "
                    + otherPlayer.getColor().getColorName()+ ".");

            if (coloredCells >= numCells) {
                aPrintStream.println("All cells have been marked.");
                break;
            }
        }
        aPrintStream.println("The grid was configured.");
        return aGrid;
    }

    private int[] getCoordinates(Grid pGrid, String coordinates) {
        String[] answerList = coordinates.replaceAll(" ", "").split(",");
        try {
            int row = Integer.parseInt(answerList[0]);
            int column = Integer.parseInt(answerList[1]);
            if (answerList.length == 2 && aParser.validateCellInGrid(pGrid, column, row)){
                return new int[]{row, column};
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public Cell chooseCell(Grid pGrid) {
        while(true) {
            aPrintStream.println("Enter the coordinates of a starting cell e.g. 4,2");
            aPrintStream.println("The first number is for the row and the second for the column.");
            String answer = aScanner.nextLine();
            String[] answerList = answer.replaceAll(" ", "").split(",");
            try {
                int row = Integer.parseInt(answerList[0]);
                int column = Integer.parseInt(answerList[1]);
                if (answerList.length == 2 && aParser.validateCellInGrid(pGrid, column, row)){
                    return pGrid.getCell(row, column);
                }
                aPrintStream.println(answer + " is not a valid coordinate. Please try again.");
            } catch (Exception e) {
                aPrintStream.println(answer + " is not a valid coordinate. Please try again.");
            }
        }
    }
}

