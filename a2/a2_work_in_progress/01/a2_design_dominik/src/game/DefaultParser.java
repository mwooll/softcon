package game;

import die.DiceCombo;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DefaultParser implements InputParser {

    private final Scanner aScanner;
    private final PrintStream aPrintStream;

    /**
     * Default Constructor which uses System.in and System.out
     */
    public DefaultParser() {
        aScanner = new Scanner(System.in);
        aPrintStream = System.out;
    }

    /**
     * Constructor where input and output stream can be specified for tests
     */
    public DefaultParser(InputStream pInputStream, PrintStream pPrintStream) {
        aScanner = new Scanner(pInputStream);
        aPrintStream = pPrintStream;
    }

    /**
     * Asks the user Y or N question, returns true if Y, false if N
     */
    private boolean askYesNo(String pQuestion, String pWarning) {

        while(true) {
            aPrintStream.println(pQuestion);
            String answer = aScanner.nextLine();

            if (answer.equals("Y") || answer.equals("N")) {
                return answer.equals("Y");
            }

            aPrintStream.println(pWarning);
        }

    }

    private boolean askRollDisplay(String pQuestion, String pWarning) {

        while(true) {
            aPrintStream.println(pQuestion);
            String answer = aScanner.nextLine();

            if (answer.equals("R") || answer.equals("D")) {
                return answer.equals("D");
            }

            aPrintStream.println(pWarning);
        }

    }

    private int askInteger(String pQuestion, String pWarning, int pMin, int pMax) {

        while(true) {
            aPrintStream.println(pQuestion);
            String answerString = aScanner.nextLine();

            try {
                int answer = Integer.parseInt(answerString);
                if (answer >= pMin && answer <= pMax) {
                     return answer;
                } else {
                    aPrintStream.println(pWarning);
                }
            } catch (NumberFormatException e) {aPrintStream.println(pWarning);}

        }

    }



    public int askNumberPlayers() {
        return askInteger("How many players are playing? Please enter a number between 2 and 4", "Please enter a single number either 2, 3 or 4", 2, 4);
    }

    public String askPlayerName(int pNumber, List<String> pForbidden) {

        Pattern patternExpected = Pattern.compile("^[a-zA-Z0-9]+$");

        while(true) {
            aPrintStream.println("Please enter the name of player " + pNumber);
            aPrintStream.println("Only use a-z A-Z and numbers");
            String answer = aScanner.nextLine();
            Matcher matcher = patternExpected.matcher(answer);

            if (!pForbidden.contains(answer) && matcher.find()) {
                return answer;
            }

            aPrintStream.println("Name is already taken or contains invalid characters, please chose another one. Names already in use:");
            aPrintStream.println(String.join(",", pForbidden));
        }
    }

    public int askWinCondition() {
        return askInteger("How many points to win the game? Please enter a number. Maximum 100'000", "Please enter a single number bigger than 0, smaller than 100000", 1,100000);
    }

    public boolean askDisplayScore() {
        return askRollDisplay("Do you want to start rolling the dice (R) or display the score (D)? R/D", "Please type R for rolling dice, D for displaying the score");
    };

    public boolean askStopAfterTutto() {
        return askYesNo("You accomplished a Tutto, do you want to stop your whole turn? Y/N", "Please type in Y or N");
    }

    public boolean askStop() {
        return askYesNo("Do you want to stop your round? Y/N", "Please type in Y or N");
    }

    public boolean askKeepRemoving() {
        return askYesNo("Do you want to keep removing dice combinations? Y/N", "Please type in Y or N");
    }

    public DiceCombo askWhichRemove(List<DiceCombo> pList) {

        assert pList != null;
        assert pList.size() > 0;

        aPrintStream.println("You have the following combinations to chose from:");
        int tmp_ct = 1;
        for (DiceCombo dicecombo : pList) {
            aPrintStream.println(String.format("[%o] %s", tmp_ct, dicecombo));
            tmp_ct++;
        }

        boolean tmpContinueWhileLoop = true;
        int tmpSelection = 0;
        aPrintStream.println("Please enter the number of the combination you want to remove:");
        while (tmpContinueWhileLoop) {

            String answerString = aScanner.nextLine();
            try {
                int answer = Integer.parseInt(answerString);
                if (answer >= 1 && answer <= pList.size()) {
                    tmpSelection = answer;
                    tmpContinueWhileLoop = false;
                } else {
                    aPrintStream.println("Please enter a number from the list");
                }
            } catch (NumberFormatException e) {
                aPrintStream.println("Please enter a valid number from the selection above");
            }

        }

        return pList.get(tmpSelection-1);
    }

}
