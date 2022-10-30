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

        /*
        Receive a valid Coordinate shot and check if a boat is hit. Update the Fleet and Boats accordingly
         */

        // The Fleet records all coordinates used for the placements of boats. Hence, it's sufficient to check that list
        // First check if there is a boat on that coordinate
        boolean isHit = aFleet.checkShot(pCoordinate);
        boolean gotDestroyed = false;

        // If it's a hit, record the hit on the boat
        if (isHit) {
            for (Boat boat : aFleet) {
                boat.recordHit(pCoordinate);
                gotDestroyed = boat.isDestroyed();
            }
        }

        // Regardless of hit, record the shot taken at my grid
        aReceivedShots.add(pCoordinate);
        return new boolean[] {isHit, gotDestroyed};
    }

    private Coordinate generateRandomCoordinate() {

        /*
        Generate random Coordinate within grid
         */
        int randRow = rand.nextInt(GameUtils.GAMESIZE-1);
        int randCol = rand.nextInt(GameUtils.GAMESIZE-1);
        Coordinate randCoordinate = new Coordinate(randRow, randCol);

        return randCoordinate;
    }

    public Coordinate callShot() {

        if (aTakenShots.isEmpty()) {
            return generateRandomCoordinate();
        }
        Coordinate randCoordinate = generateRandomCoordinate();
        int tmp_ct = 0;
        while (aTakenShots.contains(randCoordinate) && tmp_ct < GameUtils.MAX_TRY_COMP_SHOOT) {
            tmp_ct++;
            randCoordinate = generateRandomCoordinate();
        }

        return randCoordinate;
    }
}
