package initializer;

import gui.IInitializerObserver;
import player.PlayerColor;

public class GUIObserverTest implements IInitializerObserver {

    public IInitializerObservable aObservable;
    public int COUNT;

    public GUIObserverTest(IInitializerObservable pObservable) {
        aObservable = pObservable;
        aObservable.addObserver(this);
    }

    @Override
    public void nameIsSet(String pName, int pIndex) {

    }

    @Override
    public void colorIsSet(PlayerColor pPlayerColor, String pName) {

    }

    @Override
    public void heightIsSet(int pHeight) {

    }

    @Override
    public void widthIsSet(int pWidth) {

    }

    @Override
    public void setVisibility() {

    }

    @Override
    public void cellIsChosen() {

    }
}
