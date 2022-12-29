package initializer;

import gui.*;

public interface InitializerObservable {

    void addObserver(IInitializerObserver pObserver);

    boolean playerNamesSet();

    boolean playerColorSet();

    boolean gridSizeSet();

    boolean cellsAreSet();

    boolean maxCellsReached();

}
