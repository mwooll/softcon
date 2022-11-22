package die;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public enum DiceCombo implements Iterable {

    /**
     * Represents all possible Combinations the Dice can form according to the game rules
     */

    SINGLE_ONE(new ArrayList<DieValue>(List.of(
            DieValue.ONE
    ))),
    SINGLE_TWO(new ArrayList<DieValue>(List.of(
            DieValue.TWO
    ))),
    SINGLE_THREE(new ArrayList<DieValue>(List.of(
            DieValue.THREE
    ))),
    SINGLE_FOUR(new ArrayList<DieValue>(List.of(
            DieValue.FOUR
    ))),
    SINGLE_FIVE(new ArrayList<DieValue>(List.of(
            DieValue.FIVE
    ))),
    SINGLE_SIX(new ArrayList<DieValue>(List.of(
            DieValue.SIX
    ))),
    TRIPLET_ONE(new ArrayList<DieValue>(List.of(
            DieValue.ONE,DieValue.ONE,DieValue.ONE
    ))),
    TRIPLET_TWO(new ArrayList<DieValue>(List.of(
            DieValue.TWO,DieValue.TWO,DieValue.TWO
    ))),
    TRIPLET_THREE(new ArrayList<DieValue>(List.of(
            DieValue.THREE,DieValue.THREE,DieValue.THREE
    ))),
    TRIPLET_FOUR(new ArrayList<DieValue>(List.of(
            DieValue.FOUR,DieValue.FOUR,DieValue.FOUR
    ))),
    TRIPLET_FIVE(new ArrayList<DieValue>(List.of(
            DieValue.FIVE,DieValue.FIVE,DieValue.FIVE
    ))),
    TRIPLET_SIX(new ArrayList<DieValue>(List.of(
            DieValue.SIX,DieValue.SIX,DieValue.SIX
    ))),
    STRAIGHT(new ArrayList<DieValue>(List.of(
            DieValue.ONE,DieValue.TWO,DieValue.THREE,DieValue.FOUR,DieValue.FIVE,DieValue.SIX
    )));

    private final List<DieValue> aListDieValues = new ArrayList<DieValue>();

    DiceCombo(List<DieValue> pListDieValues) {
        aListDieValues.addAll(pListDieValues);
    }

    public Iterator<DieValue> iterator() {return aListDieValues.iterator();}

}
