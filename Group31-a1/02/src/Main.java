import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // System.out.println("Hello world!");




//        // Test Game convertLetter and validCoordinate
//        int myRow = 0;
//        char myCol = 'J';
//        int myColConverted = GameUtils.convertLetterToInt(myCol);
//        Coordinate myCoordinate = new Coordinate(myRow, myColConverted);
//        System.out.println(myCoordinate);
//        System.out.println("Coordinate " + myCoordinate + " is " + GameUtils.validCoordinate(myCoordinate));
//        System.out.println("Convert back");
//        System.out.println(GameUtils.convertIntToLetter(myColConverted));







//        // Test Game validUserInputPattern convertUserInputPlacementToCoorindates
//        String myInput = "A1,A1";
//        System.out.println(GameUtils.validUserInputPlacement(myInput));
//        List<Coordinate> myCoordinates = GameUtils.convertUserInputPlacementToCoorindates(myInput);
//        for (Coordinate c : myCoordinates) {
//            System.out.println(c);
//        }
//        // Test Game isStraigthLine
//        System.out.println(GameUtils.isStraigthLine(myCoordinates));
//        // Test Game generateCoordinatesFromSTartEnd
//        // Test what happens if i put in twice the same coordiantes
//        List<Coordinate> myListOfCoordinates = GameUtils.generateCoordinatesFromStartEnd(myCoordinates);
//        System.out.println(myListOfCoordinates);








//        // Test Coordinate, equals and hashCode
//        Coordinate cor1 = new Coordinate(0,0);
//        Coordinate cor2 = new Coordinate(0,0);
//        int myInt = 0;
//        System.out.println(cor1 == cor2);
//        System.out.println(cor1.equals(cor2));
//        System.out.println(cor1.equals(myInt));







//        // Test Block
//        Block myBlock = new Block(new Coordinate(0,0));
//        System.out.println(myBlock);
//        System.out.println(myBlock.getStateBoat());
//        System.out.println(myBlock.getStateShot());
//        System.out.println(myBlock.returnPrintCharacter());
//        myBlock.updatePrintCharacter("PRINT");
//        System.out.println(myBlock.returnPrintCharacter());
//        myBlock.updateStateBoat();
//        System.out.println(myBlock.getStateBoat());
//        myBlock.updateStateShot();
//        System.out.println(myBlock.getStateShot());
//        myBlock.updateStateBoat();
//        System.out.println(myBlock.getStateBoat());
//        myBlock.updateStateShot();
//        System.out.println(myBlock.getStateShot());





//        // Test Boat
//        Boat myBoat = new Boat(BoatType.PATROL);
//        List<Coordinate> myBoatCoordinates = new ArrayList<>();
//        myBoatCoordinates.add(new Coordinate(0,0));
//        myBoatCoordinates.add(new Coordinate(1,0));
//
//        // boat is not yet placed
//        System.out.println(myBoat);
//        System.out.println(myBoat.getCoordinates());
//        System.out.println(myBoat.getCoordinatesLeft());
//        System.out.println("Boat Placed: " + myBoat.isPlaced());
//        System.out.println("Boat Destroyed: " + myBoat.isDestroyed());
//
//        // place with the right amount of positions
//        myBoat.setCoordinates(myBoatCoordinates);
//        System.out.println(myBoat);
//        System.out.println(myBoat.getCoordinates());
//        System.out.println(myBoat.getCoordinatesLeft());
//        System.out.println("Boat Placed: " + myBoat.isPlaced());
//        System.out.println("Boat Destroyed: " + myBoat.isDestroyed());
//
//        // Add a third coordinate and try to place again
////        myBoat.setCoordinates(myBoatCoordinates);
////        System.out.println(myBoat);
////        System.out.println(myBoat.getCoordinates());
////        System.out.println(myBoat.getCoordinatesLeft());
//
//        // record a hit
//        Coordinate myHit = new Coordinate(0,0);
//        myBoat.recordHit(myHit);
//        System.out.println(myBoat);
//        System.out.println(myBoat.getCoordinates());
//        System.out.println(myBoat.getCoordinatesLeft());
//        System.out.println("Boat Placed: " + myBoat.isPlaced());
//        System.out.println("Boat Destroyed: " + myBoat.isDestroyed());
//
//        // record a hit that is not in boat
////        Coordinate mySecondHit = new Coordinate(9,9);
////        myBoat.recordHit(mySecondHit);
////        System.out.println(myBoat);
////        System.out.println(myBoat.getCoordinates());
////        System.out.println(myBoat.getCoordinatesLeft());
////        System.out.println("Boat Destroyed: " + myBoat.isDestroyed());
//
//        // record a second valid hit
//        Coordinate mySecondHit = new Coordinate(1,0);
//        myBoat.recordHit(mySecondHit);
//        System.out.println(myBoat);
//        System.out.println(myBoat.getCoordinates());
//        System.out.println(myBoat.getCoordinatesLeft());
//        System.out.println("Boat Placed: " + myBoat.isPlaced());
//        System.out.println("Boat Destroyed: " + myBoat.isDestroyed());




//        // Test Fleet
//        // init empty Fleet
//        Fleet myFleet = new Fleet();
//        for (Boat b : myFleet) {b.printFullBoat();}





//        // Test Player placing Fleet
//        // B0,D0
//        // B2,B3
//        PlayerHuman myPlayer = new PlayerHuman();
//        myPlayer.placeFleet();
//        Fleet myFleet = myPlayer.aFleet;
//        myFleet.printFleetStatus();
//
//        // Test callShot
//        Coordinate shot1 = myPlayer.callShot();
//        Coordinate shot2 = myPlayer.callShot();
//        Coordinate shot3 = myPlayer.callShot();
//        Coordinate shot4 = myPlayer.callShot();
//        Coordinate shot5 = myPlayer.callShot();
//
//        // Test recordShot
//        myPlayer.recordShot(shot1);
//        myPlayer.recordShot(shot2);
//        myPlayer.recordShot(shot3);
//        myPlayer.recordShot(shot4);
//        myPlayer.recordShot(shot5);
//        myFleet.printFleetStatus();




//        // Test Player Computer
//        PlayerComputer myPlayer = new PlayerComputer();
//        System.out.println("\n\nGrid Status after initialization");
//        myPlayer.aGrid.printAllStatus();
//        myPlayer.placeFleetFromList(TestUtils.generatePlacement4by4());
//        System.out.println("\n\nPlace Boat at B0,D0 and B2,B3");
//        // Test callShot
//        Coordinate shot1 = myPlayer.callShot();
//        System.out.println("Random shot 1 at " + shot1);
//        Coordinate shot2 = myPlayer.callShot();
//        System.out.println("Random shot 1 at " + shot2);
//        Coordinate shot3 = myPlayer.callShot();
//        System.out.println("Random shot 1 at " + shot3);
//        myPlayer.recordShot(shot1);
//        myPlayer.recordShot(shot2);
//        myPlayer.recordShot(shot3);
//        System.out.println("\nGrid Status after a few shots");
//        myPlayer.aGrid.updateGrid(myPlayer.aFleet, myPlayer.aReceivedShots);
//        myPlayer.aGrid.printAllStatus();



//        // Test Grid printing
//        Grid computerGrid = new Grid();
//        Grid humanGrid = new Grid();
//        PlayerComputer computerPlayer = new PlayerComputer(computerGrid);
//        PlayerHuman humanPlayer = new PlayerHuman(humanGrid);
//
//        computerPlayer.placeFleetFromList(TestUtils.generatePlacement4by4());
//        humanPlayer.placeFleetFromList(TestUtils.generatePlacement4by4());
//
//        // some shots on the computers grid
//        computerPlayer.recordShot(new Coordinate(0,0));
//        computerPlayer.recordShot(new Coordinate(0,1));
//        computerPlayer.recordShot(new Coordinate(0,2));
//        computerPlayer.recordShot(new Coordinate(0,3));
//        computerPlayer.recordShot(new Coordinate(2,1));
//        computerPlayer.recordShot(new Coordinate(3,1));
//        computerPlayer.recordShot(new Coordinate(5,5));
//
//        // some shots on the players grid
//        humanPlayer.recordShot(new Coordinate(0,0));
//        humanPlayer.recordShot(new Coordinate(0,1));
//
//        // showing grids
//        computerPlayer.aGrid.printTarget();
//        humanPlayer.aGrid.printOcean();



    }
}
