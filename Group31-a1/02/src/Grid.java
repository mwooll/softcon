import java.util.List;
import java.util.ArrayList;

/*
todo: Implement printAsTarget, printAsOcean
 */

public class Grid {

    /*
    Consists of List of List of Blocks
     */

    private final List<List<Block>> aGrid = new ArrayList<>();

    public Grid () {

        // initialize an empty grid
        for (int i = 0; i < GameUtils.GAMESIZE; i++) {
            List<Block> tmpRow = new ArrayList<>();
            aGrid.add(tmpRow);
            for (int j = 0; j < GameUtils.GAMESIZE; j++) {
                int rankBlock = GameUtils.gridOrderTopLeftToRightBottom(i, j);
                Coordinate tmpCoordinate = new Coordinate(i,j);
                Block tmpBlock = new Block(tmpCoordinate);
                tmpBlock.updatePrintCharacter(Integer.toString(rankBlock));
                aGrid.get(i).add(tmpBlock);
            }
        }
    }

    private Block getBlock(Coordinate pCoordinate) {
        /*
        Return a Block for a given Coordinate
         */
        int aRow = pCoordinate.getRow();
        int aCol = pCoordinate.getCol();

        return aGrid.get(aRow).get(aCol);
    }

    public void updateGrid(Fleet pFleet, List<Coordinate> pReceivedShots) {
        /*
        Update the grid with all the information contained by pFleet as well as all the shots taken
         */

        // First go through all received shots
        for (Coordinate c : pReceivedShots) {
            Block b = getBlock(c);
            b.updateStateShot();
        }

        // Then go thorugh all boats of the fleet and update where they are and whether they should be revealed
        for (Boat b : pFleet) {

            // List all Coordinate of the boat
            List<Coordinate> bCoordinates = b.getCoordinates();
            for (Coordinate c : bCoordinates) {
                // For each Block at the Coordiante do
                Block block = getBlock(c);

                // Set stateBoat
                block.updateStateBoat();

                // Set print character
                block.updatePrintCharacter(b.getTypePrintChar());

                // Set stateReveal (if the boat is destroyed, reveal)
                if (b.isDestroyed()) {
                    block.updateStateReveal();
                }

            }
        }
    }





    public void printAllStatus() {
        String tmp = "";
        tmp += getGridStateBoat();
        tmp += "\n" + getGridStateShot();
        tmp += "\n" + getGridStateReveal();

        System.out.println(tmp);
    }

    public String getGridStateBoat() {
        String tmp = "Boat\n";
        for (List<Block> innerlist : aGrid) {
            int rowNumber = innerlist.get(0).getCoordinate().getRow() % GameUtils.GAMESIZE;
            tmp += rowNumber + " ";
            for (Block b : innerlist) {
                tmp += b.getStateBoat() + "  ";
            }
            tmp += "\n";
        }
        return tmp;
    }

    public String getGridStateShot() {
        String tmp = "Shot\n";
        for (List<Block> innerlist : aGrid) {
            int rowNumber = innerlist.get(0).getCoordinate().getRow() % GameUtils.GAMESIZE;
            tmp += rowNumber + " ";
            for (Block b : innerlist) {
                tmp += b.getStateShot() + "  ";
            }
            tmp += "\n";
        }
        return tmp;
    }

    public String getGridStateReveal() {
        String tmp = "Reveal\n";
        for (List<Block> innerlist : aGrid) {
            int rowNumber = innerlist.get(0).getCoordinate().getRow() % GameUtils.GAMESIZE;
            tmp += rowNumber + " ";
            for (Block b : innerlist) {
                tmp += b.getStateReveal() + "  ";
            }
            tmp += "\n";
        }
        return tmp;
    }
}
