package gui.firstStage;

import gamemodel.GameModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractPlayerSetter extends Parent implements IPlayerSetter {

    protected GameModel aGameModel;
    protected final Label aLabel = new Label();
    protected final TextField aTextField = new TextField();
    protected final Button aButton = new Button("Set");
    protected int aIndex;
    protected String aDefaultText;
    protected String aCurrentText;

    @Override
    public EventHandler<ActionEvent> handleSet() {return new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionevent) {

            String tmpText = aTextField.getText();

            // trim whitespaces
            tmpText = tmpText.trim();

            // if an empty string is passed, clear the name
            if (tmpText.equals("")) {
                aGameModel.setPlayerName(tmpText, aIndex);
            }

            if (!validatePlayerName(tmpText)) {return;}

            // Check with the GameModel if this name can be set
            if (aGameModel.checkPlayerName(tmpText, aIndex)) {
                aGameModel.setPlayerName(tmpText, aIndex);
            } else {
                System.out.println("No duplicate names!");
            }
        }
    };}

    @Override
    public boolean validatePlayerName(String pName) {

        // only accept a-zA-Z
        Pattern patternLetters = Pattern.compile("^[a-zA-Z0-9\s]+$");
        Matcher matcherLetters = patternLetters.matcher(pName);
        return matcherLetters.find();

    }



}
