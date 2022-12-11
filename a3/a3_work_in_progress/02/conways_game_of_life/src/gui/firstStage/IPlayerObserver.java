package gui.firstStage;

import javafx.scene.paint.Color;

public interface IPlayerObserver {

    void nameIsSet(String pName, int pIndex);

    // todo: same for color
    void colorIsSet(Color pColor, String pName);


}
