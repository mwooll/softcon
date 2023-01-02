package gui.chooseColors;

import gui.ISetter;
import initializer.IInitializerSetterObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import player.PlayerColor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AbstractColorSetter extends Parent implements ISetter {

    private final List<String> allPlayerColors = Stream.of(PlayerColor.values()).map(PlayerColor::getColorName).filter(p -> !p.equals("White") && !p.equals("Black")).collect(Collectors.toList());
    protected final ObservableList<String> OPTIONS = FXCollections.observableArrayList(allPlayerColors);
    protected IInitializerSetterObserver aObserver;
    protected final Label aLabel = new Label();
    protected final ComboBox<String> aComboBox = new ComboBox<>(OPTIONS);

    protected String aName;

    public AbstractColorSetter(IInitializerSetterObserver pObserver, String pName) {

        aObserver = pObserver;
        aName = pName;

        aLabel.setText(String.format("Color Player %s :  ", aName));

        BorderPane bp = new BorderPane();
        bp.setLeft(aLabel);
        bp.setRight(aComboBox);
        getChildren().add(bp);

        aComboBox.setOnAction(handleSet());

    }

    @Override
    public EventHandler<ActionEvent> handleSet() {return e -> {

        String tmpColorName = aComboBox.getValue();

        // get the respective Color
        for (PlayerColor pc : PlayerColor.values()) {
            if (pc.getColorName().equals(tmpColorName)) {
                aObserver.setPlayerColor(pc, aName);
            }
        }
    };}


}
