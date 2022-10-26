/*
todo: Implement playGame, playTurn
 */

public class GameMaster {
    private final PlayerHuman human;
    private final PlayerComputer computer;
    private final Grid ocean;
    private final Grid target;

    // variables for game loop
    private boolean computerLost;
    private boolean humanLost;
    private Coordinate shot;

    public GameMaster() {
        ocean = new Grid();
        target = new Grid();
        human = new PlayerHuman(ocean);
        computer = new PlayerComputer(target);
    }

    public static void main(String args[]){
        GameMaster aGame = new GameMaster();
        aGame.gameLoop();
    }

    private void gameLoop(){
        while(true){
            computerLost = playTurn(human, computer);
            if (computerLost) break;
            humanLost = playTurn(computer, human);
            if (humanLost) break;
        }
    }

    private boolean playTurn(Player attacker, Player defender) {
        /*
        Returns whether a next turn should be played.
         */
        while(true) {
            shot = attacker.callShot(); // making a shot
            if (GameUtils.validCoordinate(shot)) break;// break if shot is in grid
        }
        defender.recordShot(shot);
        return defender.hasLost();
    }

}
