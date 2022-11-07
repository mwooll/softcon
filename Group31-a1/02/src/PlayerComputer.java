import java.util.*;

public class PlayerComputer extends Player {

    private final Random rand;

    public PlayerComputer(Grid pGrid) {
        super(pGrid, false);
        rand = new Random();
    }

    private Coordinate generateRandomCoordinate() {

        /**
         Generate random Coordinate within grid
         @return a VALID (in Grid) Coordinate
         */
        int randRow = rand.nextInt(GameUtils.GAMESIZE);
        int randCol = rand.nextInt(GameUtils.GAMESIZE);
        Coordinate randCoordinate = new Coordinate(randRow, randCol);

        return randCoordinate;
    }

    public void placeFleet() {
        /**
         * Place all boats of the fleet, amount and length determined in BoatType, FleetSpec
         * No check if there are too many boats for the GAMESIZE
         *
         * Computer places each boat of his fleet randomly
         */
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
            while (true) {
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
//            String tmpPlayerType;
//            if (isHuman()) {
//                tmpPlayerType = "Human Player";
//            } else {
//                tmpPlayerType = "Computer Player";
//            }

            // debugging - show computers placed fleet
//            System.out.println(tmpPlayerType + " placed boat " + b.getInstanceName() + " of len " + b.getLen() + ":");
//            List<String> validBoatCoordinatesPretty = new ArrayList<String>();
//            validBoatCoordinates.forEach((c) -> validBoatCoordinatesPretty.add(c.printPretty()));
//            System.out.println(String.join(",", validBoatCoordinatesPretty));

        }
    }

    public Coordinate callShot() {

        /**
         * No check for validity (in Grid), generateRandomCoordinate makes sure it is in Grid
         * @returns Valid Coordinate
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
            while (aTakenShots.contains(randCoordinate)) {
                tmp_ct++;
                randCoordinate = generateRandomCoordinate();
            }
            // for debugging, print how many tries the computer needed to find a free Coordinate
            // System.out.println("Computer number of tries to callShot: " + tmp_ct);

            // debugging, throw error if the called shot is in the list of already taken shots
            if (aTakenShots.contains(randCoordinate)) {
                System.out.println("Computer has no more Coordinates to shoot at, abort the game");
                System.exit(0);
            }

            outShot = randCoordinate;

        }

        // finally, the valid shot is added to aTakenShots
        aTakenShots.add(outShot);

        return outShot;
    }
}
