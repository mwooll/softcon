package gamemodel;

public interface GameModelView {

    boolean checkPlayerName(String pPlayerName, int index);

    void setPlayerName(String pPlayerName, int index);

    boolean allPlayersSet();

}
