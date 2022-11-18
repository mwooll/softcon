import java.util.HashMap;
import java.util.AbstractMap;

public class DeckSpec {

    /**
     * A DeckSpec is a specific setup of Cardtypes for the Deck to initialize
     * It contains CardTypes and respective Numbers
     */

    private HashMap<CardType, Integer> pDeckSpec = new HashMap<CardType, Integer>();

    /**
     * Constructor for DeckSpec. If Debug, only create small subset of Cardtypes for testing
     */
    public DeckSpec(boolean pDebug) {
        if (pDebug) {
            pDeckSpec.put(CardType.BONUS200, 1);
            pDeckSpec.put(CardType.STOP, 1);
        } else {
            pDeckSpec.put(CardType.BONUS200, 5);
            pDeckSpec.put(CardType.BONUS300, 5);
            pDeckSpec.put(CardType.BONUS400, 5);
            pDeckSpec.put(CardType.BONUS500, 5);
            pDeckSpec.put(CardType.BONUS600, 5);

            pDeckSpec.put(CardType.STOP, 10);
        }

    }

    pDeckSpec.keySet()

}
