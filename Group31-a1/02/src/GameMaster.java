/*
todo: Implement playGame, playTurn
todo: Select starting player at random instead of always human player first
 */

public class GameMaster {
    private static PlayerHuman human;
    private static PlayerComputer computer;
    private final Grid ocean;
    private final Grid target;

    // variables for game loop
    private boolean computerLost;
    private boolean humanLost;
    private Coordinate shot;
    private boolean[] shotRecords;
    private BoatType destroyedBoat;

    public GameMaster() {
        ocean = new Grid();
        target = new Grid();
        human = new PlayerHuman(ocean);
        computer = new PlayerComputer(target);
    }

    public static void main(String[] args){
        GameMaster aGame = new GameMaster();

        computer.placeFleet();
        human.placeFleet();
        aGame.gameLoop();
    }

    private void gameLoop(){
        while(true){
            // printGrid();
            computerLost = playTurn(human, computer);
            if (computerLost) {
                System.out.println("VICTORY! The computer's fleet got destroyed!");
                break;
            }
            // printGrid();
            humanLost = playTurn(computer, human);
            if (humanLost) {
                System.out.println("DEFEAT! The human's fleet got destroyed!");
                // printGrid();
                break;
            }
        }
    }

    private boolean playTurn(Player attacker, Player defender) {
        /*
        Returns whether the defending player has lost
         */

        // for debugging, print Feedback on whose turn it is
        if (attacker.isHuman()) {
            System.out.println("Humans turn to shoot");
        } else {
            System.out.println("Computers turn to shoot");
        }

        /*
        It should be printed at what target the computer shoots.
         */
        while(true) {
            shot = attacker.callShot(); // making a shot

            // for debugging, see what Coordinate the computer calls
            if (!attacker.isHuman()) {
                System.out.println("Computer shoots at " + shot);
            }

            if (GameUtils.validCoordinate(shot)) break;// break if shot is in grid
        }
        shotRecords = defender.recordShot(shot); // {boat hit, boat destroyed}
        if (shotRecords[1]) {
            //destroyedBoat = defender. ; //
            System.out.println("The shot destroyed a _");
        }
        else if (shotRecords[0]) {
            System.out.println("The shot hit a target!");
        }
        return defender.hasLost();
    }

}
