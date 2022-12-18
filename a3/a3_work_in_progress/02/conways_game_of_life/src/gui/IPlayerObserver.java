package gui;

import javafx.scene.paint.Color;

public interface IPlayerObserver {


    void nameIsSet(String pName, int pIndex);

    void colorIsSet(Color pColor, String pName);


}
