import java.util.Objects;

public class Coordinate implements Comparable<Coordinate> {

    /*
     Coordinate does not check if aRow und aCol are valid i.e. smaller than Game.GAMESIZE
     todo: Constructor could in theory check if a Coordiante is valid.
     Drawback: Wrongly entered Coordiante throws error, this requires a real error handling...
     */

    // todo: remove aRankGridOrder


    private final int aRow, aCol, aRankGridOrder;

    public Coordinate(int pRow, int pCol) {

        aRow = pRow;
        aCol = pCol;
        aRankGridOrder = GameUtils.gridOrderTopLeftToRightBottom(aRow, aCol);

    }

    public int getRow() {return aRow;}
    public int getCol() {return aCol;}

    @Override
    public String toString() {
        return "(" + aRow + "," + aCol + ")";
    }

    // the order is defined in the Game Class
    public int compareTo(Coordinate pCoordinate) {
        return Integer.compare(aRankGridOrder, pCoordinate.aRankGridOrder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return aRankGridOrder == that.aRankGridOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(aRankGridOrder);
    }

}
