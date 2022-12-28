package gui;

import cell.*;

import gamemodel.GameModel;
import gui.fifthStage.*;
import gui.firstStage.*;
import gui.secondStage.*;
import gui.thirdStage.*;
import gui.fourthStage.*;

import initializer.GUIInitializer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import parser.GameParser;
import parser.IParser;
import parser.InitializerParser;
import player.Player;
import player.PlayerColor;

import java.util.Arrays;
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

    public static class FourthStage extends Stage {

        private final GUIInitializer aInitializer;

        public FourthStage(GUIInitializer pInitializer) {

            aInitializer = pInitializer;

            VBox aRoot = new VBox();

            aRoot.setStyle("-fx-background-color: white");
            aRoot.setStyle("-fx-border-color: black");
            aRoot.setPadding(new Insets(10));
            aRoot.setSpacing(8);

            this.setTitle("Choosing initial grid configuration");

            // Create the grid with empty cells
            Grid aInitialGrid = aInitializer.createStartingConfiguration();

            // Add label explaining
            Label labelExplanation = new Label();
            labelExplanation.setText(
                    String.format("Choose initial grid configuration. The complete grid will be two times what you see now\nBoth players will have the same mirrored configuration.")
            );
            aRoot.getChildren().add(labelExplanation);




            // Draw the grid
            GridPane aGridPane = new GridPane();
            int row_ct = 1;
            int col_ct = 0;
            for (Cell c : aInitialGrid.getIterator()) {
                ICellObserver cObserver = new CellObserver(c);
//                ISetter cChooseSetter = new CellChooseSetter(c);
                if (col_ct%aInitialGrid.getWidth() == 0) {
                    row_ct++;
                    col_ct = 0;
                }
                aGridPane.add((Parent) cObserver, col_ct, row_ct);

                // For each cell, place a vbox with two setters in there
//                VBox vbox = new VBox((Parent) cChooseSetter);
//                aGridPane.add(vbox, col_ct, row_ct);

                col_ct ++;
            }

            // Add Grid to VBox aRoot
            aRoot.getChildren().add(aGridPane);




//            // Printer for debugging
//            Grid currentInitialGrid = aInitializer.getGrid();
//            String tmpString = "";
//            int ct = 1;
//            for (Cell c : currentInitialGrid.getIterator()) {
//                tmpString += c.getState().getColorName();
//                if (ct%currentInitialGrid.getWidth() == 0) {
//                    tmpString += "\n";
//                } else {
//                    tmpString += " ";
//                }
//                ct ++;
//            }
//            System.out.println(tmpString);


            // Create Continue Button
            IContinue cont = new fourthStageContinue(aInitializer);
            aRoot.getChildren().add((Parent) cont);

            // Fetch the continue button, continue to second stage
            Button continueButton = cont.getButton();

            this.setScene(new Scene(aRoot, WIDTH, HEIGHT));
            this.show();

//            continueButton.setOnAction(t -> System.out.println("Continue to fifth stage"));
            continueButton.setOnAction((t) -> {
                new FifthStage(aInitializer);
                this.close();
            });
        }

    }

    public static class FifthStage extends Stage {

        private final GameModel aGameModel;

        public FifthStage(GUIInitializer pInitializer) {
            aGameModel = new GameModel(pInitializer, new GameParser());
            Grid aGrid = aGameModel.returnGrid();

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

            // Play the first turn
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
                System.out.println("do generation action!!!");
                aGrid.generateNextGeneration();
                if (aGameModel.hasAPlayerLost()) {
                    new FinalStage(aGameModel);
                    this.close();
                }
                aGameModel.playTurn();
            });

            // Add Grid to VBox aRoot
            aRoot.getChildren().add(aGridPane);

            this.setScene(new Scene(aRoot, WIDTH, HEIGHT));
            this.show();

        }

    }

    public static class FinalStage extends Stage {

        private final GameModel aGameModel;

        public FinalStage(GameModel pGameModel) {
            aGameModel = pGameModel;
            Grid aGrid = aGameModel.returnGrid();

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

            this.setScene(new Scene(aRoot, WIDTH, HEIGHT));
            this.show();

        }

    }


    @Override
    public void start(Stage stage) {

        IParser initParser = new InitializerParser();

        class testGUIInitializer extends GUIInitializer {

            public testGUIInitializer(IParser pParser) {

                super(pParser);
//                this.createStartingConfiguration();

            }

//            @Override
//            public List<Player> getPlayers() {
//                return Arrays.asList(
//                        new Player("bob", PlayerColor.RED),
//                        new Player("adam", PlayerColor.BLUE));
//            }

//            @Override
//            public Grid createStartingConfiguration() {
//                    Grid tmpGrid = new Grid(20,20);
//
//                    tmpGrid.getCell(0,0).instantBirth(PlayerColor.BLUE);
//                    tmpGrid.getCell(1,0).instantBirth(PlayerColor.BLUE);
//                    tmpGrid.getCell(0,1).instantBirth(PlayerColor.BLUE);
//                    tmpGrid.getCell(1,1).instantBirth(PlayerColor.BLUE);
//                    tmpGrid.getCell(2,1).instantBirth(PlayerColor.BLUE);
//                    tmpGrid.getCell(1,2).instantBirth(PlayerColor.BLUE);
//
//                    tmpGrid.getCell(5,7).instantBirth(PlayerColor.RED);
//                    tmpGrid.getCell(6,7).instantBirth(PlayerColor.RED);
//                    tmpGrid.getCell(7,4).instantBirth(PlayerColor.RED);
//
//                    aInitialGrid = tmpGrid;
//                    return null;
//
//            }
        }
        GUIInitializer guiInit = new testGUIInitializer(initParser) {};
//        GUIInitializer guiInit = new GUIInitializer(initParser) {};

        new FirstStage(guiInit);
//        new FifthStage(guiInit);

    }

    public static void main(String[] args) {
        launch();
    }

}