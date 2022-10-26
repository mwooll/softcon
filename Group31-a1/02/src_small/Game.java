public class Game {

    private Player player;
    private Player computer;
    // private Grid ocean;
    // private Grid target;

    public static void main(String[] args) {
        Game game = new Game();
        game.game_loop();
    }

    private Game() {
        player = new Player();
        computer = new Player();
        // ocean = new Grid(); // give grid size as parameter?
        // target = new Grid();
    }

    private void game_loop() {
        while (true) {
            play_turn(player, computer);
            play_turn(computer, player);
            break;
        }
    }

    private void play_turn(Player cur_player, Player opponent) {
        while (true) {
            while (true) {
                String shot = cur_player.call_shot();
//                if (!Grid.coord_in_grid(shot)) {
//                    System.println("Your shot needs to be inside the grid!");
//                }
//                else break;
                break;
            }
            if (opponent.receive_shot(shot)) break;
        }
        // a valid shot was entered, so we can proceed
        opponent.record_shot(shot);
    }

    private boolean is_straight_line(String start, String end) {
        return (start[0] == end[0] || start[1] == end[1]);
    }

    //private static void print_grid() {}

}