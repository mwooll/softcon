module main.conways_game_of_life {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens game to javafx.fxml;
    exports game;
}