package gui.secondStage;

import gamemodel.GameModel;
import gui.ISetter;
import initializer.InitializerObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import player.PlayerColor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AbstractColorSetter extends Parent implements ISetter {

    private final List<Color> allPlayerColors = Stream.of(PlayerColor.values()).map(PlayerColor::getColorHex).collect(Collectors.toList());
    protected final ObservableList<Color> OPTIONS = FXCollections.observableArrayList(allPlayerColors);
    protected InitializerObserver aObserver;
    protected final Label aLabel = new Label();
    protected final ComboBox<Color> aComboBox = new ComboBox<>(OPTIONS);

    protected String aName;

    public AbstractColorSetter(InitializerObserver pObserver, String pName) {

        aObserver = pObserver;
        aName = pName;

        aLabel.setText(String.format("Color Player %s", aName));

        final HBox hBox = new HBox(aLabel, aComboBox);
        getChildren().add(hBox);

        aComboBox.setOnAction(handleSet());

    }

    @Override
    public EventHandler<ActionEvent> handleSet() {return actionevent -> {

        Color tmpColor = aComboBox.getValue();

        aObserver.setPlayerColor(tmpColor, aName);

    };}


}
