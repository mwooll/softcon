package gui.secondStage;

import gamemodel.GameModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

public class AbstractColorPicker extends Parent implements IColorPicker {

    protected final ObservableList<Color> FINAL_COLORS = FXCollections.observableArrayList(Color.BLUE, Color.RED);

    protected GameModel aGameModel;
    protected final ComboBox<Color> aComboBox = new ComboBox<>(FINAL_COLORS);
    protected String aName;

    public AbstractColorPicker(GameModel pGameModel, String pName) {

        aGameModel = pGameModel;
        aName = pName;

        // Replace text in ComboBox with a rectangle of the color
        aComboBox.setCellFactory(new Callback<ListView<Color>, ListCell<Color>>() {
            @Override
            public ListCell<Color> call(ListView<Color> p) {
                return new ListCell<Color>() {
                    private final Rectangle rectangle;

                    {
                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        rectangle = new Rectangle(10, 10);
                    }

                    @Override
                    protected void updateItem(Color item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            rectangle.setFill(item);
                            setGraphic(rectangle);
                        }
                    }
                };
            }
        });

        final HBox hbox = new HBox(aComboBox);
        getChildren().add(hbox);

        aComboBox.setOnAction(handleSet());

    }

    @Override
    public EventHandler<ActionEvent> handleSet() {return actionevent -> {

        Color tmpColor = aComboBox.getValue();

        aGameModel.setPlayerColor(tmpColor, aName);

        };
    }

}
