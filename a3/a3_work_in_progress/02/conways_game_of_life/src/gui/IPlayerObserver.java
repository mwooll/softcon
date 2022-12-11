package gui;

import gamemodel.GameModel;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public interface IPlayerObserver {


    void nameIsSet(String pName, int pIndex);

    void colorIsSet(Color pColor, String pName);


}
