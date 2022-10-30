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

    public static final int GAMESIZE = 4, MAX_TRY_USER_INPUT = 20, MAX_TRY_COMP_SHOOT = 1000;

    public static int gridOrderTopLeftToRightBottom(int pRow, int pCol) {
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

        /*
        Receive a user input String of form A2,A5 for boat placement and the type of the boat to be placed
        Check if the string comes in the valid format
        Check if both coordinates are valid coordinates
        Check if they form a straight line
        Check if length of the staright line matches the required lenght of the boat

        Return false if any of the checks fails, true otherwise
         */

        int aLenBoat = pBoatType.getTypeLen();

        // check if valid pattern e.g. A2,A5
        if (!GameUtils.validUserInputPlacement(pString)) {
            System.out.println("Pattern (e.g. A2,A5) not machted exactly, try again");
            return false;
        }

        // convert to two coordinates
        List<Coordinate> userInputStartEnd = GameUtils.convertUserInputPlacementToCoordinates(pString);

        // check if both coordinates are valid
        for (Coordinate c : userInputStartEnd) {
            if (!GameUtils.validCoordinate(c)) {
                System.out.println("Coordinate " + c + " from userInput is outside grid, try again");
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
        /*
        Helper to convert pLetter<char> to row/col integer representation.
        Uses Unicode decimal values.
        Example: A = 0, B = 1
        Not checked: Maximal range is A-Z
         */
       return (int) pLetter - (int) 'A';
    }

    public static char convertIntToLetter(int pInt) {
        /*
        Helper to convert pInt<int> from row/col integer to the letter (for printing)
        Se convertLetterToInt for example
         */
        int pIntWithBaseline = pInt + (int) 'A';
        return (char) pIntWithBaseline;
    }

    public static boolean validUserInputPlacement(String pString) {
        /*
        Check if a user input for placement fulfills pattern required, e.g. A2,A5
        Return false if string is not valid, true otherwise
         */
        Pattern patternExpected = Pattern.compile("^[A-Z]{1}[0-9]{1},[A-Z]{1}[0-9]{1}$");
        Matcher matcher = patternExpected.matcher(pString);
        return matcher.find();
    }

    public static List<Coordinate> convertUserInputPlacementToCoordinates(String pString) {

        /*
        Given a valid user input String for boat placement, convert to a list of two Coordiantes
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

        /*
        Given a list of two Coordinate, check if they form a straight line. Does not check length
        Assumes valid Coordinates, hence DOES NOT CHECK for validity of coordinates

        Returns true if the two Coordiante form straight line, false otherwise
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

        /*
        Assumes a list of two valid coordinates that form a line
        DOES NOT CHECK for those conditions

        Returns a list of all the Coordinates between the two supplied Coordinates
         */

        // Check that length is 2
        assert pListCoordinates.size() == 2;
        assert GameUtils.isStraightLine(pListCoordinates) : "List of Coordinates is not a straight line";

        List<Coordinate> outListCoordiantes = new ArrayList<>();

        int row1 = pListCoordinates.get(0).getRow();
        int col1 = pListCoordinates.get(0).getCol();
        int row2 = pListCoordinates.get(1).getRow();
        int col2 = pListCoordinates.get(1).getCol();

        // if the two coordinates are identical return an empty list
        if (row1 == row2 && col1 == col2) {
            return outListCoordiantes;
        }

        // we know it is a straight line, so either row or col are identical
        if (row1 == row2) {
            int minCol = Math.min(col1,col2);
            int maxCol = Math.max(col1,col2);
            for (int i = 0; i <= maxCol-minCol; i++) {
                outListCoordiantes.add(new Coordinate(row1, minCol+i));
            }
        }

        if (col1 == col2) {
            int minRow = Math.min(row1,row2);
            int maxRow = Math.max(row1,row2);
            for (int i = 0; i <= maxRow-minRow; i++) {
                outListCoordiantes.add(new Coordinate(minRow+i, col1));
            }
        }

        return outListCoordiantes;

    }




    public static boolean checkUserInputShot(String pString) {

        /*
        Receive a user input String of form A0 for shot calling
        Check if the string comes in the valid format
        Check if the Coordiante is valid

        Return false if any of the checks fails, true otherwise
         */

        // check if valid pattern e.g. A2
        if (!GameUtils.validUserInputShot(pString)) {
            System.out.println("Pattern (e.g. A2) not machted exactly, try again");
            return false;
        }

        // convert to one coordinates
        Coordinate userInputCoordinate = GameUtils.convertUserInputShotToCoordinates(pString);

        // check if coordinate is valid
        if (!GameUtils.validCoordinate(userInputCoordinate)) {
                System.out.println("Coordinate shot " + userInputCoordinate + " from userInput is outside grid (Gridsize " + GameUtils.GAMESIZE + ")" + ", try again");
                return false;
        }

        return true;
    }

    public static boolean validUserInputShot(String pString) {

        /*
        Check if a user input for shot calling fulfills pattern required, e.g. A2
        Return false if string is not valid, true otherwise
         */

        Pattern patternExpected = Pattern.compile("^[A-Z]{1}[0-9]{1}$");
        Matcher matcher = patternExpected.matcher(pString);
        return matcher.find();
    }

    public static Coordinate convertUserInputShotToCoordinates(String pString) {

        /*
        Return a Coordinate from the valid string input for shot calling of the user
         */

        int row1 = Integer.parseInt(pString.substring(1,2));
        int col1 = GameUtils.convertLetterToInt(pString.charAt(0));

        return new Coordinate(row1, col1);

    }

}


