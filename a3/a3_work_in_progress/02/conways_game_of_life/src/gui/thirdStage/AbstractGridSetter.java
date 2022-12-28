package gui.thirdStage;

import gui.ISetter;
import initializer.IInitializerSetterObserver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AbstractGridSetter extends Parent implements ISetter {

    protected IInitializerSetterObserver aObserver;
    protected final Label aLabel = new Label();
    protected final Label aLabelButton = new Label();
    protected final TextField aTextField = new TextField();
    protected final Button aButton = new Button("Set");
    protected final String aDefaultText = "Set ";
    protected final String aIdentifier;

    public AbstractGridSetter(IInitializerSetterObserver pObserver, String pIdentifier) {

        aObserver = pObserver;
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

        // if an empty string is passed, clear the field
        if (tmpText.equals("")) {
            aObserver.setGridDimension(-1, aIdentifier);
        }

        // Check with the Observer if it can be set
        if (aIdentifier.equals("W")) {
            if (!aObserver.validateWidth(tmpText)) {return;}
        } else {
            if (!aObserver.validateHeight(tmpText)) {return;}
        }

        // make int
        int tmpInt = Integer.parseInt(tmpText);

        aObserver.setGridDimension(tmpInt, aIdentifier);

    };}

}
