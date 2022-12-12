package gui.secondStage;

import gamemodel.GameModel;
import gui.ISetter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class AbstractColorSetter extends Parent implements ISetter {

    protected final ObservableList<String> OPTIONS = FXCollections.observableArrayList("RED", "BLUE");
    protected GameModel aGameModel;
    protected final Label aLabel = new Label();
    protected final ComboBox<String> aComboBox = new ComboBox<>(OPTIONS);

    protected String aName;

    public AbstractColorSetter(GameModel pGameModel, String pName) {

        aGameModel = pGameModel;
        aName = pName;

        aLabel.setText(String.format("Color Player %s", aName));

        final HBox hBox = new HBox(aLabel, aComboBox);
        getChildren().add(hBox);

        aComboBox.setOnAction(handleSet());

    }

    @Override
    public EventHandler<ActionEvent> handleSet() {return actionevent -> {

        String tmpString = aComboBox.getValue();

        if (tmpString.equals("RED")) {
            aGameModel.setPlayerColor(Color.RED, aName);
        }
        if (tmpString.equals("BLUE")) {
            aGameModel.setPlayerColor(Color.BLUE, aName);
        }

    };}


}
