package cell;

import gui.ICellObserver;
import player.PlayerColor;

public interface ICellObservable {

    void addObserver(ICellObserver pObserver);

    PlayerColor getState();

    void notifyObserver();

}
