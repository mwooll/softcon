package initializer;

import java.util.ArrayList;

public interface Initializer {

    String choosePlayerName();

    player.PlayerColor choosePlayerColor();

    ArrayList<Integer> chooseGridDimensions();

    cell.Grid createStartingConfiguration();
}
