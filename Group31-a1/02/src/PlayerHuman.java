import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayerHuman extends Player {

    public PlayerHuman(Grid pGrid) {
        super(pGrid, true);
    }

    private String inputUser() {

        /**
         * Accept input from human user. Used for placeFleet and callShot
         */

        Scanner in = new Scanner(System.in);
        return in.nextLine();

    }

    public void placeFleet() {
        /**
         * Place all boats of the fleet, amount and length determined in BoatType, FleetSpec
         * No check if there are too many boats for the GAMESIZE
         *
         * The user is prompted for each boat of the fleet to enter start and end coordinate
         * Validate the user input via checks
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
            // We will put the valid Coordinate in this List
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

                // if the boat overlaps try again
                if (!checkOverlap) {
                    System.out.println("There is already a boat, try again");
                    ++tmp_ct;
                // otherwise the placement is valid, add them to the List of already used Coordinates for overlap check
                } else {
                    validUserInputCoordinates.addAll(tmpValidUserInputCoordinates);
                    break;
                }
            }


            // place the boat which was determined valid
            aFleet.placeBoat(b, validUserInputCoordinates);

            // update the grid accordingly
            for (Coordinate c : b.getCoordinates()) {
                aGrid.updateHasBoat(c);
                aGrid.updateBoatType(c, b.getTypePrintChar());
            }
        }
    }

    public Coordinate callShot() {
        /**
         * Prompt human user for a shot
         * Validate the user input via checks
         *
         * @returns Valid Coordinate
         */

        System.out.println("Enter your Shot, e.g. A2");
        int tmp_ct = 0;
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
                // System.out.println("Adding shot " + tmpUserInputCoordinate + " to list of aTakenShots");
                aTakenShots.add(tmpUserInputCoordinate);
                validUserInputCoordinate.add(tmpUserInputCoordinate);
                break;
            }

        }

        return validUserInputCoordinate.get(0);
    }
}