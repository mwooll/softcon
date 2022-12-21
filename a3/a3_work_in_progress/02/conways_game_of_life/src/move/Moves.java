package move;

import cell.Cell;
import player.Player;
import player.PlayerColor;

public class Moves {
    /**
     * @return boolean stating if the move is legal for the player
     */
    public static boolean canCreateCell(Cell pCell) {
        PlayerColor currentState = pCell.getState();
        if (currentState != PlayerColor.WHITE) return false;
        return true;
    }

    /**
     * @pre canCreateCell()
     */
    public static void createCell(Player pPlayer, Cell pCell) {
        pCell.instantBirth(pPlayer.getColor());
    }

    /**
     * @return boolean stating if the move is legal for the player
     */
    public static boolean canDeleteCell(Player pPlayer, Cell pCell) {
        PlayerColor currentState = pCell.getState();
        if (currentState == PlayerColor.WHITE) return false;
        if (currentState == pPlayer.getColor()) return false;
        return true;
    }

    /**
     * @pre canDeleteCell()
     */
    public static void deleteCell(Cell pCell) {
        pCell.instantDeath();
    }
}
