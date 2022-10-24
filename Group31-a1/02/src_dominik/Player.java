import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public interface Player {

    /*
     todo: I think an abstract class instead of interface would make more sense here
      Many functions like hasLost, placeFleetFromList are identical
     */

    List<Coordinate> aTakenShots = new ArrayList<>();

    void placeFleet();

    void placeFleetFromList(HashMap<String, List<Coordinate>> pPlacement);

    Coordinate callShot();

    void recordShot(Coordinate pCoordinate);

    boolean hasLost();


}
