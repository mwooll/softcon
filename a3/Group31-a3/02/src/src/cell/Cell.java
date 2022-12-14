package cell;

import gui.ICellObserver;
import player.PlayerColor;

import java.util.ArrayList;
import java.util.List;

public class Cell implements ICellObservable {
    private PlayerColor currentState;
    private PlayerColor nextState;
    private boolean changedState;
    private List<ICellObserver> aObservers = new ArrayList<>();

    private boolean lives;

    public Cell() {
        currentState = PlayerColor.WHITE;
        changedState = false;
        lives = false;
    }

    /**
     * @pre currentState != PlayerColor.WHITE
     */
    public void die() {
        nextState = PlayerColor.WHITE;
        changedState = true;
    }

    /**
     * @pre currentState != PlayerColor.WHITE
     */
    public void instantDeath() {
        currentState = PlayerColor.WHITE;
        notifyObserver();
        lives = false;
    }

    /**
     * @pre currentState == PlayerColor.WHITE
     * @param newState != PlayerColor.WHITE
     */
    public void arrive(PlayerColor newState) {
        nextState = newState;
        changedState = true;
    }

    /**
     * @pre currentState == PlayerColor.WHITE
     * @param newState != PlayerColor.WHITE
     */
    public void instantBirth(PlayerColor newState) {

        currentState = newState;
        notifyObserver();
        lives = true;
    }

    /**
     * @return currentState
     */
    public PlayerColor getState() { return currentState; }

    /**
     * @return changedState
     */
    public boolean hasStateChanged() { return changedState; }

    /**
     * @return lives
     */
    public boolean isAlive(){ return lives;}
    /**
     * @pre changedState == true
     */
    public void resetChangedState() {
        changedState = false;
    }

    /**
     * @pre (currentState != nextState) and (changedState == true)
     */
    public void updateState() {
        currentState = nextState;
        if (currentState == PlayerColor.WHITE) {
            lives = false;
        }
        else{
            lives = true;
        }
        resetChangedState();
        notifyObserver();
    }

    @Override
    public void addObserver(ICellObserver pObserver) {
        aObservers.add(pObserver);
    }

    @Override
    public void notifyObserver() {
        for (ICellObserver observer : aObservers) {
            observer.stateChanged();
        }
    }
}
