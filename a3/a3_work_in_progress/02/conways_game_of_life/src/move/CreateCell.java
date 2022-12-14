package move;

public class CreateCell extends Move {
    private final player.Player aPlayer;
    private final cell.Cell aCell;

    public CreateCell(player.Player pPlayer, cell.Cell pCell) {
        aPlayer = pPlayer;
        aCell = pCell;
    }

    public void executeMove() {
        player.PlayerColor newState = aPlayer.getColor();
        aCell.instantBirth(newState);
    }
}
