package gui.fifthStage;

import gamemodel.IGameModelObservable;
import gui.IGameModelObserver;
import initializer.InitializerObservable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import player.Player;

public class TurnLabelObserver extends Parent implements IGameModelObserver {

    private final IGameModelObservable aObservable;
    private final Label aLabel = new Label();

    public TurnLabelObserver(IGameModelObservable pObservable) {

        aObservable = pObservable;

        aLabel.setText(generatePlayerLabel());

        VBox vbox = new VBox(aLabel);
        getChildren().add(vbox);

        aObservable.addObserver(this);

    }

    private String generatePlayerLabel() {
        Player currentPlayer = aObservable.returnCurrentPlayer();
        int currentTurnNumber = aObservable.returnCurrentTurnNumber();
        return String.format("Player %s Color %s - Turn Number %s", currentPlayer.getName(), currentPlayer.getColor().getColorName(), currentTurnNumber);
    }

    @Override
    public void currentPlayerChanged() {
        aLabel.setText(generatePlayerLabel());
    }

    @Override
    public void stateCanDeleteChanged() {

    }

    @Override
    public void stateCanCreateChanged() {

    }
}
