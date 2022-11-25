package game;
import card.*;
import die.DiceSet;
import ruleset.Ruleset;

public class Game {

    /**
     * The Game class orchestrates the whole game. It is responsible for
     * initializing the players
     * play turn
     *
     * todo: remove debugging boolean after finished
     */

    private final int N_PLAYERS = 2;
    private final int WIN_CONDITION = 6000;
    private Deck aDeck;
    private DiscardPile aDiscardPile;
    private Tableau aTableau = new Tableau();
    private InputParser aParser = new DefaultParser();

    private boolean aDebug; // remove later

    /**
     * Constructor does initialize the game
     */
    public Game(boolean pDebug) {

        // for debugging, add one player
        aTableau.add("p1");
        aDebug = pDebug;

        // initialize a tableau
        // ask how many players
        // ask players names
        // add each player to tableau

        // Debug Deck in DeckSpec with fewer cards
        aDeck = new Deck(pDebug);
        aDiscardPile = new DiscardPile();



    }

    /**
     * Determine if a player fulfills the win condition
     */
    private boolean aPlayerHasWon() {return aTableau.aPlayerHasWon(WIN_CONDITION);}

    /**
     * Determine the final winner
     *
     * todo: implement properly, for now just show the tableau
     */
    private void determineWinner() {
        aTableau.printTableau();
    }

    /**
     * Game loop that entails the whole game
     */
    public void playGame() {

        // For each tableau round (one player once)
        // play turn of player X
        // after turn, check if anybody has won
        // if yes, set flag that after tableau round the game will be over
        // after tableau round, either repeat or finish
    }

    /**
     * Play a turn of a player
     * The turn lets the player play as many round as he can play
     * At the end of each round, the turn updates the tableau in case there are negative points
     * At the end of the turn, the turn updates the tableau for positive and negative points again
     */
    public void playTurn(String pPlayerName) {

        int turnScore = 0;
        int turnCounter = 0;
        Card turnCurrentCard;
        Ruleset turnCurrentRuleset;
        Round turnCurrentRound;

        System.out.println(String.format("%s turn", pPlayerName));

        // ask if display score or play turn
        while(true) {
            if (aParser.askDisplayScore()) {
                aTableau.printTableau();
            } else {
                break;
            }
        }

        // todo: Check if deck has cards, if not reshuffle

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
        aTableau.printTableau();

    }

}
