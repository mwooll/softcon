package die;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public enum DiceCombo implements Iterable<DieValue> {

    /**
     * Represents all possible Combinations the Dice can form according to the game rules
     * Since the whole game has the same association of Combo and Points, the respective points are hardcoded here
     * The order does matter! For Fireworks, always the most top DiceCombo gets chosen
     */

    TRIPLET_ONE(new ArrayList<>(List.of(DieValue.ONE,DieValue.ONE,DieValue.ONE)), 1000),
    TRIPLET_SIX(new ArrayList<>(List.of(DieValue.SIX,DieValue.SIX,DieValue.SIX)),600),
    TRIPLET_FIVE(new ArrayList<>(List.of(DieValue.FIVE,DieValue.FIVE,DieValue.FIVE)), 500),
    TRIPLET_FOUR(new ArrayList<>(List.of(DieValue.FOUR,DieValue.FOUR,DieValue.FOUR)), 400),
    TRIPLET_THREE(new ArrayList<>(List.of(DieValue.THREE,DieValue.THREE,DieValue.THREE)), 300),
    TRIPLET_TWO(new ArrayList<>(List.of(DieValue.TWO,DieValue.TWO,DieValue.TWO)), 200),
    SINGLE_ONE(new ArrayList<>(List.of(DieValue.ONE)), 100),
    SINGLE_FIVE(new ArrayList<>(List.of(DieValue.FIVE)), 50),
    SINGLE_TWO(new ArrayList<>(List.of(DieValue.TWO)), 0),
    SINGLE_THREE(new ArrayList<>(List.of(DieValue.THREE)), 0),
    SINGLE_FOUR(new ArrayList<>(List.of(DieValue.FOUR)), 0),
    SINGLE_SIX(new ArrayList<>(List.of(DieValue.SIX)), 0);

    private final List<DieValue> aListDieValues = new ArrayList<>();
    private int aPoints = 0;

    DiceCombo(List<DieValue> pListDieValues, int pPoints) {
        aListDieValues.addAll(pListDieValues);
        aPoints = pPoints;
    }

    /**
     * Return a HashMap with DieValue,count specifying how much of which DieValue the combination needs
     * @return
     */
    public HashMap<DieValue, Integer> returnCounts() {

        HashMap<DieValue, Integer> counts = new HashMap<>();

        for (DieValue dievalue : DieValue.values()) {
            counts.put(dievalue, 0);
        }

        for (DieValue dievalue : aListDieValues) {
            counts.put(dievalue, counts.get(dievalue) + 1);
        }

        return counts;

    }

    public int returnPoints() {return aPoints;}

    public Iterator<DieValue> iterator() {return aListDieValues.iterator();}

    /**
     * Return a triplet corresponding to the given DieValue.
     *
     * @return DiceCombo
     */
    public static DiceCombo returnTriplet(DieValue dievalue) {
        DiceCombo triplet;
        switch (dievalue) {
            case ONE:
                triplet = TRIPLET_ONE;
                break;
            case TWO:
                triplet = TRIPLET_TWO;
                break;
            case THREE:
                triplet = TRIPLET_THREE;
                break;
            case FOUR:
                triplet = TRIPLET_FOUR;
                break;
            case FIVE:
                triplet = TRIPLET_FIVE;
                break;
            case SIX:
                triplet = TRIPLET_SIX;
                break;
            default:
                triplet = TRIPLET_ONE; //to make it compilable
        }
        return triplet;
    }
}
