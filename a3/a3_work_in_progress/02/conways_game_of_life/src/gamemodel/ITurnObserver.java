package gamemodel;

import cell.Cell;
import player.Player;

public interface ITurnObserver {

    void makeBirthMove(Cell pCell);

    void makeDeleteMove(Cell pCell);

    Player returnCurrentPlayer();

}
