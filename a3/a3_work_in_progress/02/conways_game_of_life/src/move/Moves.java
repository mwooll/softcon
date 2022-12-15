package move;

public class Moves {
    /**
     * @return boolean stating if the move is legal for the player
     */
    static boolean canCreateCell(cell.Cell pCell) {
        player.PlayerColor currentState = pCell.getState();
        if (currentState != player.PlayerColor.WHITE) return false;
        return true;
    }

    /**
     * @pre canCreateCell()
     */
    static void createCell(player.Player pPlayer, cell.Cell pCell) {
        pCell.instantBirth(pPlayer.getColor());
    }

    /**
     * @return boolean stating if the move is legal for the player
     */
    static boolean canDeleteCell(player.Player pPlayer, cell.Cell pCell) {
        player.PlayerColor currentState = pCell.getState();
        if (currentState == player.PlayerColor.WHITE) return false;
        if (currentState == pPlayer.getColor()) return false;
        return true;
    }

    /**
     * @pre canDeleteCell()
     */
    static void deleteCell(cell.Cell pCell) {
        pCell.instantDeath();
    }
}
