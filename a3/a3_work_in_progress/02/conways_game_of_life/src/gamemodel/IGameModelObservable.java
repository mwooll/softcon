package gamemodel;

import gui.IGameModelObserver;
import player.Player;

public interface IGameModelObservable {

    void addObserver(IGameModelObserver pObserver);

    boolean getStatusCellDeleted();
    boolean getStatusCellCreated();

    Player returnCurrentPlayer();
    int returnCurrentTurnNumber();

    void notifyObservers();

}
