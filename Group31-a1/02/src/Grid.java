import java.util.List;
import java.util.ArrayList;

/*
todo: Implement printAsTarget, printAsOcean
 */

public class Grid {

    /*
    Consists of nested Array (fixed length) of Blocks
     */
    private final Block[][] aGrid = new Block[GameUtils.GAMESIZE][GameUtils.GAMESIZE];

    public Grid () {

        // initialize the empty grid
        for (int i = 0; i < GameUtils.GAMESIZE; i++) {
            for (int j = 0; j < GameUtils.GAMESIZE; j++) {
                aGrid[i][j] = new Block(new Coordinate(i,j));
            }
        }
    }

    private Block getBlock(Coordinate pCoordinate) {
        /**
         * Return a Block for a given Coordinate
         * @param pCoordinate Coordiante for which to return the corresponding Block
         */

        return aGrid[pCoordinate.getRow()][pCoordinate.getCol()];
    }

    public void updateShotAt(Coordinate pCoordinate) {
        /**
         * Receive a Coordinate and update the corresponding Block
         * We can assume a valid Coordinate
         * @param pCoordinate A Coordinate where the Block should update its shotAt
         */

        Block b = getBlock(pCoordinate);
        b.updateShotAt();

        // for debugging
//        System.out.println("Updated shotAt at Block " + b);

    }

    public void updateHasBoat(Coordinate pCoordinate) {
        /**
         * Receive a Coordinate and update the corresponding Block
         * We can assume a valid Coordinate
         * @param pCoordinate A Coordinate where the Block should update its hasBoat
         */

        Block b = getBlock(pCoordinate);
        b.updateHasBoat();

        // for debugging
//        System.out.println("Updated hasBoat at Block " + b);

    }

    public void updateShowDestroyed(Coordinate pCoordinate) {
        /**
         * Receive a Coordinate and update the corresponding Block
         * We can assume a valid Coordinate
         * @param pCoordinate A Coordinate where the Block should update its showDestroyed
         */

        Block b = getBlock(pCoordinate);
        b.updateShowDestroyed();

        // for debugging
//        System.out.println("Updated showDestroyed at Block " + b);

    }

    public void updateBoatType(Coordinate pCoordinate, char pBoatType) {
        /**
         * Receive a Coordinate and update the corresponding Block
         * We can assume a valid Coordinate
         * @param pCoordinate A Coordinate where the Block should update its boatType
         */

        Block b = getBlock(pCoordinate);
        b.updateBoatType(pBoatType);

        // for debugging
//        System.out.println("Updated boatType at Block " + b);

    }

    public void printOcean() {
        /**
         * Print the Gird as an Ocean Map
         */

        System.out.println("===== OCEAN GRID =====");
        // A B C D E ...
        System.out.print(" ");
        for (int i = 0; i < GameUtils.GAMESIZE; i++) {
            System.out.print(" " + GameUtils.convertIntToLetter(i));
        }
        // +-+-+- ...
        System.out.print("\n ");
        for (int i = 0; i < GameUtils.GAMESIZE; i++) {
            System.out.print("+-");
        }
        // rows and cols
        System.out.print("+\n");
        for (int i = 0; i < GameUtils.GAMESIZE; i++) {
            System.out.print(i + "|");
            for (int j = 0; j < GameUtils.GAMESIZE; j++) {
                Block block_ij = getBlock(new Coordinate(i,j));

                boolean shotAt_ij = block_ij.getShotAt();
                boolean hasBoat_ij = block_ij.getHasBoat();

                // if shot at and ...
                if (shotAt_ij) {
                    // ... has boat
                    if (hasBoat_ij) {
                        // print uppercase X
                        System.out.print("X|");
                    // ... no boat
                    } else {
                        // print missed shot o
                        System.out.print("o|");
                    }
                }
                // if not shot at and ...
                if (!shotAt_ij) {
                    // ... has boat
                    if (hasBoat_ij) {
                        // print boatType
                        System.out.print(block_ij.getBoatType() + "|");
                    // ... no boat
                    } else {
                        // print empty
                        System.out.print(" |");
                    }
                }
            }
            System.out.print(i + "\n");
        }
        System.out.print(" ");
        // +-+-+- ...
        for (int i = 0; i < GameUtils.GAMESIZE; i++) {
            System.out.print("+-");
        }
        System.out.print("+");
        // A B C D E ...
        System.out.print(" \n ");
        for (int i = 0; i < GameUtils.GAMESIZE; i++) {
            System.out.print(" " + GameUtils.convertIntToLetter(i));
        }
        System.out.print("\n");

    }

    public void printTarget() {
        /**
         * Print the Gird as a Target Map
         */

        System.out.println("===== TARGET GRID =====");
        // A B C D E ...
        System.out.print(" ");
        for (int i = 0; i < GameUtils.GAMESIZE; i++) {
            System.out.print(" " + GameUtils.convertIntToLetter(i));
        }
        // +-+-+- ...
        System.out.print("\n ");
        for (int i = 0; i < GameUtils.GAMESIZE; i++) {
            System.out.print("+-");
        }
        // rows and cols
        System.out.print("+\n");
        for (int i = 0; i < GameUtils.GAMESIZE; i++) {
            System.out.print(i + "|");
            for (int j = 0; j < GameUtils.GAMESIZE; j++) {
                Block block_ij = getBlock(new Coordinate(i,j));

                boolean shotAt_ij = block_ij.getShotAt();
                boolean hasBoat_ij = block_ij.getHasBoat();
                boolean showDestroyed_ij = block_ij.getShowDestroyed();

                // if shot at and ...
                if (shotAt_ij) {
                    // ... has boat
                    if (hasBoat_ij) {
                        // ... show destroyed
                        if (showDestroyed_ij) {
                            // print boatType
                            System.out.print(block_ij.getBoatType() + "|");
                        }
                        // ... not destroyed yet
                        else {
                            // print uppercase X
                            System.out.print("X|");
                        }
                    // ... no boat
                    } else {
                        // print missed shot o
                        System.out.print("o|");
                    }
                }
                // if not shot at
                if (!shotAt_ij) {
                    // print empty
                    System.out.print(" |");
                }
            }
            System.out.print(i + "\n");
        }
        System.out.print(" ");
        // +-+-+- ...
        for (int i = 0; i < GameUtils.GAMESIZE; i++) {
            System.out.print("+-");
        }
        // A B C D E ...
        System.out.print(" \n");
        for (int i = 0; i < GameUtils.GAMESIZE; i++) {
            System.out.print(" " + GameUtils.convertIntToLetter(i));
        }
        System.out.print("\n");
    }

    public void printTargetRemaining() {

        /**
         * Print the Grid as Target map when the game is over
         * Show all the Boats which have not yet been shot at
         */

        System.out.println("===== FINAL TARGET GRID =====");
        // A B C D E ...
        System.out.print(" ");
        for (int i = 0; i < GameUtils.GAMESIZE; i++) {
            System.out.print(" " + GameUtils.convertIntToLetter(i));
        }
        // +-+-+- ...
        System.out.print("\n ");
        for (int i = 0; i < GameUtils.GAMESIZE; i++) {
            System.out.print("+-");
        }
        // rows and cols
        System.out.print("+\n");
        for (int i = 0; i < GameUtils.GAMESIZE; i++) {
            System.out.print(i + "|");
            for (int j = 0; j < GameUtils.GAMESIZE; j++) {
                Block block_ij = getBlock(new Coordinate(i,j));

                boolean shotAt_ij = block_ij.getShotAt();
                boolean hasBoat_ij = block_ij.getHasBoat();
                boolean showDestroyed_ij = block_ij.getShowDestroyed();

                // if shot at and ...
                if (shotAt_ij) {
                    // ... has boat
                    if (hasBoat_ij) {
                        // ... show destroyed
                        if (showDestroyed_ij) {
                            // print boatType
                            System.out.print(block_ij.getBoatType() + "|");
                        }
                        // ... not destroyed yet
                        else {
                            // print uppercase X
                            System.out.print("X|");
                        }
                        // ... no boat
                    } else {
                        // print missed shot o
                        System.out.print("o|");
                    }
                }
                // if not shot at
                if (!shotAt_ij) {
                    // if there is a boat, show it
                    if (hasBoat_ij) {
                        System.out.print(block_ij.getBoatType() + "|");
                    }
                    // else print empty
                    else {
                        System.out.print(" |");
                    }
                }
            }
            System.out.print(i + "\n");
        }
        System.out.print(" ");
        // +-+-+- ...
        for (int i = 0; i < GameUtils.GAMESIZE; i++) {
            System.out.print("+-");
        }
        // A B C D E ...
        System.out.print(" \n");
        for (int i = 0; i < GameUtils.GAMESIZE; i++) {
            System.out.print(" " + GameUtils.convertIntToLetter(i));
        }
        System.out.print("\n");


    }
}
