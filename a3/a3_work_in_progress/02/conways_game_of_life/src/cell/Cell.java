package cell;

import player.PlayerColor;

public class Cell {
    private player.PlayerColor currentState;
    private player.PlayerColor nextState;
    private boolean wasChanged;

    public Cell() {
        currentState = player.PlayerColor.WHITE;
        wasChanged = false;
    }

    /**
     * @pre currentState != player.PlayerColor.WHITE
     */
    public void die() {
        nextState = player.PlayerColor.WHITE;
        wasChanged = true;
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
        wasChanged = true;
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
     * @pre wasChanged == true
     */
    public void resetWasChanged() {
        wasChanged = false;
    }
}
