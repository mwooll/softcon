public class Grid {
    //Parameters of Grid, Should width and height be final or is private enough?
    private Block[][] Coordinate;
    private int height;
    private int width;

    //Constructor with default height, width = 10,10
    public Grid(){
        this(10,10);
    }
    //Constructor   Input: #height, #width      Output: Grid of given size composed of Blocks
    public Grid( int height, int width) {
        this.height = height;
        this.width = width;
        this.Coordinate = new Block[height][width];
        for (int i = 0; i<height; i++){
            for (int j = 0; j<width; j++){
                this.Coordinate[i][j] = new Block();
            }
        }
    }
    //CoordInGrid()     USES:RowType, ColType   INPUT: coordinates  OUTPUT: true if coordinates in grid
    public boolean coordInGrid(int row, int column){
        return 0 < row && row < this.height && 0 < column && column < this.width;
    }
    //IsShot()          USES:Block
    public boolean isShot(int row, int column){
        return this.Coordinate[row][column].giveStateShotAt();
    }
    //HasBoat()         USES:Block
    public boolean hasBoat(int row, int column){
        return this.Coordinate[row][column].giveStateHasBoat();
    }
    //#### ADDED ###### get boat type
    public char getBoatType(int row, int column){
        return this.Coordinate[row][column].giveBoatType();
    }
    //#### ADDED ##### set boat type
    public void setBoatType(int row, int column, char boatType){
        this.Coordinate[row][column].setBoatType(boatType);
    }
    }




