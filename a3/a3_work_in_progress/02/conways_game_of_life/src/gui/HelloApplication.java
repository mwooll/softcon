package gui;

import gamemodel.GameModel;

import gui.firstStage.*;
import gui.secondStage.*;

import gui.secondStage.IColorPicker;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;

import java.util.List;


public class HelloApplication extends Application {

    static final int HEIGHT = 400;
    static final int WIDTH = 600;
    static final int MARGIN_OUTER = 10;

    public static class FirstStage extends Stage {

        private final GameModel aGameModel;

        public FirstStage(GameModel pGameModel) {

            aGameModel = pGameModel;

            GridPane aRoot = new GridPane();

            aRoot.setGridLinesVisible(true);
            aRoot.setStyle("-fx-background-color: white");
            aRoot.setHgap(MARGIN_OUTER);
            aRoot.setVgap(MARGIN_OUTER);
            aRoot.setPadding(new Insets(MARGIN_OUTER));

            this.setTitle("First Stage");

            // Create Player Name Observers
            IPlayerObserver po0 = new PlayerObserver(aGameModel, 0);
            IPlayerObserver po1 = new PlayerObserver(aGameModel, 1);
            aRoot.add((Parent) po0, 0, 0);
            aRoot.add((Parent) po1, 0, 1);

            // Create Player Name Setters
            AbstractPlayerSetter ps0 = new PlayerSetter(aGameModel, 0);
            AbstractPlayerSetter ps1 = new PlayerSetter(aGameModel, 1);
            aRoot.add(ps0, 0, 2);
            aRoot.add(ps1, 0, 3);

            // Create Continue Button
            NameContinueObserver continueObserver = new NameContinueObserver(aGameModel);
            aRoot.add((Parent) continueObserver, 0, 4);

            this.setScene(new Scene(aRoot, WIDTH, HEIGHT));
            this.show();

            // Fetch the continue button, continue to second stage
            Button continueButton = continueObserver.getButton();

//            continueButton.setOnAction(t -> System.out.println("Continue to second stage"));
            continueButton.setOnAction(t -> new SecondStage(aGameModel));

        }

    }

    public static class SecondStage extends Stage {

        private final GameModel aGameModel;

        public SecondStage(GameModel pGameModel) {

            aGameModel = pGameModel;

            GridPane aRoot = new GridPane();

            aRoot.setGridLinesVisible(true);
            aRoot.setStyle("-fx-background-color: white");
            aRoot.setHgap(MARGIN_OUTER);
            aRoot.setVgap(MARGIN_OUTER);
            aRoot.setPadding(new Insets(MARGIN_OUTER));

            this.setTitle("Second Stage");

            // Fetch all currently registered players
            List<String> currentPlayerNames = aGameModel.getPlayerNames();

            // Create Player Color Pickers
            int tmpRowCt = 0;
            for (String name : currentPlayerNames) {
                IColorPicker tmpCP = new ColorPicker(aGameModel, name);
                aRoot.add((Parent) tmpCP, 0, tmpRowCt);
                tmpRowCt++;
            }

            // Create Continue Button
            ColorContinueObserver continueObserver = new ColorContinueObserver(aGameModel);
            aRoot.add(continueObserver, 0, tmpRowCt + 1);

            this.setScene(new Scene(aRoot, WIDTH, HEIGHT));
            this.show();

            // Fetch the continue button, continue to second stage
            Button continueButton = continueObserver.getButton();

            continueButton.setOnAction(t -> System.out.println("Continue to third stage"));
//            continueButton.setOnAction(t -> new SecondStage(aGameModel));

        }

    }

    @Override
    public void start(Stage stage) {

        // Game model
        final GameModel gamemodel = new GameModel();

        new FirstStage(gamemodel);

    }

    public static void main(String[] args) {
        launch();
    }

}