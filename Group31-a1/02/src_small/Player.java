import java.util.Scanner;

public class Player {

    //private Fleet fleet;
    private Scanner scanner;
    //private Grid ocean;

    //only included right now for testing methods
    public static void main(String[] args) {
        Player player = new Player();
        String shot = player.call_shot();
        System.out.println(shot);
    }

    public Player() { // need to pass grid as argument
//        fleet = new Fleet();
//        ocean = grid;
        scanner = new Scanner(System.in);
    }

    public void place_fleet() {
        for ()
    }

    public String call_shot() {
        System.out.println("Call your shot: ");
        String shot = scanner.nextLine();
        scanner.close();
        return shot;
    }
    // returns a boolean which states if the shot is valid
    public boolean receive_shot(String shot) {
        // need to know the state of the block the shot is targeted at
        // shot is invalid if the block was already shot at
//        if (ocean.is_shot(shot)) return true;
//        else
        return false;
    }

    public void record_shot(String shot) {
        ocean.
    }
}