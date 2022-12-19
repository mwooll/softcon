package gui.firstStage;

import gamemodel.GameModel;
import gui.ISetter;
import initializer.InitializerObserver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractPlayerSetter extends Parent implements ISetter {

    protected InitializerObserver aObserver;
    protected final Label aLabel = new Label();
    protected final TextField aTextField = new TextField();
    protected final Button aButton = new Button("Set");
    protected int aIndex;
    private String DEFAULT_TEXT = "Player ";
    protected String aDefaultText;
    protected String aCurrentText;

    public AbstractPlayerSetter(InitializerObserver pObserver, int pIndex) {
        aObserver = pObserver;
        aIndex = pIndex;
        aDefaultText = DEFAULT_TEXT;

        aCurrentText = String.format("%s %s ", aDefaultText, aIndex + 1);
        aLabel.setText(aCurrentText);

        final HBox hbox = new HBox(aLabel, aTextField, aButton);
        getChildren().add(hbox);

        aButton.setOnAction(handleSet());

    }

    @Override
    public EventHandler<ActionEvent> handleSet() {return actionevent -> {

        String tmpText = aTextField.getText();

        // trim whitespaces
        tmpText = tmpText.trim();

        // if an empty string is passed, clear the name
        if (tmpText.equals("")) {
            aObserver.setPlayerName(tmpText, aIndex);
        }

        // Check with the Observer if this name can be set
        if (aObserver.validatePlayerName(tmpText)) {
            aObserver.setPlayerName(tmpText, aIndex);
        }
    };}

}
