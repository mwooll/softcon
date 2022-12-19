package parser;

import javafx.scene.paint.Color;

import java.util.List;

public interface IParser {

    boolean validatePlayerName(List<String> pNamesInUse, String pName);

    boolean validateColorName(List<String> pColorNamesInUse, String pColorName);

    boolean validateHeight(int pMaxHeight, int pMinHeight, String pHeight);
    boolean validateWidth(int pMaxWidth, int pMinWidth, String pWidth);

    boolean validateCell(cell.Cell pCell);

}
