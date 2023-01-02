package gui;

import player.PlayerColor;

public interface IInitializerObserver {

    void nameIsSet(String pName, int pIndex);
    void colorIsSet(PlayerColor pPlayerColor, String pName);
    void heightIsSet(int pHeight);
    void widthIsSet(int pWidth);
    void setVisibility();
    void cellIsChosen();

}
