package initializer;

import gui.*;

public interface InitializerObservable {

    void addObserver(IInitializerObserver pObserver);

//    void addPlayerObserver(IPlayerObserver pPlayerObserver);
//    void addGridObserver(IGridObserver pGridObserver);
//    void addContinueObserver(IContinue pContinueObserver);

    boolean playerNamesSet();

    boolean playerColorSet();

    boolean gridSizeSet();

    boolean cellsAreSet();

}
