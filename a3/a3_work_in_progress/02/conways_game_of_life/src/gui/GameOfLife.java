package gui;

import cell.*;

import gamemodel.GameModel;
import gui.playGame.*;
import gui.chooseNames.*;
import gui.chooseColors.*;
import gui.chooseGridsize.*;
import gui.createInitialConfig.*;

import initializer.GUIInitializer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import parser.IParser;
import parser.InitializerParser;
import player.Player;
import player.PlayerColor;

import java.util.List;


public class GameOfLife extends Application {

    static final int HEIGHT = 800;
    static final int WIDTH = 1200;
    static final int MARGIN_OUTER = 10;

    public static class ChooseNames extends Stage {

        private final GUIInitializer aInitializer;

        public ChooseNames(GUIInitializer pInitializer) {

            aInitializer = pInitializer;

            GridPane aRoot = new GridPane();

            aRoot.setGridLinesVisible(false);
            aRoot.setStyle("-fx-background-color: white");
            aRoot.setHgap(MARGIN_OUTER);
            aRoot.setVgap(MARGIN_OUTER);
            aRoot.setPadding(new Insets(MARGIN_OUTER));

            this.setTitle("First Stage");

            // Create Player Name Observers
            IInitializerObserver po0 = new PlayerObserver(aInitializer, 0);
            IInitializerObserver po1 = new PlayerObserver(aInitializer, 1);
            aRoot.add((Parent) po0, 0, 0);
            aRoot.add((Parent) po1, 0, 1);

            // Create Player Name Setters
            ISetter ps0 = new PlayerSetter(aInitializer, 0);
            ISetter ps1 = new PlayerSetter(aInitializer, 1);
            aRoot.add((Parent) ps0, 0, 2);
            aRoot.add((Parent) ps1, 0, 3);

            // Create Continue Button
            IContinue cont = new chooseNamesContinue(aInitializer);
            aRoot.add((Parent) cont, 0, 4);

            this.setScene(new Scene(aRoot, WIDTH, HEIGHT));
            this.show();

            // Fetch the continue button, continue to second stage
            Button continueButton = cont.getButton();

//            continueButton.setOnAction(t -> System.out.println("Continue to second stage"));
            continueButton.setOnAction((t) -> {
                new ChooseColors(aInitializer);
                this.close();
            });

        }

    }

    public static class ChooseColors extends Stage {

        private final GUIInitializer aInitializer;

        public ChooseColors(GUIInitializer pInitializer) {

            aInitializer = pInitializer;

            GridPane aRoot = new GridPane();

            aRoot.setGridLinesVisible(false);
            aRoot.setStyle("-fx-background-color: white");
            aRoot.setHgap(MARGIN_OUTER);
            aRoot.setVgap(MARGIN_OUTER);
            aRoot.setPadding(new Insets(MARGIN_OUTER));

            this.setTitle("Second Stage");

            // Fetch all currently registered players
            List<Player> currentPlayers = aInitializer.getPlayers();

            // Create Player Color Pickers
            int tmpRowCt = 0;
            for (Player player : currentPlayers) {
                String playerName = player.getName();
                PlayerColor playerColor = player.getColor();
                String playerColorName = playerColor.getColorName();
                ISetter cs = new ColorSetter(aInitializer, playerName);
                aRoot.add((Parent) cs, 0, tmpRowCt);
                tmpRowCt++;
            }

            // Create Continue Button
            IContinue cont = new chooseColorsContinue(aInitializer);
            aRoot.add((Parent) cont, 0, tmpRowCt + 1);

            this.setScene(new Scene(aRoot, WIDTH, HEIGHT));
            this.show();

            // Fetch the continue button, continue to second stage
            Button continueButton = cont.getButton();

//            continueButton.setOnAction(t -> System.out.println("Continue to third stage"));
            continueButton.setOnAction((t) -> {
                new ChooseGridsize(aInitializer);
                this.close();
            });

        }

    }

    public static class ChooseGridsize extends Stage {

        private final GUIInitializer aInitializer;

        public ChooseGridsize(GUIInitializer pInitializer) {

            aInitializer = pInitializer;

            GridPane aRoot = new GridPane();

            aRoot.setGridLinesVisible(false);
            aRoot.setStyle("-fx-background-color: white");
            aRoot.setHgap(MARGIN_OUTER);
            aRoot.setVgap(MARGIN_OUTER);
            aRoot.setPadding(new Insets(MARGIN_OUTER));

            this.setTitle("Third Stage");

            // Add label explaining
            Label labelExplanation = new Label();
            labelExplanation.setText(
                    String.format("Chose the size of the gird PER PLAYER. The final Grid will be double that size.\nMin size %s, Max size %s", aInitializer.getMinGridSize(), aInitializer.getMaxGridSize())
            );
            aRoot.add(labelExplanation, 0, 0);

            // Create Grid Observers
            IInitializerObserver go0 = new GridObserver(aInitializer, "H");
            IInitializerObserver go1 = new GridObserver(aInitializer, "W");
            aRoot.add((Parent) go0, 0, 1);
            aRoot.add((Parent) go1, 0, 2);

            // Create Grid Setters
            ISetter gs0 = new GridSetter(aInitializer, "H");
            ISetter gs1 = new GridSetter(aInitializer, "W");
            aRoot.add((Parent) gs0, 0, 3);
            aRoot.add((Parent) gs1, 0, 4);

            // Create Continue Button
            IContinue cont = new chooseGridsizeContinue(aInitializer);
            aRoot.add((Parent) cont, 0, 5);

            this.setScene(new Scene(aRoot, WIDTH, HEIGHT));
            this.show();

            // Fetch the continue button, continue to second stage
            Button continueButton = cont.getButton();

//            continueButton.setOnAction(t -> System.out.println("Continue to fourth stage"));
            continueButton.setOnAction((t) -> {
                new CreateInitialConfig(aInitializer);
                this.close();
            });

        }

    }

    public static class CreateInitialConfig extends Stage {

        private final GUIInitializer aInitializer;

        public CreateInitialConfig(GUIInitializer pInitializer) {

            aInitializer = pInitializer;

            ScrollPane sp = new ScrollPane();

            VBox aRoot = new VBox();

            aRoot.setStyle("-fx-background-color: white");
            aRoot.setStyle("-fx-border-color: black");
            aRoot.setPadding(new Insets(10));
            aRoot.setSpacing(8);

            this.setTitle("Choosing initial grid configuration");

            // Create the grid with empty cells
            aInitializer.createEmptyStartingGrid();
            Grid aInitialGrid = aInitializer.getGrid();

            // Add label explaining
            Label labelExplanation = new Label();
            labelExplanation.setText(
                    String.format("""
                            Choose initial grid configuration.The complete grid will consist of two times what you see now.
                            Both players will have the same mirrored configuration.
                            Choose min %s and max %s cells.""", aInitializer.getMinCellsChoose(), aInitializer.getMaxCellsChoose())
            );
            aRoot.getChildren().add(labelExplanation);


            // Draw the grid
            GridPane aGridPane = new GridPane();
            int row_ct = 1;
            int col_ct = 0;
            for (Cell c : aInitialGrid.getIterator()) {
                ICellObserver cObserver = new CellObserver(c);
                ISetter cChooseSetter = new CellChooseSetter(aInitializer, c, aInitializer);
                if (col_ct%aInitialGrid.getWidth() == 0) {
                    row_ct++;
                    col_ct = 0;
                }
                aGridPane.add((Parent) cObserver, col_ct, row_ct);

                // For each cell, place a vbox with two setters in there
                VBox vbox = new VBox((Parent) cChooseSetter);
                aGridPane.add(vbox, col_ct, row_ct);

                col_ct ++;
            }

            // Add Grid to VBox aRoot
            aRoot.getChildren().add(aGridPane);


            // Create Continue Button
            IContinue cont = new createInitialConfigContinue(aInitializer);
            aRoot.getChildren().add((Parent) cont);

            // Fetch the continue button, continue to second stage
            Button continueButton = cont.getButton();

            sp.setContent(aRoot);
            this.setScene(new Scene(sp, WIDTH, HEIGHT));
            this.show();

//            continueButton.setOnAction(t -> System.out.println("Continue to fifth stage"));
            continueButton.setOnAction((t) -> {
                aInitializer.createStartingConfiguration();
                new PlayGame(aInitializer);
                this.close();
            });
        }

    }

    public static class PlayGame extends Stage {

        private final GameModel aGameModel;

        public PlayGame(GUIInitializer pInitializer) {
            aGameModel = new GameModel(pInitializer);
            Grid aGrid = aGameModel.returnGrid();

            ScrollPane sp = new ScrollPane();

            VBox aRoot = new VBox();

            aRoot.setStyle("-fx-background-color: white");
            aRoot.setStyle("-fx-border-color: black");
            aRoot.setPadding(new Insets(10));
            aRoot.setSpacing(8);

            this.setTitle("Let's play the game!");

            // Add label explaining
            Label labelExplanation = new Label();
            labelExplanation.setText(
                    String.format("Lets start the game!")
            );
            aRoot.getChildren().add(labelExplanation);

            // Play the first turn, end game directly if someone has lost already by faulty starting config
            if (aGameModel.hasAPlayerLost()) {
                this.close();
                new GameOver(aGameModel);
            }
            aGameModel.playTurn();

            // Add Label with current player and which turn it is
            IGameModelObserver turnLabelObserver = new TurnLabelObserver(aGameModel);
            aRoot.getChildren().add((Parent) turnLabelObserver);

            // Draw the grid
            GridPane aGridPane = new GridPane();
            int row_ct = 2;
            int col_ct = 0;
            for (Cell c : aGrid.getIterator()) {
                ICellObserver cObserver = new CellObserver(c);
                ISetter cDeleteSetter = new CellDeleteSetter(aGameModel, c, aGameModel);
                ISetter cCreateSetter = new CellCreateSetter(aGameModel, c, aGameModel);
                if (col_ct%aGameModel.aWidth == 0) {
                    row_ct++;
                    col_ct = 0;
                }
                aGridPane.add((Parent) cObserver, col_ct, row_ct);

                // For each cell, place a vbox with two setters in there
                VBox vbox = new VBox((Parent) cDeleteSetter, (Parent) cCreateSetter);
                aGridPane.add(vbox, col_ct, row_ct);

                col_ct ++;
            }

            // Add Button telling if the moves of the players turn have taken place or not
            IContinue turnContinue = new turnContinue(aGameModel);
            aRoot.getChildren().add((Parent) turnContinue);
            // Fetch the turnContinue button, calculate generation
            Button turnContinueButton = turnContinue.getButton();
            turnContinueButton.setOnAction((t) -> {
                aGrid.generateNextGeneration();
                if (aGameModel.hasAPlayerLost()) {
                    new GameOver(aGameModel);
                    this.close();
                }
                aGameModel.playTurn();
            });

            // Add Grid to VBox aRoot
            aRoot.getChildren().add(aGridPane);

            // only show if not over already
            if (!aGameModel.hasAPlayerLost()) {
                sp.setContent(aRoot);
                this.setScene(new Scene(sp, WIDTH, HEIGHT));
                this.show();
            }

        }

    }

    public static class GameOver extends Stage {

        private final GameModel aGameModel;

        public GameOver(GameModel pGameModel) {
            aGameModel = pGameModel;
            Grid aGrid = aGameModel.returnGrid();

            ScrollPane sp = new ScrollPane();

            VBox aRoot = new VBox();

            aRoot.setStyle("-fx-background-color: white");
            aRoot.setStyle("-fx-border-color: black");
            aRoot.setPadding(new Insets(10));
            aRoot.setSpacing(8);

            this.setTitle("Game over. Final Board:");

            // Add label explaining
            Label labelExplanation = new Label();
            labelExplanation.setText(
                    String.format(aGameModel.determineWinner())
            );
            aRoot.getChildren().add(labelExplanation);

            // Draw the grid
            GridPane aGridPane = new GridPane();
            int row_ct = 2;
            int col_ct = 0;
            for (Cell c : aGrid.getIterator()) {
                ICellObserver cObserver = new CellObserver(c);
                if (col_ct%aGameModel.aWidth == 0) {
                    row_ct++;
                    col_ct = 0;
                }
                aGridPane.add((Parent) cObserver, col_ct, row_ct);
                col_ct ++;
            }

            // Add Grid to VBox aRoot
            aRoot.getChildren().add(aGridPane);

            sp.setContent(aRoot);
            this.setScene(new Scene(sp, WIDTH, HEIGHT));
            this.show();

        }

    }


    @Override
    public void start(Stage stage) {

        IParser initParser = new InitializerParser();


//        GUIInitializer guiInit = new testGUIInitializer(initParser) {};
        GUIInitializer guiInit = new GUIInitializer(initParser) {};

        new ChooseNames(guiInit);
//        new FifthStage(guiInit);

    }

    public static void main(String[] args) {
        launch();
    }

}