package gui.thirdStage;

import gui.IInitializerObserver;
import initializer.InitializerObservable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import player.PlayerColor;

public class GridObserver extends Parent implements IInitializerObserver {

    private final InitializerObservable aObservable;
    private String aIdentifier;
    private final Text text = new Text();

    public GridObserver(InitializerObservable pObservable, String pIdentifier) {

        aObservable = pObservable;
        aIdentifier = pIdentifier;

        text.setText(aIdentifier);

        VBox vbox = new VBox(text);
        getChildren().add(vbox);

        aObservable.addObserver(this);

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

    @Override
    public void nameIsSet(String pName, int pIndex) {}
    @Override
    public void colorIsSet(PlayerColor pPlayerColor, String pName) {}
    @Override
    public void setVisibility() {}
    @Override
    public void cellIsChosen() {}

}
