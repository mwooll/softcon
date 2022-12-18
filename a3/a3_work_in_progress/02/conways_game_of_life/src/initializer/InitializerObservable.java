package initializer;

import gui.IContinue;
import gui.IGridObserver;
import gui.IPlayerObserver;

public interface InitializerObservable {

    void addPlayerObserver(IPlayerObserver pPlayerObserver);
    void addGridObserver(IGridObserver pGridObserver);
    void addContinueObserver(IContinue pContinueObserver);

    boolean playerNamesSet();

    boolean playerColorSet();

    boolean gridSizeSet();

}
