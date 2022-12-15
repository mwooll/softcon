package cell;

import player.PlayerColor;

public class Cell {
    private player.PlayerColor currentState;
    private player.PlayerColor nextState;
    private boolean changedState;

    public Cell() {
        currentState = player.PlayerColor.WHITE;
        changedState = false;
    }

    /**
     * @pre currentState != player.PlayerColor.WHITE
     */
    public void die() {
        nextState = player.PlayerColor.WHITE;
        changedState = true;
    }

    /**
     * @pre currentState != player.PlayerColor.WHITE
     */
    public void instantDeath() {
        currentState = player.PlayerColor.WHITE;
    }

    /**
     * @pre currentState == player.PlayerColor.WHITE
     */
    public void arrive(player.PlayerColor newState) {
        nextState = newState;
        changedState = true;
    }

    /**
     * @pre currentState == player.PlayerColor.WHITE
     */
    public void instantBirth(player.PlayerColor newState) {
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
}
