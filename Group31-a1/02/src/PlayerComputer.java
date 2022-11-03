import java.util.*;


/*
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
        int length;
        int tmp_ct;
        int randRow;
        int randCol;
        Coordinate randomCoordinate; 
        Coordinate verticalCoordinate;
        Coordinate horizontalCoordinate;
        List<Coordinate> verticalCoordinates;
        List<Coordinate> horizontalCoordinates;
        List<Coordinate> verticalBoat;
        List<Coordinate> horizontalBoat;
        boolean noVerticalOverlap;
        boolean noHorizontalOverlap;
        Random randNum = new Random();
        int coinFlip;


        for (Boat b : aFleet) {
            length = b.getLen();
            if (length > GameUtils.GAMESIZE) continue; // ignore boats that are too long
            tmp_ct = 0;
            List<Coordinate> validBoatCoordinates = new ArrayList<>();
            while (tmp_ct < GameUtils.MAX_TRY_COMP_PLACE) {
                randRow = rand.nextInt(GameUtils.GAMESIZE - length + 1);
                randCol = rand.nextInt(GameUtils.GAMESIZE - length + 1);
                randomCoordinate = new Coordinate(randRow, randCol);

                verticalCoordinate = new Coordinate(randRow, randCol + length - 1);
                horizontalCoordinate = new Coordinate(randRow + length - 1, randCol);

                verticalCoordinates = List.of(new Coordinate[]{randomCoordinate, verticalCoordinate});
                horizontalCoordinates = List.of(new Coordinate[]{randomCoordinate, horizontalCoordinate});

                verticalBoat = GameUtils.generateCoordinatesFromStartEnd(verticalCoordinates);
                horizontalBoat = GameUtils.generateCoordinatesFromStartEnd(horizontalCoordinates);

                // now check with the fleet if the Coordinates are already in use
                noVerticalOverlap = aFleet.validateOverlap(verticalBoat);
                noHorizontalOverlap = aFleet.validateOverlap(horizontalBoat);

                if (!noVerticalOverlap && !noHorizontalOverlap) ++tmp_ct;
                else {
                    if (noVerticalOverlap && !noHorizontalOverlap) {
                        validBoatCoordinates.addAll(verticalBoat);
                    }
                    else if (!noVerticalOverlap && noHorizontalOverlap){
                        validBoatCoordinates.addAll(horizontalBoat);
                    }
                    else {
                        coinFlip = randNum.nextInt(2);
                        if (coinFlip == 0){
                            validBoatCoordinates.addAll(horizontalBoat);
                        }
                        else {
                            validBoatCoordinates.addAll(verticalBoat);
                        }
                    }
                    break;
                }
            }

            // place the b
            aFleet.placeBoat(b, validBoatCoordinates);

            // update the grid accordingly
            for (Coordinate c : b.getCoordinates()) {
                aGrid.updateHasBoat(c);
                aGrid.updateBoatType(c, b.getTypePrintChar());
            }

            // debug
            System.out.println("Computer placed b %s at: " + b.getTypeName());
            System.out.println(Arrays.toString(validBoatCoordinates.toArray()));
        }
    }
    public void placeFleetFromList(HashMap<String, List<Coordinate>> pPlacement) {
        for (Boat b : aFleet) {

            // place boat
            aFleet.placeBoat(b, pPlacement.get(b.getInstanceName()));

            // update the grid accordingly
            for (Coordinate c : b.getCoordinates()) {
                aGrid.updateHasBoat(c);
                aGrid.updateBoatType(c, b.getTypePrintChar());
            }

            // debug
            System.out.println("Computer placed b " + b.getInstanceName() + " at: " + b.getCoordinates());
        }
    }

    public boolean hasLost() {
        return aFleet.isDestroyed();
    }

    public boolean[] recordShot(Coordinate pCoordinate) {

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
            // System.out.println("Computer number of tries: " + tmp_ct);
            outShot = randCoordinate;

        }

        // finally, the valid shot is added to aTakenShots
        // System.out.println("Adding shot " + outShot + " to list of aTakenShots");
        aTakenShots.add(outShot);
        // System.out.println("List of taken shots: " + aTakenShots);

        return outShot;
    }

    public String getBoatTypeString(Coordinate pCoordinate) {
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
}
