package gui;

import gamemodel.GameModel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerSetter extends VBox implements IPlayerSetter {

    private final GameModel aGameModel;

    private final int aIndex;

    private final String defaultText = "Set Name Player";
    private String currentText;

    public PlayerSetter(GameModel pGameModel, int pIndex) {

        aGameModel = pGameModel;
        aIndex = pIndex;

        final Label label = new Label();
        final TextField textfield = new TextField();
        final Button button = new Button("Set Name");

        currentText = String.format("%s %s", defaultText, aIndex+1);
        label.setText(currentText);

        final HBox hbox = new HBox(label, textfield, button);
        getChildren().add(hbox);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String tmpText = textfield.getText();

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
        });

    }

    @Override
    public boolean validatePlayerName(String pName) {

        // only accept a-zA-Z
        Pattern patternLetters = Pattern.compile("^[a-zA-Z0-9\s]+$");
        Matcher matcherLetters = patternLetters.matcher(pName);
        return matcherLetters.find();

    }

}
