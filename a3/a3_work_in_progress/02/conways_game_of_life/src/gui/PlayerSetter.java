package gui;

import gamemodel.GameModel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class PlayerSetter extends HBox implements IPlayerSetter {

    private final GameModel aGameModel;

    public PlayerSetter(GameModel pGameModel, int pIndex) {
        aGameModel = pGameModel;

        final Label label = new Label("Player 1 Name: ");
        final TextField textfield = new TextField();
        final Button button = new Button();
        getChildren().addAll(label, textfield, button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                aGameModel.setPlayerName(textfield.getText(), pIndex);
            }
        });

    }

}
