package game;

import card.DeckSpec;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestGame {

    DeckSpec ds = new DeckSpec.DeckSpecBuilder().setDefault().build();
    Game game = new Game(true, ds);



}