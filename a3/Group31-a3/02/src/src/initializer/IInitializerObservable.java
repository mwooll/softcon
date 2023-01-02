package initializer;

import gui.*;

public interface IInitializerObservable {

    void addObserver(IInitializerObserver pObserver);

    boolean playerNamesSet();

    boolean playerColorSet();

    boolean gridSizeSet();

    boolean cellsAreSet();

    boolean maxCellsReached();

}
