package die;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public enum DiceCombo implements Iterable {

    /**
     * Represents all possible Combinations the Dice can form according to the game rules
     */

    SINGLE_ONE(new ArrayList<>(List.of(
            DieValue.ONE
    ))),
    SINGLE_TWO(new ArrayList<>(List.of(
            DieValue.TWO
    ))),
    SINGLE_THREE(new ArrayList<>(List.of(
            DieValue.THREE
    ))),
    SINGLE_FOUR(new ArrayList<>(List.of(
            DieValue.FOUR
    ))),
    SINGLE_FIVE(new ArrayList<>(List.of(
            DieValue.FIVE
    ))),
    SINGLE_SIX(new ArrayList<>(List.of(
            DieValue.SIX
    ))),
    TRIPLET_ONE(new ArrayList<>(List.of(
            DieValue.ONE,DieValue.ONE,DieValue.ONE
    ))),
    TRIPLET_TWO(new ArrayList<>(List.of(
            DieValue.TWO,DieValue.TWO,DieValue.TWO
    ))),
    TRIPLET_THREE(new ArrayList<>(List.of(
            DieValue.THREE,DieValue.THREE,DieValue.THREE
    ))),
    TRIPLET_FOUR(new ArrayList<>(List.of(
            DieValue.FOUR,DieValue.FOUR,DieValue.FOUR
    ))),
    TRIPLET_FIVE(new ArrayList<>(List.of(
            DieValue.FIVE,DieValue.FIVE,DieValue.FIVE
    ))),
    TRIPLET_SIX(new ArrayList<>(List.of(
            DieValue.SIX,DieValue.SIX,DieValue.SIX
    ))),
    STRAIGHT(new ArrayList<>(List.of(
            DieValue.ONE,DieValue.TWO,DieValue.THREE,DieValue.FOUR,DieValue.FIVE,DieValue.SIX
    )));

    private final List<DieValue> aListDieValues = new ArrayList<>();

    DiceCombo(List<DieValue> pListDieValues) {
        aListDieValues.addAll(pListDieValues);
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

    public Iterator<DieValue> iterator() {return aListDieValues.iterator();}

}
