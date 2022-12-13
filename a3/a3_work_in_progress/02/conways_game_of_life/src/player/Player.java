package player;

public class Player {
    private final String aName;
    private final PlayerColor aColor;

    public Player(String pName, PlayerColor pColor) {
        aName = pName;
        aColor = pColor;
    }

    public String getName() { return aName; }

    public PlayerColor getColor() { return aColor; }
}
