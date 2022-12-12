package gui.thirdStage;

import gamemodel.GameModel;
import gui.IGridObserver;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GridObserver extends Parent implements IGridObserver {

    private final GameModel aGameModel;
    private String aIdentifier;
    private final Text text = new Text();

    public GridObserver(GameModel pGameModel, String pIdentifier) {

        aGameModel = pGameModel;
        aIdentifier = pIdentifier;

        text.setText(aIdentifier);

        VBox vbox = new VBox(text);
        getChildren().add(vbox);

        aGameModel.addGridObserver(this);

    }

    @Override
    public void heightIsSet(int pHeight) {
        if (aIdentifier.equals("H")) {
            String pHeightString = Integer.toString(pHeight);
            text.setText(String.format("%s : %s", aIdentifier, pHeightString));
        }
    }

    @Override
    public void widthIsSet(int pWidth) {
        if (aIdentifier.equals("W")) {
            String pHeightString = Integer.toString(pWidth);
            text.setText(String.format("%s : %s", aIdentifier, pHeightString));
        }
    }

}
