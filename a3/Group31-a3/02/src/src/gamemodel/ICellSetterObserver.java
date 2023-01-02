package gamemodel;

import cell.Cell;
import player.Player;

public interface ICellSetterObserver {

    void makeBirthMove(Cell pCell);

    void makeDeleteMove(Cell pCell);

    Player returnCurrentPlayer();

}
