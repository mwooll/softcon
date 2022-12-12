package gui.secondStage;

import gamemodel.GameModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

public class AbstractColorPicker extends Parent implements IColorPicker {

//    protected final ObservableList<Color> FINAL_COLORS = FXCollections.observableArrayList(Color.BLUE, Color.RED);
    protected final Color DEFAULT_COLOR = Color.WHITE;

    protected GameModel aGameModel;
    protected final Label aLabel = new Label();
    protected final ColorPicker aColorPicker = new ColorPicker(DEFAULT_COLOR);
    protected String aName;

    public AbstractColorPicker(GameModel pGameModel, String pName) {

        aGameModel = pGameModel;
        aName = pName;

        aLabel.setText(String.format("Color Player %s", aName));

        final HBox hbox = new HBox(aLabel, aColorPicker);
        getChildren().add(hbox);

        aColorPicker.setOnAction(handleSet());

    }

    @Override
    public EventHandler<ActionEvent> handleSet() {return actionevent -> {

        Color tmpColor = aColorPicker.getValue();

        aGameModel.setPlayerColor(tmpColor, aName);

        };
    }

}
