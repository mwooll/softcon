package game;
import card.*;
import die.DiceSet;
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
     * todo:
     */

    // aPlayers contains the order in which the players take their turns
    public List<String> aPlayers = new ArrayList<>();
    private final int aWinCondition;
    private Deck aDeck;
    private final Tableau aTableau = new Tableau();
    private InputParser aParser = new DefaultParser();

    private boolean aDebug; // remove later

    /**
     * Constructor does initialize the game
     * @param pDebug specify if the DiceSet with a fixed roll ONE or a real random DiceSet is fetched by Round
     * @param pDeckSpec specifying the type of Deck to be used for the game
     */
    public Game(boolean pDebug, DeckSpec pDeckSpec) {

        // for debugging, using the dice set
        aDebug = pDebug;

        // Set the input parser to use for the game
        // Can be overridden with the setParser method on a game instance
        setParser(new DefaultParser());

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
     * Determine the final winner
     *
     * todo: implement properly, for now just printTableau
     */
    private void determineWinner() {
        printTableau();
    }

    /**
     * Game loop that entails the whole game
     */
    public void playGame() {

        System.out.println("-------------------- START GAME -----------------");
        printTableau();

        int nTurns = 0;
        int nPlayers = aPlayers.size();
        boolean keepPlaying = true;

        // as long as no player has reached the win condition, keep playing
        while (keepPlaying) {
            System.out.println(String.format("-------------------- TURN %s -----------------", nTurns));
            for (String playerName : aPlayers) {
                playTurn(playerName);

                // check if anybody has the win condition
                if (aPlayerHasWon()) {
                    System.out.println(String.format("A Player has reached the win condition of %s points.\nEvery player can catch up turns then the game ends and the player with the most points wins.", aWinCondition));
                    keepPlaying = false;
                }
            }
        }

        aTableau.announceWinner();

    }

    /**
     * Play a turn of a player
     * The turn lets the player play as many round as he can play
     * At the end of each round, the turn updates the tableau in case there are negative points
     * At the end of the turn, the turn updates the tableau for positive and negative points again
     */
    private void playTurn(String pPlayerName) {

        int turnScore = 0;
        int turnCounter = 0;
        Card turnCurrentCard;
        Ruleset turnCurrentRuleset;
        Round turnCurrentRound;

        System.out.println(String.format("-------------------- %s turn --------------------", pPlayerName));

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
        turnCurrentRuleset = turnCurrentCard.returnCardType().getRuleset();

        System.out.println(String.format("Starting Turn with a %s card with ruleset %s", turnCurrentCard.returnCardType(), turnCurrentRuleset.returnName()));

        // init the round instance
        // todo: Check that a new Round always answers drawNewCard() with false!
        turnCurrentRound = new Round(turnCurrentRuleset);

        // debugging only
        if (aDebug) {
            turnCurrentRound.setDiceSet(DiceSet.getDebug());
        }

        // play as many round as possible
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
                turnCurrentRuleset = turnCurrentCard.returnCardType().getRuleset();
                System.out.println(String.format("Starting Turn with a %s card with ruleset %s", turnCurrentCard.returnCardType(), turnCurrentRuleset.returnName()));
                turnCurrentRound = new Round(turnCurrentRuleset);

                // debugging only
                if (aDebug) {
                    turnCurrentRound.setDiceSet(DiceSet.getDebug());
                }

            }

            turnCounter += 1;
            turnScore += turnCurrentRound.playRound();

            // Ask if points must be deducted, do that right away
            if (turnCurrentRound.decreasePoints()) {
                aTableau.decrease();
            }

            // if the round was a null, end the turn here, give no points
            if (turnCurrentRound.isNull()) {
                turnScore = 0;
                break;
            }

            // if the round was a tutto, ask if the player wants to keep on playing
            if (turnCurrentRound.isTutto()) {
                if (aParser.askStopAfterTutto()) {
                    break;
                }
            }

            // if it was neither a null nor tutto, stop playing
            if (!turnCurrentRound.isNull() && !turnCurrentRound.isTutto()) {
                break;
            }

        }

        // Update scores
        aTableau.update(pPlayerName, turnScore);

        // Check with the round

        System.out.println("Turn ended, current score:");
        printTableau();

    }



}
