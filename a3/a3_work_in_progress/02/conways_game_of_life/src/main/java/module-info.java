module com.example.conways_game_of_life {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.conways_game_of_life to javafx.fxml;
    exports com.example.conways_game_of_life;
}