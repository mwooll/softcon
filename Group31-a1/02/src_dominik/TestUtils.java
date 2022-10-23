import java.util.List;
import java.util.HashMap;
import java.util.Arrays;

public class TestUtils {

    public static HashMap<String, List<Coordinate>> generatePlacement4by4() {
        HashMap<String, List<Coordinate>> aHashMapOut = new HashMap<>();
        aHashMapOut.put("SUBMARINE1", Arrays.asList(
                new Coordinate(0,1),
                new Coordinate(0,2),
                new Coordinate(0,3)
        ));
        aHashMapOut.put("PATROL1", Arrays.asList(
                new Coordinate(2,1),
                new Coordinate(3,1)
        ));

        return aHashMapOut;
    }

}
