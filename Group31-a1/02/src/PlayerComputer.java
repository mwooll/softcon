import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


/*
Will implement Player
todo: Implement placeFleet
 */
public class PlayerComputer implements Player {

    public final Fleet aFleet;
    public final Grid aGrid;
    public final List<Coordinate> aTakenShots = new ArrayList<>();
    public final List<Coordinate> aReceivedShots = new ArrayList<>();

    private final Random rand;

    public PlayerComputer(Grid pGrid) {
        aFleet = new Fleet();
        aGrid = pGrid;
        rand = new Random();
    }

    public boolean isHuman() {return false;}

    public void placeFleet() {
        placeFleetFromList(TestUtils.generatePlacement4by4());
    }
    public void placeFleetFromList(HashMap<String, List<Coordinate>> pPlacement) {
        for (Boat b : aFleet) {
            aFleet.placeBoat(b, pPlacement.get(b.getInstanceName()));
        }
    }

    public boolean hasLost() {
        return aFleet.isDestroyed();
    }

    public boolean[] recordShot(Coordinate pCoordinate) {

        /**
         * Receive a valid Coordinate shot and check if a boat is hit. Update the Fleet and Boats accordingly
         * @param pCoordinate Coordinate of the valid shot taken at the Player
         * @return [isHit, getDestroyed] True if a boat got hit, True if that hit destroyed the boat
         *
         * todo: How to return the BoatType or the BoatTypeName of the boat which got destroyed? List<Object>, HashMap?
         */

        // Check the pCoordinate with the Fleet, receive if hit and if the boat got destroyed
        // Fleet does update the boat within checkShot
        boolean[] responseFleet = aFleet.checkShot(pCoordinate);

        // Regardless of hit, record the shot taken at my grid
        aReceivedShots.add(pCoordinate);
        return responseFleet;
    }

    private Coordinate generateRandomCoordinate() {

        /**
        Generate random Coordinate within grid
        @return a VALID (in Grid) Coordinate
         */
        int randRow = rand.nextInt(GameUtils.GAMESIZE-1);
        int randCol = rand.nextInt(GameUtils.GAMESIZE-1);
        Coordinate randCoordinate = new Coordinate(randRow, randCol);

        return randCoordinate;
    }

    public Coordinate callShot() {

        /**
        No check for validity (in Grid), generateRandomCoordinate makes sure it is in Grid
        */

        // init Coordinate
        Coordinate outShot;

        // if no shot was recorded yet, pick one at random
        if (aTakenShots.isEmpty()) {
            outShot = generateRandomCoordinate();
        } else {
            // otherwise try to find a shot which has not been called yet
            Coordinate randCoordinate = generateRandomCoordinate();
            int tmp_ct = 0;
            while (aTakenShots.contains(randCoordinate) && tmp_ct < GameUtils.MAX_TRY_COMP_SHOOT) {
                tmp_ct++;
                randCoordinate = generateRandomCoordinate();
            }
            // for debugging, print how many tries the computer needed to find a free Coordinate
            System.out.println("Computer number of tries: " + tmp_ct);
            outShot = randCoordinate;

        }

        // finally, the valid shot is added to aTakenShots
        System.out.println("Adding shot " + outShot + " to list of aTakenShots");
        aTakenShots.add(outShot);
        System.out.println("List of taken shots: " + aTakenShots);

        return outShot;
    }
}
