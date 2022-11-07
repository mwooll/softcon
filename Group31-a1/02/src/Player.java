import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Player {

    /**
    Subclasses PlayerHuman, PlayerComputer
    Difference only in
    - human or computer
    - placeFleet
    - callShot
     */

    // all fields are by default protected. subclasses can access
    boolean aIsHuman = false;
    final Fleet aFleet;
    final Grid aGrid;
    final List<Coordinate> aTakenShots = new ArrayList<>();
    final List<Coordinate> aReceivedShots = new ArrayList<>();

    public Player(Grid pGrid, boolean pIsHuman) {
        aFleet = new Fleet();
        aGrid = pGrid;
        if (pIsHuman) aIsHuman = true;
    }

    abstract void placeFleet();

    abstract Coordinate callShot();


    public boolean isHuman() {
        return aIsHuman;
    }

    boolean[] recordShot(Coordinate pCoordinate) {

        /**
         * Receive a valid Coordinate shot and check if a boat is hit. Update the Fleet, Boats and Grid accordingly
         * @param pCoordinate Coordinate of the valid shot taken at the Player
         * @return [isHit, getDestroyed] True if a boat got hit, True if that hit destroyed the boat
         */

        // Check the pCoordinate with the Fleet, receive if hit and if the boat got destroyed
        // Fleet does update the boat within checkShot
        boolean[] responseFleet = aFleet.checkShot(pCoordinate);

        // Regardless of hit, record the shot taken at my grid, update the grid with the taken shot
        aReceivedShots.add(pCoordinate);
        aGrid.updateShotAt(pCoordinate);

        // if the boat got destroyed with that shot, update the grid accordingly FOR ALL BLOCKS of that boat
        if (responseFleet[1]) {

            Boat boatDestroyed = null;

            // if a boat got destroyed, fetch the boat
            // checkShot makes sure that pCoordinate belongs to a boat
            for (Boat b : aFleet) {
                if (b.getCoordinates().contains(pCoordinate)) {
                    boatDestroyed = b;
                }
            }

            // For each Coordinate of the boatDestroyed, update the Block in Grid
            for (Coordinate c : boatDestroyed.getCoordinates()) {
                aGrid.updateShowDestroyed(c);
            }
        }

        return responseFleet;

    }

    String getBoatTypeString(Coordinate pCoordinate) {

        /**
         * Return the Type of the Boat at the given Coordinate in the Fleet
         * We assume that exactly one boat contains pCoordinate. Print Warning if nothing is found and return ""
         *
         * @param pCoordinate A Coordinate where a boat is placed
         * @return String Type of the boat at Coordinate pCoordinate
         */

        String outString = "";
        boolean foundBoat = false;

        for (Boat b : aFleet) {
            if (b.getCoordinates().contains(pCoordinate)) {
                foundBoat = true;
                outString = b.getTypeName();
            }
        }

        if (!foundBoat) {
            System.out.println("ERROR getBoatTypeString: No Boat found at " + pCoordinate + ". Returning empty String");

        }

        return outString;

    }

    boolean hasLost() {
        return aFleet.isDestroyed();
    }

}
