public class Block {

    private Coordinate aCoordinate;
    private boolean aStateShot, aStateBoat, aStateReveal;
    private String aPrintChar = "";

    public Block(Coordinate pCoordinate) {

        aCoordinate = pCoordinate;

        // init with no Shot
        aStateShot = false;

        // init with no Boat
        aStateBoat = false;

        // init without revealing
        aStateReveal = false;

    };

    public Coordinate getCoordinate() {
        return aCoordinate;
    };

    public boolean getStateShot() {return aStateShot;}

    public boolean getStateBoat() {return aStateBoat;}

    public boolean getStateReveal() {return aStateReveal;}

    public void updateStateShot() {
        /*
        A shot can only change from false to true, once true its final
         */
        if (!aStateShot) {
            aStateShot = !aStateShot;
        }

    }

    public void updateStateBoat() {
        /*
        A boat can only change from false to true, once true its final
         */
        if (!aStateBoat) {
            aStateBoat = !aStateBoat;
        }
    }

    public void updateStateReveal() {
        /*
        A reveal can only change from false to true, once true its final
         */
        if (!aStateReveal) {
            aStateReveal = !aStateReveal;
        }
    }

    public String returnPrintCharacter() {return aPrintChar;}

    public void updatePrintCharacter(String pPrintChar) {aPrintChar = pPrintChar;}

    public void printBlockStatus() {
        String tmpString = toString();
        tmpString += "\nStatus Shot: " + getStateShot();
        tmpString += "\nStatus Boat: " + getStateBoat();
        tmpString += "\nStatus Reveal: " + getStateReveal();
        System.out.println(tmpString);
    }

    @Override
    public String toString() {
        return "Block with Coordinates (" + aCoordinate.getRow() + "," + aCoordinate.getCol() + ")";
    }

}
