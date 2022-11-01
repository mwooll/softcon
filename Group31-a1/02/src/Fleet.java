import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Fleet implements Iterable<Boat> {

    private final List<Boat> aBoats = new ArrayList<>();

    // aCoordinatesUsed ONLY used for placement, not for shot recording!
    private final List<Coordinate> aCoordinatesUsed = new ArrayList<>();

    public Fleet() {
        for (FleetSpec fs : FleetSpec.values()) {
            for (int i = 0; i < fs.getTypeCount(); i++) {
                String tmpInstanceName = fs.getType().getTypeName() + (i+1);
                aBoats.add(new Boat(fs.getType(), tmpInstanceName));
            }
        }
    }

    public int nBoats() {
        return aBoats.size();
    }

    public boolean isDestroyed() {
        boolean fleetDestroyed = true;
        for (Boat b : aBoats) {
            if (!b.isDestroyed()) {
                fleetDestroyed = false;
            }
        }
        return fleetDestroyed;
    }

    public int nBoatsDestroyed() {
        int ct = 0;
        for (Boat b : aBoats) {
            if (b.isDestroyed()) {
                ct++;
            }
        }
        return ct;
    }

    // todo: Is this neccessary? Does it break encapsulation?
    public List<Boat> getBoatsDestroyed() {
        List<Boat> boatsDestroyed = new ArrayList<>();
        for (Boat b : aBoats) {
            if (b.isDestroyed()) {
                boatsDestroyed.add(b);
            }
        }
        return boatsDestroyed;
    }



    /**
     * Receive a Coordinate and check if a boat is hit.
     * @param pCoordinate The Coordinate to check.
     * @return [isHit, gotDestroyed] True if pCoordinate hit a boat, True if that hit destroyed to boat
     */
    public boolean[] checkShot(Coordinate pCoordinate) {

        // for each Boat, check if its hit
        for (Boat b : aBoats) {
            // recordHit will remove the pCoordinate from that boats aCoordinatesLeft
            if (b.recordHit(pCoordinate)) {
                // if the hit destroyed the boat, return [true, true], otherwise [true, false]
                if (b.isDestroyed()) {
                    return new boolean[] {true, true};
                } else {
                    return new boolean[] {true, false};
                }
            }
        }

        // if no boat is hit, return [false, false]
        return new boolean[] {false, false};

    }



    // todo: Where should checks for already placed boats be? I think here in the fleet.
    public void placeBoat(Boat pBoat, List<Coordinate> pListCoordinates) {

        // check inputs
        assert !pBoat.isPlaced() : "Boat is already placed";

        // Set the Coordinates on the boat
        pBoat.setCoordinates(pListCoordinates);

        // Add the Coordinates to the list with the already used coordinates
        aCoordinatesUsed.addAll(pListCoordinates);

    }

    // returns true if no overlap occurs, false otherwise
    public boolean validateOverlap(List<Coordinate> pListCoordinates) {
        /*
        Returns true if there is no overlap between pListCoordinates and the boats of the fleet
         */
        for (Coordinate c : pListCoordinates) {
            if (aCoordinatesUsed.contains(c)) {
                return false;
            }
        }
        return true;
    }



    public void printFleetStatus() {

        System.out.println("Fleet destroyed: " + isDestroyed());

        for (Boat b : aBoats) {
            b.printFullBoat();
        }

    }


    public Iterator<Boat> iterator() {
        return aBoats.iterator();
    }
}
