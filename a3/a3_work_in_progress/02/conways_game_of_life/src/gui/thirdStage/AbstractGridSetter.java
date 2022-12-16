package gui.thirdStage;

import gamemodel.GameModel;
import gui.ISetter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AbstractGridSetter extends Parent implements ISetter {

    protected GameModel aGameModel;
    protected final Label aLabel = new Label();
    protected final Label aLabelButton = new Label();
    protected final TextField aTextField = new TextField();
    protected final Button aButton = new Button("Set");
    protected final String aDefaultText = "Set ";
    protected final String aIdentifier;

    public AbstractGridSetter(GameModel pGameModel, String pIdentifier) {

        aGameModel = pGameModel;
        aIdentifier = pIdentifier;

        aLabelButton.setText(String.format("%s %s", aDefaultText, pIdentifier));

        final HBox hBox = new HBox(aLabel, aLabelButton, aTextField, aButton);

        getChildren().add(hBox);

        aButton.setOnAction(handleSet());

    }

    @Override
    public EventHandler<ActionEvent> handleSet() {return actionevent -> {

        String tmpText = aTextField.getText();

        // trim whitespaces
        tmpText = tmpText.trim();

        // validate
        if (!validate(tmpText)) {return;}

        // make int
        int tmpInt = Integer.parseInt(tmpText);

        aGameModel.setGridDimension(tmpInt, aIdentifier);
    };}

    @Override
    public boolean validate(String pInput) {

        int outInt;

        // only accept integers
        try {
            outInt = Integer.parseInt(pInput);
        } catch (NumberFormatException e) {
            return false;
        }

        return outInt >= aGameModel.getMinGridSize() && outInt <= aGameModel.getMaxGridSize();

    }

}
