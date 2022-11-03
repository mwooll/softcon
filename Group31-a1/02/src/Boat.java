import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Boat  {

    private int aLen;
    private final String aInstanceName;
    private boolean aIsPlaced;
    private final BoatType aType;

    // aCoordinatesLeft = List of Coordinates which are not yet hit.
    private final List<Coordinate> aCoordinates, aCoordinatesLeft;

    public Boat(BoatType pType, String pInstanceName) {

        /*
         Constructor with a name for the instance.
         */

        aType = pType;
        aInstanceName = pInstanceName;

        for (BoatType bt : BoatType.values()) {
            if (bt.getTypeName() == aType.getTypeName()) {
                aLen = aType.getTypeLen();
                break;
            }
        }

        // init arraylist which is empty. ArrayList does not support a predefined capacity!
        aCoordinates = new ArrayList<Coordinate>();
        aCoordinatesLeft = new ArrayList<Coordinate>();

        // init as not yet placed
        aIsPlaced = false;

    }

    public BoatType getType() {
        return aType;
    }

    public String getTypeName() {
        return aType.getTypeName();
    }

    public char getTypePrintChar() {
        return aType.getTypePrintChar();
    }

    public int getLen() {
        return aType.getTypeLen();
    }

    public String getInstanceName() {
        return aInstanceName;
    }

    public boolean isDestroyed() {

        // todo: isEmpty instead of size()?

        // if the boat is not placed, it is never destroyed
        if (!isPlaced()) {return false;}

        return aCoordinatesLeft.size() == 0;
    }

    public boolean recordHit(Coordinate pCoordinate) {

        /*
        Receive a Coordinate and remove it from the list of leftover Coordinates if there is one. Return true if hit recorded, false otherwise
         */

        return aCoordinatesLeft.remove(pCoordinate);

    }

    public List<Coordinate> getCoordinates() {
        return Collections.unmodifiableList(aCoordinates);
    }

    public List<Coordinate> getCoordinatesLeft() {
        return Collections.unmodifiableList(aCoordinatesLeft);
    }

    public void setCoordinates(List<Coordinate> pListCoordinates) {

        /**
         * There are no checks or assertions, because the validity of the list of Coordinates is ensured
         * by the Player and GameMaster
         */

        for (Coordinate c : pListCoordinates) {
            aCoordinates.add(c);
            aCoordinatesLeft.add(c);
        }

        // set the boat to be placed
        aIsPlaced = true;
    }

    public boolean isPlaced() {return aIsPlaced;}


    public void printCoordinates() {
        for (Coordinate c : getCoordinates()) {
            System.out.println(c);
        }
    }

    public void printCoordinatesLeft() {
        for (Coordinate c : getCoordinatesLeft()) {
            System.out.println(c);
        }
    }

    public void printFullBoat() {
        String tmpString = "";
        tmpString += toString();
        tmpString += " " + aInstanceName;
        tmpString += " - Placed: " + isPlaced();
        tmpString += " - (" + getCoordinates() + ")";
        tmpString += " - (" + getCoordinatesLeft() + ")";
        tmpString += " - Destroyed: " + isDestroyed();

        System.out.println(tmpString);

    }



    @Override
    public String toString() {

        return aType + " (len " + aLen + ")";

    }



    public Iterator<Coordinate> iterator() {
        return aCoordinates.iterator();
    }
}
