package terminal;

import initializer.Initializer;
import initializer.TerminalInitializer;
import parser.InitializerParser;
import player.Player;
import player.PlayerColor;
import parser.IParser;

import java.util.ArrayList;
import java.util.List;

public class TerminalMain {
    private final int maxHeight;
    private final int minHeight;
    private final int maxWidth;
    private final int minWidth;

    private Player[] aPlayers = {new Player(), new Player()};

    public static void main(String[] args) {
        TerminalMain aTerminal = new TerminalMain(10, 2, 20, 2);
        aTerminal.initializeGame();
    }
    public TerminalMain(int pMaxHeight, int pMinHeight, int pMaxWidth, int pMinWidth) {
        maxHeight = pMaxHeight;
        minHeight = pMinHeight;
        maxWidth = pMaxWidth;
        minWidth = pMinWidth;
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

        System.out.println("\nCreating player a:");
        while (true) {
            String firstName = pInitializer.choosePlayerName();
            if (pParser.validatePlayerName(aPlayerNames, firstName)) {
                aPlayers[0].setName(firstName);
                break;
            } else {
                System.out.println("Invalid name! Please try again.");
            }
        }
        while (true) {
            PlayerColor firstColor = pInitializer.choosePlayerColor();
            if (pParser.validateColor(aPlayerColors, firstColor)) {
                aPlayers[0].setColor(firstColor);
                break;
            }
            else {
                System.out.println("Invalid color! Please try again.");
            }
        }
        System.out.println("\nCreating player b:");
        while (true) {
            String secondName = pInitializer.choosePlayerName();
            if (pParser.validatePlayerName(aPlayerNames, secondName)) {
                aPlayers[1].setName(secondName);
                break;
            } else {
                System.out.println("Invalid name! Please try again.");
            }
        }
        while (true) {
            PlayerColor secondColor = pInitializer.choosePlayerColor();
            if (pParser.validateColor(aPlayerColors, secondColor)) {
                aPlayers[1].setColor(secondColor);
                break;
            }
            else {
                System.out.println("Invalid color! Please try again.");
            }
        }

    }
}


