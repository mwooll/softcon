package cell;

import gui.ICellObserver;
import player.PlayerColor;

public class Cell implements ICellObservable {
    private PlayerColor currentState;
    private PlayerColor nextState;
    private boolean changedState;
    private ICellObserver aObserver;

    public Cell() {
        currentState = PlayerColor.WHITE;
        changedState = false;
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
        resetChangedState();
    }

    @Override
    public void addObserver(ICellObserver pObserver) {
        aObserver = pObserver;
    }

    @Override
    public void notifyObserver() {

    }
}
