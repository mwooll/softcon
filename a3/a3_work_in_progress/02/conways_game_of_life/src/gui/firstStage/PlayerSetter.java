package gui.firstStage;

import gamemodel.GameModel;

import gui.firstStage.AbstractPlayerSetter;
import initializer.InitializerObserver;
import javafx.scene.layout.HBox;

public class PlayerSetter extends AbstractPlayerSetter {

    public PlayerSetter(InitializerObserver pObserver, int pIndex) {
        super(pObserver, pIndex);
    }

}
