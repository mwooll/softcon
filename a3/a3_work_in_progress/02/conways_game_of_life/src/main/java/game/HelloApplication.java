package game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
        root.setStyle("-fx-background-color: green");
        root.setHgap(MARGIN_OUTER);
        root.setVgap(MARGIN_OUTER);
        root.setPadding(new Insets(MARGIN_OUTER));

        Button buttonOne = new Button("Click me.");
        buttonOne.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Button clicked.");
            }
        });
        root.getChildren().add(buttonOne);

        stage.setScene(new Scene(root, WIDTH, HEIGHT));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}