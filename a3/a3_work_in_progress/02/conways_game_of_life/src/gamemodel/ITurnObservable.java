package gamemodel;

import gui.ICellObserver;
import gui.ITurnObserver;
import player.PlayerColor;

public interface ITurnObservable {

    void addObserver(ITurnObserver pObserver);

    boolean getStatusCellDeleted();
    boolean getStatusCellCreated();

    void notifyObservers();

}
