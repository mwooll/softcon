package main;

import card.*;
import game.*;

public class Main {

    public static void main(String[] args) {

        // Setup a "normal" game with the real card deck
        DeckSpec deckspec = new DeckSpec.DeckSpecBuilder().setDefault().build();
        Game game = new Game(false, deckspec, new DefaultParser());
        game.playGame();

    }
}
