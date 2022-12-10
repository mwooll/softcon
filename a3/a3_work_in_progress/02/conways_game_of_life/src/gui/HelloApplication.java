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
import javafx.scene.layout.VBox;
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

        // Game model
        final GameModel gamemodel = new GameModel();

        stage.setTitle("Test JavaFX");

        GridPane rootFirstStage = new GridPane();
        rootFirstStage.setGridLinesVisible(true);
        rootFirstStage.setStyle("-fx-background-color: white");
        rootFirstStage.setHgap(MARGIN_OUTER);
        rootFirstStage.setVgap(MARGIN_OUTER);
        rootFirstStage.setPadding(new Insets(MARGIN_OUTER));

        int rowCounter = 0;
        rootFirstStage.add(new Label("! No duplicate names allowed"), 0, rowCounter);
        rowCounter ++;

        for (int i = 0; i < gamemodel.N_PLAYERS; i++) {
            // Player Observer
            IPlayerObserver playerObserver = new PlayerObserver(gamemodel, i);
            rootFirstStage.add((VBox) playerObserver, 0, rowCounter);

            rowCounter ++;
        }

        for (int i = 0; i < gamemodel.N_PLAYERS; i++) {
            // Player Setter
            IPlayerSetter playerSetter = new PlayerSetter(gamemodel, i);
            rootFirstStage.add((VBox) playerSetter, 0,rowCounter);

            rowCounter ++;
        }

        // Continue Button (Observer)
        IPlayerObserver continueButton = new PlayerContinueObserver(gamemodel);
        rootFirstStage.add((VBox) continueButton, 0, rowCounter);

        rowCounter++;

        stage.setScene(new Scene(rootFirstStage, WIDTH, HEIGHT));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}