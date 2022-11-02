public enum FleetSpec {

    CARRIER(BoatType.CARRIER, 0),
    BATTLESHIP(BoatType.BATTLESHIP, 0),
    SUBMARINE(BoatType.SUBMARINE, 1),
    PATROL(BoatType.PATROL, 1);

    private final BoatType aType;
    private final int aTypeCount;

    private FleetSpec(BoatType pType, int pTypeCount) {
        aType = pType;
        aTypeCount = pTypeCount;
    }

    public BoatType getType() {
        return aType;
    }

    public int getTypeCount() {
        return aTypeCount;
    }

}
