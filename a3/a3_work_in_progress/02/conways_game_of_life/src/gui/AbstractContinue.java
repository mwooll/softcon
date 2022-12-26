package gui;

import initializer.InitializerObservable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class AbstractContinue extends Parent implements IContinue {

    protected final InitializerObservable aObservable;
    protected final Button aButton;

    public AbstractContinue(InitializerObservable pObservable) {

        aObservable = pObservable;

        aButton = new Button("Continue");
        aButton.setVisible(false);

        VBox vbox = new VBox(aButton);
        getChildren().add(vbox);

        aObservable.addContinueObserver(this);

    }

    @Override
    public void setVisibility(){aButton.setVisible(false);};

    @Override
    public Button getButton(){return aButton;}
}
