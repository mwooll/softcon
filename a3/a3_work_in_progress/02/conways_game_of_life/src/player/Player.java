package player;

import java.util.Comparator;

public class Player {
    private String aName;
    private PlayerColor aColor;

    /**
     * @param pColor must not be PlayerColor.WHITE
     */
    public Player(String pName, PlayerColor pColor) {
        aName = pName;
        aColor = pColor;
    }

    /**
     * Initialize without arguments, creating a Player with the String "" as name and color WHITE
     */
    public Player() {
        aName = "";
        aColor = PlayerColor.WHITE;
    }

    public static Comparator<Player> nameComparator() {
        return Comparator.comparing(Player::getName);
    }

    public void setName(String pName) {aName = pName;}
    public void setColor(PlayerColor pColor) {aColor = pColor;}

    public String getName() { return aName; }

    public PlayerColor getColor() { return aColor; }
}
