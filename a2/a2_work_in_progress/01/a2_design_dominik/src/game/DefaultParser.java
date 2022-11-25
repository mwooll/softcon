package game;

import die.DiceCombo;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

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
     * Asks the user J or N question, returns true if J, false if N
     */
    private boolean askYesNo(String pQuestion, String pWarning) {

        while(true) {
            aPrintStream.println(pQuestion);
            String answer = aScanner.nextLine();

            if (answer.equals("J") || answer.equals("N")) {
                return answer.equals("J");
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

    public boolean askDisplayScore() {
        return askRollDisplay("Do you want to start rolling the dice (R) or display the score (D)? R/D", "Please type R for rolling dice, D for displaying the score");
    };


    public boolean askStopAfterTutto() {
        return askYesNo("You accomplished a Tutto, do you want to stop your whole turn? J/N", "Please type in J or N");
    }

    public boolean askStop() {
        return askYesNo("Do you want to stop your round? J/N", "Please type in J or N");
    }

    public boolean askKeepRemoving() {
        return askYesNo("Do you want to keep removing dice combinations? J/N", "Please type in J or N");
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
