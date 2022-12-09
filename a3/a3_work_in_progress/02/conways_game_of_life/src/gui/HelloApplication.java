package gui;

import gamemodel.GameModel;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;


public class HelloApplication extends Application {

    private static final int HEIGHT = 400;
    private static final int WIDTH = 600;
    private static final int MARGIN_OUTER = 10;

    @Override
    public void start(Stage stage) {

        stage.setTitle("Test JavaFX");

        GridPane root = new GridPane();
        root.setGridLinesVisible(true);
        root.setStyle("-fx-background-color: white");
        root.setHgap(MARGIN_OUTER);
        root.setVgap(MARGIN_OUTER);
        root.setPadding(new Insets(MARGIN_OUTER));

        // Game model
        final GameModel gamemodel = new GameModel();

        // Player Setter
        IPlayerSetter playerSetter1 = new PlayerSetter(gamemodel, 0);
        root.add((HBox) playerSetter1, 0,1);

        // Player Observer
        IPlayerObserver playerObserver = new PlayerObserver(gamemodel);
        root.add((HBox) playerObserver, 0,0);

        stage.setScene(new Scene(root, WIDTH, HEIGHT));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}