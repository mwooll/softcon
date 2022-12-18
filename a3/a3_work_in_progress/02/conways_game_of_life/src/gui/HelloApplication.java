package gui;

import gamemodel.GameModel;

import cell.*;

import gui.firstStage.*;
import gui.secondStage.*;
import gui.thirdStage.*;
import gui.fourthStage.*;

import initializer.GUIInitializer;
import initializer.Initializer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import parser.IParser;
import parser.InitializerParser;

import java.util.List;


public class HelloApplication extends Application {

    static final int HEIGHT = 400;
    static final int WIDTH = 600;
    static final int MARGIN_OUTER = 10;

    public static class FirstStage extends Stage {

        private final GUIInitializer aInitializer;

        public FirstStage(GUIInitializer pInitializer) {

            aInitializer = pInitializer;

            GridPane aRoot = new GridPane();

            aRoot.setGridLinesVisible(true);
            aRoot.setStyle("-fx-background-color: white");
            aRoot.setHgap(MARGIN_OUTER);
            aRoot.setVgap(MARGIN_OUTER);
            aRoot.setPadding(new Insets(MARGIN_OUTER));

            this.setTitle("First Stage");

            // Create Player Name Observers
            IPlayerObserver po0 = new PlayerObserver(aInitializer, 0);
            IPlayerObserver po1 = new PlayerObserver(aInitializer, 1);
            aRoot.add((Parent) po0, 0, 0);
            aRoot.add((Parent) po1, 0, 1);

            // Create Player Name Setters
            ISetter ps0 = new PlayerSetter(aInitializer, 0);
            ISetter ps1 = new PlayerSetter(aInitializer, 1);
            aRoot.add((Parent) ps0, 0, 2);
            aRoot.add((Parent) ps1, 0, 3);

            // Create Continue Button
            IContinue cont = new firstStageContinue(aInitializer);
            aRoot.add((Parent) cont, 0, 4);

            this.setScene(new Scene(aRoot, WIDTH, HEIGHT));
            this.show();

            // Fetch the continue button, continue to second stage
            Button continueButton = cont.getButton();

//            continueButton.setOnAction(t -> System.out.println("Continue to second stage"));
            continueButton.setOnAction((t) -> {
                new SecondStage(aInitializer);
                this.close();
            });

        }

    }

    public static class SecondStage extends Stage {

        private final GUIInitializer aInitializer;

        public SecondStage(GUIInitializer pInitializer) {

            aInitializer = pInitializer;

            GridPane aRoot = new GridPane();

            aRoot.setGridLinesVisible(true);
            aRoot.setStyle("-fx-background-color: white");
            aRoot.setHgap(MARGIN_OUTER);
            aRoot.setVgap(MARGIN_OUTER);
            aRoot.setPadding(new Insets(MARGIN_OUTER));

            this.setTitle("Second Stage");

            // Fetch all currently registered players
            List<String> currentPlayerNames = aInitializer.getPlayers();

            // Create Player Color Pickers
            int tmpRowCt = 0;
            for (String name : currentPlayerNames) {
                ISetter cs = new ColorSetter(aInitializer, name);
                aRoot.add((Parent) cs, 0, tmpRowCt);
                tmpRowCt++;
            }

            // Create Continue Button
            IContinue cont = new secondStageContinue(aInitializer);
            aRoot.add((Parent) cont, 0, tmpRowCt + 1);

            this.setScene(new Scene(aRoot, WIDTH, HEIGHT));
            this.show();

            // Fetch the continue button, continue to second stage
            Button continueButton = cont.getButton();

//            continueButton.setOnAction(t -> System.out.println("Continue to third stage"));
            continueButton.setOnAction((t) -> {
                new ThirdStage(aInitializer);
                this.close();
            });

        }

    }
//
    public static class ThirdStage extends Stage {

        private final GUIInitializer aInitializer;

        public ThirdStage(GUIInitializer pInitializer) {

            aInitializer = pInitializer;

            GridPane aRoot = new GridPane();

            aRoot.setGridLinesVisible(true);
            aRoot.setStyle("-fx-background-color: white");
            aRoot.setHgap(MARGIN_OUTER);
            aRoot.setVgap(MARGIN_OUTER);
            aRoot.setPadding(new Insets(MARGIN_OUTER));

            this.setTitle("Third Stage");

            // Add label explaining
            Label labelExplanation = new Label();
            labelExplanation.setText(
                    String.format("Min size %s, Max size %s", aInitializer.getMinGridSize(), aInitializer.getMaxGridSize())
            );
            aRoot.add(labelExplanation, 0, 0);

            // Create Grid Observers
            IGridObserver go0 = new GridObserver(aInitializer, "H");
            IGridObserver go1 = new GridObserver(aInitializer, "W");
            aRoot.add((Parent) go0, 0, 1);
            aRoot.add((Parent) go1, 0, 2);

            // Create Grid Setters
            ISetter gs0 = new GridSetter(aInitializer, "H");
            ISetter gs1 = new GridSetter(aInitializer, "W");
            aRoot.add((Parent) gs0, 0, 3);
            aRoot.add((Parent) gs1, 0, 4);

            // Create Continue Button
            IContinue cont = new thirdStageContinue(aInitializer);
            aRoot.add((Parent) cont, 0, 5);

            this.setScene(new Scene(aRoot, WIDTH, HEIGHT));
            this.show();

            // Fetch the continue button, continue to second stage
            Button continueButton = cont.getButton();

//            continueButton.setOnAction(t -> System.out.println("Continue to fourth stage"));
            continueButton.setOnAction((t) -> {
                new FourthStage(aInitializer);
                this.close();
            });

        }

    }
//
    public static class FourthStage extends Stage {

        private final GUIInitializer aInitializer;

        public FourthStage(GUIInitializer pInitializer) {

            aInitializer = pInitializer;

            GridPane aRoot = new GridPane();

            aRoot.setGridLinesVisible(true);
            aRoot.setStyle("-fx-background-color: white");
            aRoot.setHgap(MARGIN_OUTER);
            aRoot.setVgap(MARGIN_OUTER);
            aRoot.setPadding(new Insets(MARGIN_OUTER));

            this.setTitle("Fourth Stage");

            // Add label explaining
            Label labelExplanation = new Label();
            labelExplanation.setText(
                    String.format("This is the initial grid configuration, based on the chosen size and a random start pattern")
            );
            aRoot.add(labelExplanation, 0, 0);


            // Create the grid with the cells
            aInitializer.createStartingConfiguration();

            // Printer for debugging
            Grid currentInitialGrid = aInitializer.getGrid();
            String tmpString = "";
            int ct = 1;
            for (Cell c : currentInitialGrid.getIterator()) {
                tmpString += c.getState().getColorName();
                if (ct%currentInitialGrid.getWidth() == 0) {
                    tmpString += "\n";
                } else {
                    tmpString += " ";
                }
                ct ++;
            }
            System.out.println(tmpString);


            // Create Continue Button
            IContinue cont = new fourthStageContinue(aInitializer);
            aRoot.add((Parent) cont, 0, 5);

            this.setScene(new Scene(aRoot, WIDTH, HEIGHT));
            this.show();

            // Fetch the continue button, continue to second stage
            Button continueButton = cont.getButton();

            continueButton.setOnAction(t -> System.out.println("Continue to fifth stage"));

        }

    }

    @Override
    public void start(Stage stage) {

        IParser initParser = new InitializerParser();
        GUIInitializer guiInit = new GUIInitializer(initParser) {};

        new FirstStage(guiInit);

    }

    public static void main(String[] args) {
        launch();
    }

}