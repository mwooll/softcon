package ColorPickerApp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX ColorPicker custom palette");

        GridPane root = new GridPane();
        ColorPicker colorpicker = new ColorPicker();
        root.add(colorpicker, 0, 0);

        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
    }

}
