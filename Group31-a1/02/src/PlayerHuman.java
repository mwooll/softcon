import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayerHuman implements Player {

    // todo: will be private in the end. public for testing
    public final Fleet aFleet;
    public final Grid aGrid;
    public final List<Coordinate> aTakenShots = new ArrayList<>();
    public final List<Coordinate> aReceivedShots = new ArrayList<>();

//    List <Coordinate> aTakenShots = new ArrayList<>();

    public PlayerHuman(Grid pGrid) {
        aFleet = new Fleet();
        aGrid = pGrid;
    }

    public boolean isHuman() {return true;}

    public boolean hasLost() {
        return aFleet.isDestroyed();
    }


    public void placeFleetFromList(HashMap<String, List<Coordinate>> pPlacement) {
        for (Boat b : aFleet) {
            aFleet.placeBoat(b, pPlacement.get(b.getInstanceName()));
        }
    }


    /*
    Fleet placement
    */
    public String inputUser() {

        Scanner in = new Scanner(System.in);
        return in.nextLine();

    }

    public void placeFleet() {

        /*
        User must input all the positions
         */
        System.out.println("Enter Start and End of Boat, e.g. A2,A5");
        for (Boat b : aFleet) {

            // if for some reason the boat is already placed, skip it
            if (b.isPlaced()) {
                System.out.println("Boat " + b.getInstanceName() + " is already placed, next one");
                continue;
            }

            System.out.println("Please enter the position of your " + b.getInstanceName() + " of Length " + b.getType().getTypeLen() + ":");
            int tmp_ct = 0;
            String validUserInput = new String();
            List<Coordinate> validUserInputCoordinates = new ArrayList<>();
            while (tmp_ct < GameUtils.MAX_TRY_USER_INPUT) {

                // user inputs positions
                String tmpUserInput = inputUser();

                // string is checked
                boolean checkInput = GameUtils.checkUserInputPlacement(tmpUserInput, b.getType());

                if (!checkInput) {
                    ++tmp_ct;
                    continue;
                }

                // user input is formally valid
                List<Coordinate> tmpValidUserInputStartEnd = GameUtils.convertUserInputPlacementToCoordinates(tmpUserInput);
                List<Coordinate> tmpValidUserInputCoordinates = GameUtils.generateCoordinatesFromStartEnd(tmpValidUserInputStartEnd);

                // now check with the fleet if the Coordinates are already in use
                boolean checkOverlap = aFleet.validateOverlap(tmpValidUserInputCoordinates);

                if (!checkOverlap) {
                    System.out.println("There is already a boat, try again");
                    ++tmp_ct;
                } else {
                    validUserInputCoordinates.addAll(tmpValidUserInputCoordinates);
                    break;
                }


            }

            aFleet.placeBoat(b, validUserInputCoordinates);
        }
    }

    public Coordinate callShot() {
        /*
        User must input one coordinate as a shot
        Check it against his already taken shots
         */

        System.out.println("Enter your Shot, e.g. A2");
        int tmp_ct = 0;
        String validUserInput = new String();
        List<Coordinate> validUserInputCoordinate = new ArrayList<>();
        while (tmp_ct < GameUtils.MAX_TRY_USER_INPUT) {

            // user inputs positions
            String tmpUserInput = inputUser();

            // string is checked
            boolean checkInput = GameUtils.checkUserInputShot(tmpUserInput);

            if (!checkInput) {
                ++tmp_ct;
                continue;
            }

            // check if this positions has already been shot at
            Coordinate tmpUserInputCoordinate = GameUtils.convertUserInputShotToCoordinates(tmpUserInput);
            if (aTakenShots.contains(tmpUserInputCoordinate)) {
                System.out.println("You shot there already, choose another one");
            } else {
                System.out.println("Adding shot " + tmpUserInputCoordinate + " to list of aTakenShots");
                aTakenShots.add(tmpUserInputCoordinate);
                validUserInputCoordinate.add(tmpUserInputCoordinate);
                break;
            }

        }

        return validUserInputCoordinate.get(0);
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


}