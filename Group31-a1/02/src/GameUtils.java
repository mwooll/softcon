import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Math;

public class GameUtils {

    /*
    todo: validUserInputXXX could be replaced with one function that takes pattern as input
    todo: convertUserInputXXX could be repalced with one function that takes List of any length and outputs List
     */

    // Maximum GAMESIZE supported: 10
    public static final int GAMESIZE = 3;
    public static final int MAX_TRY_USER_INPUT = 20, MAX_TRY_COMP_SHOOT = 1000, MAX_TRY_COMP_PLACE = 1000;

    public static int gridOrderTopLeftToRightBottom(int pRow, int pCol) {

        /**
         * To make Coordinates in a Grid comparable, number them from top left to bottom right
         */

        return pRow * GameUtils.GAMESIZE + pCol;
    }

    public static boolean validCoordinate(Coordinate pCoordinate) {

        // no negative numbers
        if (pCoordinate.getRow() < 0 || pCoordinate.getCol() < 0) {return false;}

        // not outside GAMESIZE
        if (pCoordinate.getRow() > GAMESIZE-1  || pCoordinate.getCol() > GAMESIZE-1) {
            return false;
        } else {
            return true;
        }
    }




    public static boolean checkUserInputPlacement(String pString, BoatType pBoatType) {

        /**
        Receive a user input String of form A2,A5 for boat placement and the type of the boat to be placed
        Check if the string comes in the valid format
        Check if both coordinates are valid coordinates
        Check if they form a straight line
        Check if length of the straight line matches the required length of the boat

        Return false if any of the checks fails, true otherwise
         */

        int aLenBoat = pBoatType.getTypeLen();

        // check if valid pattern e.g. A2,A5
        if (!GameUtils.validUserInputPlacement(pString)) {
            System.out.println("Pattern (e.g. A2,A5) not matched exactly, try again");
            return false;
        }

        // convert to two coordinates
        List<Coordinate> userInputStartEnd = GameUtils.convertUserInputPlacementToCoordinates(pString);

        // check if both coordinates are valid
        for (Coordinate c : userInputStartEnd) {
            if (!GameUtils.validCoordinate(c)) {
                System.out.println("Coordinate " + c.printPretty() + " from userInput is outside grid, try again");
                return false;
            }
        }

        // check if straight line
        if (!GameUtils.isStraightLine(userInputStartEnd)) {
            System.out.println("Coordinates not a straight line, try again");
            return false;
        }

        // generate all Coordinates
        List<Coordinate> userInputCoordinates = GameUtils.generateCoordinatesFromStartEnd(userInputStartEnd);

        // check if len of boat and input matches
        if (aLenBoat != userInputCoordinates.size()) {
            System.out.println("There should be a line of " + aLenBoat + " Coordinates, you entered " + userInputCoordinates.size() + ", try again");
            return false;
        }

        return true;

    }

    public static int convertLetterToInt(char pLetter) {
        /**
        Helper to convert pLetter<char> to row/col integer representation.
        Uses Unicode decimal values.
        Example: A = 0, B = 1
        todo: Checked: Maximal range is A-Z?
         */
       return (int) pLetter - (int) 'A';
    }

    public static char convertIntToLetter(int pInt) {
        /**
        Helper to convert pInt<int> from row/col integer to the letter (for printing)
        Se convertLetterToInt for example
         */
        int pIntWithBaseline = pInt + (int) 'A';
        return (char) pIntWithBaseline;
    }

    public static boolean validUserInputPlacement(String pString) {
        /**
        Check if a user input for placement fulfills pattern required, e.g. A2,A5
        Return false if string is not valid, true otherwise

         We ONLY accept GAMESIZE <= 10, hence only one digit after letter!
         */
        Pattern patternExpected = Pattern.compile("^[A-Z]{1}[0-9]{1},[A-Z]{1}[0-9]{1}$");
        Matcher matcher = patternExpected.matcher(pString);
        return matcher.find();
    }

    public static List<Coordinate> convertUserInputPlacementToCoordinates(String pString) {

        /**
        Given a valid user input String for boat placement, convert to a list of two Coordinates
        DOES NOT CHECK validity of the coordinates, only assumes valid regex e.g. A2,A5
         */

        List<Coordinate> outListCoordiantes = new ArrayList<>();

        int row1 = Integer.parseInt(pString.substring(1,2));
        int col1 = GameUtils.convertLetterToInt(pString.charAt(0));

        int row2 = Integer.parseInt(pString.substring(4,5));
        int col2 = GameUtils.convertLetterToInt(pString.charAt(3));

        outListCoordiantes.add(new Coordinate(row1, col1));
        outListCoordiantes.add(new Coordinate(row2, col2));

        return outListCoordiantes;
    }

    public static boolean isStraightLine(List<Coordinate> pListCoordinates) {

        /**
        Given a list of two Coordinate, check if they form a straight line. Does not check length
        Assumes valid Coordinates, hence DOES NOT CHECK for validity of coordinates

        Returns true if the two Coordinate form straight line, false otherwise
         */

        // Check that length is 2
        assert pListCoordinates.size() == 2;

        if (pListCoordinates.get(0).getRow() == pListCoordinates.get(1).getRow() || pListCoordinates.get(0).getCol() == pListCoordinates.get(1).getCol()) {
            return true;
        } else {
            return false;
        }

    }

    public static List<Coordinate> generateCoordinatesFromStartEnd (List<Coordinate> pListCoordinates) {

        /**
        Assumes a list of two valid coordinates that form a line
        DOES NOT CHECK for those conditions, they have been checked before

        Returns a list of all the Coordinates between the two supplied Coordinates
         */

        List<Coordinate> outListCoordinates = new ArrayList<>();

        int row1 = pListCoordinates.get(0).getRow();
        int col1 = pListCoordinates.get(0).getCol();
        int row2 = pListCoordinates.get(1).getRow();
        int col2 = pListCoordinates.get(1).getCol();

        // if the two coordinates are identical return an empty list
        if (row1 == row2 && col1 == col2) {
            return outListCoordinates;
        }

        // we know it is a straight line, so either row or col are identical
        if (row1 == row2) {
            int minCol = Math.min(col1,col2);
            int maxCol = Math.max(col1,col2);
            for (int i = 0; i <= maxCol-minCol; i++) {
                outListCoordinates.add(new Coordinate(row1, minCol+i));
            }
        }

        if (col1 == col2) {
            int minRow = Math.min(row1,row2);
            int maxRow = Math.max(row1,row2);
            for (int i = 0; i <= maxRow-minRow; i++) {
                outListCoordinates.add(new Coordinate(minRow+i, col1));
            }
        }

        return outListCoordinates;

    }




    public static boolean checkUserInputShot(String pString) {

        /**
        Receive a user input String of form A0 for shot calling
        Check if the string comes in the valid format
        Check if the Coordinate is valid

        Return false if any of the checks fails, true otherwise
         */

        // check if valid pattern e.g. A2
        if (!GameUtils.validUserInputShot(pString)) {
            System.out.println("Pattern (e.g. A2) not matched exactly, try again");
            return false;
        }

        // convert to one coordinates
        Coordinate userInputCoordinate = GameUtils.convertUserInputShotToCoordinates(pString);

        // check if coordinate is valid
        if (!GameUtils.validCoordinate(userInputCoordinate)) {
                System.out.println("Coordinate shot " + userInputCoordinate.printPretty() + " from userInput is outside grid (Gridsize " + GameUtils.GAMESIZE + ")" + ", try again");
                return false;
        }

        return true;
    }

    public static boolean validUserInputShot(String pString) {

        /**
        Check if a user input for shot calling fulfills pattern required, e.g. A2
        Return false if string is not valid, true otherwise

         We ONLY accept GAMESIZE <= 10, hence only one digit after letter!
         */

        Pattern patternExpected = Pattern.compile("^[A-Z]{1}[0-9]{1}$");
        Matcher matcher = patternExpected.matcher(pString);
        return matcher.find();
    }

    public static Coordinate convertUserInputShotToCoordinates(String pString) {

        /**
        Return a Coordinate from the valid string input for shot calling of the user
         */

        int row1 = Integer.parseInt(pString.substring(1,2));
        int col1 = GameUtils.convertLetterToInt(pString.charAt(0));

        return new Coordinate(row1, col1);

    }

}


