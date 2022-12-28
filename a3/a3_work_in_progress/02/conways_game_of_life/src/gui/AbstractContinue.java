package gui;

import initializer.InitializerObservable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import player.PlayerColor;

public class AbstractContinue extends Parent implements IContinue, IInitializerObserver {

    protected final InitializerObservable aObservable;
    protected final Button aButton;

    public AbstractContinue(InitializerObservable pObservable) {

        aObservable = pObservable;

        aButton = new Button("Continue");
        aButton.setVisible(false);

        VBox vbox = new VBox(aButton);
        getChildren().add(vbox);

        aObservable.addObserver(this);

    }

    @Override
    public void nameIsSet(String pName, int pIndex) {}

    @Override
    public void colorIsSet(PlayerColor pPlayerColor, String pName) {}

    @Override
    public void heightIsSet(int pHeight) {}

    @Override
    public void widthIsSet(int pWidth) {}

    @Override
    public void setVisibility(){aButton.setVisible(false);}

    @Override
    public void cellIsChosen() {}

    @Override
    public Button getButton(){return aButton;}
}
