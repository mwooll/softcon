package card;

import java.util.HashMap;
import java.util.Iterator;

public class DeckSpec implements Iterable<CardType> {

    /**
     * A card.DeckSpec is a specific setup of Cardtypes for the card.Deck to initialize
     * It contains CardTypes and respective Numbers
     */

    private final HashMap<CardType, Integer> aCardList = new HashMap<CardType, Integer>();

    /**
     * Constructor creates an instance containing Cardtype and number of Cards
     * @param pDebug If true only use small subset of all Cards, otherwise use the full game specs
     */
    public DeckSpec(boolean pDebug) {

        if (pDebug) {
            aCardList.put(CardType.STOP, 1);
        } else {
            aCardList.put(CardType.BONUS200, 5);
            aCardList.put(CardType.BONUS300, 5);
            aCardList.put(CardType.BONUS400, 5);
            aCardList.put(CardType.BONUS500, 5);
            aCardList.put(CardType.BONUS600, 5);

            aCardList.put(CardType.STOP, 10);
        }

    }

    public Integer getCount(CardType pCardType) {return aCardList.get(pCardType);}

    public Iterator<CardType> iterator() {
        return aCardList.keySet().iterator();
    }


}
