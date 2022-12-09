package gamemodel;

import gui.IPlayerObserver;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements GameModelView {

    private final String[] aPlayers = new String[2];
    private List<IPlayerObserver> aPlayersObservers = new ArrayList<>();

    public GameModel() {

    }

    public void addPlayersObservers(IPlayerObserver pObserver) {
        aPlayersObservers.add(pObserver);
    }

    @Override
    public void setPlayerName(String pPlayerName, int index) {
        System.out.println("Setting name " + pPlayerName);
        aPlayers[index] = pPlayerName;
        for (IPlayerObserver observer : aPlayersObservers) {
            observer.nameIsSet(pPlayerName);
        }
    }

}
