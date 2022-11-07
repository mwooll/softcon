public enum BoatType {

    CARRIER("CARRIER", 'C', 6),
    BATTLESHIP("BATTLESHIP", 'B', 4),
    SUBMARINE("SUBMARINE", 'S', 3),
    PATROL("PATROL", 'P', 2);

    private final String aTypeName;
    private final char aTypePrintChar;
    private final int aTypeLen;

    private BoatType(String pTypeName, char pTypePrintChar, int pTypeLen) {
        aTypeName = pTypeName;
        aTypePrintChar = pTypePrintChar;
        aTypeLen = pTypeLen;
    }

    public String getTypeName() {
        return aTypeName;
    }

    public char getTypePrintChar() {
        return aTypePrintChar;
    }

    public int getTypeLen() {
        return aTypeLen;
    }

}
