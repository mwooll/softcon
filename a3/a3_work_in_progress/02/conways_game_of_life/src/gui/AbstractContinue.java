package gui;

import gamemodel.GameModel;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class AbstractContinue extends Parent implements IContinue {

    protected final GameModel aGameModel;
    protected final Button aButton;

    public AbstractContinue(GameModel pGameModel) {

        aGameModel = pGameModel;

        aButton = new Button("Continue");
        aButton.setVisible(false);

        VBox vbox = new VBox(aButton);
        getChildren().add(vbox);

        aGameModel.addContinueObserver(this);

    }

    @Override
    public void setVisibility(){aButton.setVisible(false);};

    @Override
    public Button getButton(){return aButton;}
}
