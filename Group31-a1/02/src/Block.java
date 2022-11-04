public class Block {

    /**
     * A Block contains a Coordinate, knows if
     * it's shot at
     * if there is a boat
     * if the boat is destroyed
     * and the boatType
     */

    private boolean shotAt = false;
    private boolean hasBoat = false;
    private boolean showDestroyed = false;

    private char boatType;
    private final Coordinate aCoordinate;

    public Block(Coordinate pCoordinate) {
        // set the Coordinate
        aCoordinate = pCoordinate;
    };

    public boolean getShotAt() {return shotAt;}

    public boolean getHasBoat() {return hasBoat;}

    public boolean getShowDestroyed() {return showDestroyed;}

    public char getBoatType() {return boatType;}

    public Coordinate getCoordinate() {return aCoordinate;}

    public void updateShotAt() {
        /*
        A shot can only change from false to true, once true its final
         */
        if (!shotAt) {
            shotAt = true;
        }

    }

    public void updateHasBoat() {
        /*
        Having a boat can only change from false to true, once true its final
         */
        if (!hasBoat) {
            hasBoat = true;
        }

    }

    public void updateShowDestroyed() {
        /*
        Having showing destroyed can only change from false to true, once true its final
         */
        if (!showDestroyed) {
            showDestroyed = true;
        }

    }

    public void updateBoatType(char pBoatType) {
        boatType = pBoatType;
    }

    @Override
    public String toString() {
        return "Block at " + aCoordinate + " with shotAt:" + shotAt + ", hasBoat: " + hasBoat + ", boatType: " + boatType + ", showDestroyed: " + showDestroyed;
    }

}
