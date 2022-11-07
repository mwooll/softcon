import java.util.Random;

public class GameMaster {
    private final PlayerHuman aHuman;
    private final PlayerComputer aComputer;

    private int aCountTurns = 1;

    private Player attacker;
    private Player defender;

    public GameMaster() {
        Grid humanGrid = new Grid();
        Grid computerGrid = new Grid();
        aHuman = new PlayerHuman(humanGrid);
        aComputer = new PlayerComputer(computerGrid);
    }

    public static void main(String[] args){
        GameMaster aGame = new GameMaster();

        // After starting the game, print the empty grids
        System.out.println("Starting the Game");
        aGame.aComputer.aGrid.printTarget();
        aGame.aHuman.aGrid.printOcean();

        aGame.aComputer.placeFleet();
        aGame.aHuman.placeFleet();

        // After placement, print the grids again
        aGame.aComputer.aGrid.printTarget();
        aGame.aHuman.aGrid.printOcean();

        aGame.gameLoop();
    }

    private void gameLoop(){

        boolean defenderLost;

        // Determine who starts at random
        int coinFlip;
        Random randNum = new Random();
        coinFlip = randNum.nextInt(2);

        // Human start
        if (coinFlip == 0) {
            System.out.println("Human Player starts");
            attacker = aHuman;
            defender = aComputer;
        }
        // Computer start
        else {
            System.out.println("Computer Player starts");
            attacker = aComputer;
            defender = aHuman;
        }

        while (true) {

            // Print which turn is played
            System.out.print(String.format("\n\n===== TURN %d =====\n", aCountTurns));

            defenderLost = playTurn(attacker, defender);

            // at the end of each turn, print the grids for the human
            aComputer.aGrid.printTarget();
            aHuman.aGrid.printOcean();

            if (defenderLost) {

                // if the defender lost, the game is over
                // determine if the human or computer lost
                String defenderPlayerType;
                if (defender.isHuman()) {
                    defenderPlayerType = "Human";
                } else {
                    defenderPlayerType = "Computer";
                }
                System.out.println("GAME OVER! " + defenderPlayerType + " Player lost!");

                // if the human lost, show him the remaining ships
                if (defender.isHuman()) {
                    System.out.println("Showing the remaining boats of the opponent");
                    attacker.aGrid.printTargetRemaining();
                }

                break;

            } else {
                // swap the roles of attacker and defender1
                Player tmp = attacker;
                attacker = defender;
                defender = tmp;
            }

            aCountTurns++;
        }
    }

    private boolean playTurn(Player attacker, Player defender) {
        /**
         * Play a turn where the attacker could be either human or computer
         *
         * @return If the defending player has lost
         */

        Coordinate shot;
        boolean[] shotRecords;

        // for debugging, print Feedback on whose turn it is
        if (attacker.isHuman()) {
            System.out.println("Humans turn to shoot");
        } else {
            System.out.println("Computers turn to shoot");
        }

        // callShot ensures validity of the shot, is not checked again
        shot = attacker.callShot();

        // debugging
        // print what Coordinate the computer calls
        if (!attacker.isHuman()) {
            System.out.println("Computer shoots at " + shot.printPretty());
        }

        shotRecords = defender.recordShot(shot); // {any boat hit, was boat destroyed}
        if (shotRecords[1]) {
            // If the shot destroyed a boat, ask the player for the type of the boat
            String boatTypeDestroyed = defender.getBoatTypeString(shot);
            System.out.println("The shot destroyed a " + boatTypeDestroyed);
        }
        else if (shotRecords[0]) {
            System.out.println("The shot hit a target!");
        }
        return defender.hasLost();
    }

}
