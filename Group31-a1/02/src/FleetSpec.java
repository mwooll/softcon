public enum FleetSpec {

    CARRIER(BoatType.CARRIER, 1),
    BATTLESHIP(BoatType.BATTLESHIP, 2),
    SUBMARINE(BoatType.SUBMARINE, 3),
    PATROL(BoatType.PATROL, 4);

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
