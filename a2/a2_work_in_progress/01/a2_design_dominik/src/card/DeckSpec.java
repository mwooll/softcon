package card;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class DeckSpec implements Iterable<CardType> {

    /**
     * A card.DeckSpec is a specific setup of Cardtypes for the card.Deck to initialize
     * It contains CardTypes and respective Numbers
     *
     * todo: Create method allowing to create custom DeckSpecs for testing
     */

    private final HashMap<CardType, Integer> aCardList = new HashMap<CardType, Integer>();

    /**
     * Constructor creates an instance containing Cardtype and number of Cards
     */
    private DeckSpec(DeckSpecBuilder builder) {
        for (CardType ct : CardType.values()) {
            aCardList.put(ct, builder.builderCardList.get(ct));
        }
    }

    /**
     * Builder for a DeckSpec
     */
    public static class DeckSpecBuilder {

        private final HashMap<CardType, Integer> builderCardList = new HashMap<CardType, Integer>();

        public DeckSpecBuilder() {
            for (CardType ct : CardType.values()) {
                builderCardList.put(ct,0);
            }
        }

        public DeckSpecBuilder setBonus(int pBONUS200, int pBONUS300, int pBONUS400, int pBONUS500, int pBONUS600) {
            builderCardList.put(CardType.BONUS200, pBONUS200);
            builderCardList.put(CardType.BONUS300, pBONUS300);
            builderCardList.put(CardType.BONUS400, pBONUS400);
            builderCardList.put(CardType.BONUS500, pBONUS500);
            builderCardList.put(CardType.BONUS600, pBONUS600);
            return this;
        }

        public DeckSpecBuilder setStop(int pSTOP) {
            builderCardList.put(CardType.STOP, pSTOP);
            return this;
        }

        public DeckSpecBuilder setCloverleaf(int pCLOVERLEAF) {
            builderCardList.put(CardType.CLOVERLEAF, pCLOVERLEAF);
            return this;
        }

        public DeckSpecBuilder setFireworks(int pFIREWORKS) {
            builderCardList.put(CardType.FIREWORKS, pFIREWORKS);
            return this;
        }

        public DeckSpecBuilder setPlusminus(int pPLUSMINUS) {
            builderCardList.put(CardType.PLUSMINUS, pPLUSMINUS);
            return this;
        }

        public DeckSpecBuilder setStraight(int pSTRAIGHT) {
            builderCardList.put(CardType.STRAIGHT, pSTRAIGHT);
            return this;
        }

        public DeckSpecBuilder setX2(int pX2) {
            builderCardList.put(CardType.X2, pX2);
            return this;
        }

        /**
         * Build the default game Deck
         */
        public DeckSpecBuilder setDefault() {
            setBonus(5,5,5,5,5);
            setStop(10);
            setCloverleaf(1);
            setFireworks(5);
            setStraight(5);
            setPlusminus(5);
            setX2(5);
            return this;
        }

        public DeckSpec build() {
            return new DeckSpec(this);
        }
    }

    public Integer getCount(CardType pCardType) {return aCardList.get(pCardType);}

    public Iterator<CardType> iterator() {
        return aCardList.keySet().iterator();
    }


}
