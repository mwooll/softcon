package move;

public class DeleteCell {
    private final player.Player aPlayer;
    private final cell.Cell aCell;

    public DeleteCell(player.Player pPlayer, cell.Cell pCell){
        aPlayer = pPlayer;
        aCell = pCell;
    }
    
    public void executeMove() {
        aCell.instantDeath();
    }
}
