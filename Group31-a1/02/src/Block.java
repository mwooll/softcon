public class Block {
    private boolean shotAt;
    private boolean hasBoat; //final?
    private char boatType; //final?
    //TODO Decide weather is sunk is known to the block (Printfunction)
    private boolean isSunk; //final?
    public Block(){
        this.shotAt = false;
    }

    //GiveStateShotAt()         Return values: True False   Input:
    public boolean giveStateShotAt() {
        return this.shotAt;
    }

    //GiveStateHasBoat()        Return Values: True False   Input:
    public boolean giveStateHasBoat() {
        return hasBoat;
    }

    //GiveBoatType()            Return Values: ['C', 'B', 'S', 'P']
    public char giveBoatType(){
        return this.boatType;
    }

    //SetBoatType   INPUT: boat type
    public void setBoatType(char boatType){
        this.boatType = boatType;
    }
}
