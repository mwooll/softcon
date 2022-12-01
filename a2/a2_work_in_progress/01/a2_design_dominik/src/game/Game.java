package game;
import card.*;
import die.DiceSet;
import ruleset.Cloverleaf;
import ruleset.Ruleset;

import java.util.ArrayList;
import java.util.List;

public class Game {

    /**
     * The Game class orchestrates the whole game. It is responsible for
     * initializing the players
     * play turn
     *
     * todo: remove debugging boolean after finished
     * todo: remove discardPile completely, we don't need it. Keep everything in deck.
     * todo: Method drawCard at beginning of playTurn
     * todo: Method finishUpRound at end of playTurn
     */

    // aPlayers contains the order in which the players take their turns
    private final List<String> aPlayers = new ArrayList<>();
    private final int aWinCondition;
    private final Deck aDeck;
    private final Tableau aTableau = new Tableau();
    private InputParser aParser;

    private final boolean aDebug; // remove later

    /**
     * Constructor does initialize the game
     * @param pDebug specify if the DiceSet with a fixed roll ONE or a real random DiceSet is fetched by Round
     * @param pDeckSpec specifying the type of Deck to be used for the game
     */
    public Game(boolean pDebug, DeckSpec pDeckSpec, InputParser pParser) {

        // for debugging, using the dice set
        aDebug = pDebug;

        // Set the input parser to use for the game
        // Can be overridden with the setParser method on a game instance
        setParser(pParser);

        // ask how many players
        int numberPlayers = aParser.askNumberPlayers();
        assert numberPlayers > 0;

        // ask players names
        System.out.println("Order of player names entered will determine order of turns");
        for (int i = 1; i <= numberPlayers; i++) {
            aPlayers.add(aParser.askPlayerName(i, aPlayers));
        }

        // ask how many points win condition
        aWinCondition = aParser.askWinCondition();

        // add each player to tableau
        for (String playerName : aPlayers) {
            aTableau.add(playerName);
        }

        // Initializing the Deck and DiscardPile
        aDeck = new Deck(pDeckSpec);
        aDeck.shuffle();
    }

    /**
     * Set a InputParser for the Game instance
     */
    public void setParser(InputParser pInputParser) {
        aParser = pInputParser;
    }

    /**
     * Given the ordering of players in aPlayers, print for each player the points
     */
    private void printTableau() {
        for (String playerName : aPlayers) {
            System.out.println(playerName + " : " + aTableau.getPoints(playerName));
        }
    }

    /**
     * Determine if a player fulfills the win condition
     */
    private boolean aPlayerHasWon() {return aTableau.aPlayerHasWon(aWinCondition);}

    /**
     * Determine the final winner and announce him
     * In case of tie, announce all players
     * No assertion about empty tableau, init of Game should not allow that through DefaultParser
     */
    private void determineWinner() {
        int maxPoints = -1;
        List<String> winners = new ArrayList<>();

        for (String playerName : aPlayers) {
            // if the player has more or equal points as current maximum, reset maxPoints
            int pPoints = aTableau.getPoints(playerName);
            if (pPoints >= maxPoints) {maxPoints = pPoints;}
        }
        // Loop a second time, adding all winners to the list
        for (String playerName : aPlayers) {
            // if the player has more or equal points as current maximum, reset maxPoints
            int pPoints = aTableau.getPoints(playerName);
            if (pPoints == maxPoints) {winners.add(playerName);}
        }

        String winnersString = String.join(",", winners);
        System.out.println(String.format("The winner(s) with %s points are", maxPoints));
        System.out.println(winnersString);

    }

    /**
     * Game loop that entails the whole game
     * @return true to test
     */
    public boolean playGame() {

        System.out.println("-------------------- START GAME -----------------");
        printTableau();

        int nTurns = 0;
        boolean keepPlaying = true;

        // as long as no player has reached the win condition, keep playing
        while (keepPlaying) {
            System.out.println(String.format("-------------------- PLAYING TABLEAU NO %s -----------------", nTurns));
            for (String playerName : aPlayers) {
                System.out.println(String.format("-------------------- TURN %s -----------------", playerName));
                boolean cloverleafEndingGame = playTurn(playerName);

                // check if the CLOVERLEAF ends the game
                if (cloverleafEndingGame) {
                    System.out.println(String.format("The CLOVERLEAF ends the game, %s has won!", playerName));
                    return true;
                }

                // check if anybody has the win condition
                if (aPlayerHasWon()) {
                    System.out.println(String.format("A Player has reached the win condition of %s points.\nEvery player can catch up turns then the game ends and the player with the most points wins.", aWinCondition));
                    keepPlaying = false;
                }
            }
        }

        System.out.println(String.format("GAME IS OVER! Determining the winner ... "));
        determineWinner();
        System.out.println("Final Tableau:");
        printTableau();

        return true;

    }

    /**
     * Play a turn of a player
     * The turn lets the player play as many round as he can play
     * At the end of each round, the turn updates the tableau in case there are negative points
     * At the end of the turn, the turn updates the tableau for positive and negative points again
     * @return True if a Cloverleaf Tutto happened, in this case the game is over right away, False otherwise
     */
    private boolean playTurn(String pPlayerName) {

        int turnScore = 0;
        int turnCounter = 0;
        Card turnCurrentCard;
        Ruleset turnCurrentRuleset;
        Round turnCurrentRound;

        // ask if display score or play turn
        while(true) {
            if (aParser.askDisplayScore()) {
                printTableau();
            } else {
                break;
            }
        }

        // Check if Deck still has cards to draw from, if not inform and refresh deck
        if (aDeck.isEmpty()) {
            System.out.println("Deck is used up, reshuffle ...");
            aDeck.refresh();
        }

        // At the beginning of the turn, you always draw a card
        turnCurrentCard = aDeck.draw();
        // initialize a fresh ruleset whenever a card is drawn
        turnCurrentRuleset = turnCurrentCard.returnCardType().getFreshRuleset();

        System.out.println(String.format("Starting Turn with a %s card with ruleset %s", turnCurrentCard.returnCardType(), turnCurrentRuleset.returnName()));

        // init the round instance
        turnCurrentRound = new Round(turnCurrentRuleset);
        // set the parser
        turnCurrentRound.setParser(aParser);

        // debugging only
        if (aDebug) {
            turnCurrentRound.setDiceSet(DiceSet.getDebug());
        }

        // play as many rounds as possible
        while (true) {

            // Check with the current round if we need to draw a new Card.
            // if so, draw a card and create new Round instance
            // Only do that if it's not the first round of the turn
            if (turnCounter > 0 && turnCurrentRound.drawNewCard()) {

                // Check if Deck still has cards to draw from, if not inform and refresh deck
                if (aDeck.isEmpty()) {
                    System.out.println("Deck is used up, reshuffle ...");
                    aDeck.refresh();
                }

                System.out.println("Drawing new card ...");
                turnCurrentCard = aDeck.draw();
                turnCurrentRuleset = turnCurrentCard.returnCardType().getFreshRuleset();
                System.out.println(String.format("Starting Turn with a %s card with a fresh ruleset %s", turnCurrentCard.returnCardType(), turnCurrentRuleset.returnName()));
                turnCurrentRound = new Round(turnCurrentRuleset);
                turnCurrentRound.setParser(aParser);

                // debugging only
                if (aDebug) {
                    turnCurrentRound.setDiceSet(DiceSet.getDebug());
                }

            }

            turnCounter += 1;
            turnScore += turnCurrentRound.playRound();

            // Handle an instant win by CLOVERLEAF. If the current ruleset is CLOVERLEAF and has numAchievedTuttos > 1
            // Handle it by returning 1 right away
            if (turnCurrentRuleset.returnName().equals("CLOVERLEAF")) {
                Cloverleaf turnCurrentRulesetCloverleaf = (Cloverleaf) turnCurrentRuleset;
                if (turnCurrentRulesetCloverleaf.numAchievedTuttos > 1) {
                    return true;
                }
            }

            // Ask if points must be deducted, do that right away
            if (turnCurrentRound.decreasePoints()) {
                aTableau.decrease();
            }

            // if the round was a null, end the turn here. The points were handled in playRound
            if (turnCurrentRound.isNull()) {
                break;
            }

            // if the round was a tutto, ask if the player wants to keep on playing
            // cloverleaf - If tutto and the game has not yet ended, that means the player has 1 Tutto so far
            // fireworks - You need to keep on playing
            if (turnCurrentRound.isTutto()) {
                if (turnCurrentRuleset.returnName().equals("CLOVERLEAF")) {
                    System.out.println("You have to try to score a second Tutto, if you do you win the game!");
                } else if (turnCurrentRuleset.returnName().equals("FIREWORKS")) {
                    System.out.println("You have to keep rolling the dice!");
                } else {
                    if (aParser.askStopAfterTutto()) {
                        break;
                    }
                }

            }

            // if it was neither a null nor tutto, stop playing
            if (!turnCurrentRound.isNull() && !turnCurrentRound.isTutto()) {
                break;
            }

        }

        // Update scores
        aTableau.update(pPlayerName, turnScore);

        // if no CLOVERLEAF ended the game, return 0
        return false;

    }

}
