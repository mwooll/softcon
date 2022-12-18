package gui.thirdStage;

import gamemodel.GameModel;
import gui.IGridObserver;
import initializer.InitializerObservable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GridObserver extends Parent implements IGridObserver {

    private final InitializerObservable aObservable;
    private String aIdentifier;
    private final Text text = new Text();

    public GridObserver(InitializerObservable pObservable, String pIdentifier) {

        aObservable = pObservable;
        aIdentifier = pIdentifier;

        text.setText(aIdentifier);

        VBox vbox = new VBox(text);
        getChildren().add(vbox);

        aObservable.addGridObserver(this);

    }

    @Override
    public void heightIsSet(int pHeight) {
        if (aIdentifier.equals("H")) {

            // if negative clear to an empty string
            if (pHeight < 0) {
                text.setText(String.format("%s : %s", aIdentifier, ""));
                return;
            }

            String pHeightString = Integer.toString(pHeight);
            text.setText(String.format("%s : %s", aIdentifier, pHeightString));
        }
    }

    @Override
    public void widthIsSet(int pWidth) {
        if (aIdentifier.equals("W")) {

            // if negative clear to an empty string
            if (pWidth < 0) {
                text.setText(String.format("%s : %s", aIdentifier, ""));
                return;
            }

            String pHeightString = Integer.toString(pWidth);
            text.setText(String.format("%s : %s", aIdentifier, pHeightString));
        }
    }

}
