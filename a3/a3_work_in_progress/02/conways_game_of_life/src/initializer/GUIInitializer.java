package initializer;

import cell.Grid;
import gui.IContinue;
import gui.IGridObserver;
import gui.IPlayerObserver;
import javafx.scene.paint.Color;
import parser.IParser;
import player.Player;
import player.PlayerColor;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class GUIInitializer implements Initializer, InitializerObservable, InitializerObserver {

    private final int MIN_SIZE = 3;
    private final int MAX_SIZE = 10;
    private final int N_PLAYERS = 2;

//    private final List<String> aPlayers = Arrays.asList(new String[N_PLAYERS]);
//    private final List<Color> aColors = Arrays.asList(new Color[N_PLAYERS]);
//    private Grid aInitialGrid;
    private final List<Player> aPlayers = Stream.generate(Player::new).limit(N_PLAYERS).collect(Collectors.toList());
    protected Grid aInitialGrid;

    private int aGridH = -1;
    private int aGridW = -1;
    private final List<IPlayerObserver> aPlayerObservers = new ArrayList<>();
    private final List<IGridObserver> aGridObservers = new ArrayList<>();
    private final List<IContinue> aContinueObservers = new ArrayList<>();

    private final IParser aParser;

    public GUIInitializer(IParser pParser) {
        aParser = pParser;
    }

    public List<Player> getPlayers() {return Collections.unmodifiableList(aPlayers);}
    private List<String> getCurrentPlayerNames() {return aPlayers.stream().map(Player::getName).toList();}
    private List<String> getCurrentColorNames() {return aPlayers.stream().map(p -> p.getColor().getColorName()).toList();}

    public Grid getGrid() {
        Grid tmpGridCopy = new Grid(aInitialGrid);
        return tmpGridCopy;
    }

    public int getMinGridSize() {return MIN_SIZE;}
    public int getMaxGridSize() {return MAX_SIZE;}

    @Override
    public void addPlayerObserver(IPlayerObserver pPlayerObserver) {
        aPlayerObservers.add(pPlayerObserver);
    }

    @Override
    public void addGridObserver(IGridObserver pGridObserver) {
        aGridObservers.add(pGridObserver);
    }

    @Override
    public void addContinueObserver(IContinue pContinueObserver) {
        aContinueObservers.add(pContinueObserver);
    }

    @Override
    public boolean playerNamesSet() {
        // check if all players are not null. No check for duplicates, that happened in the parser
        for (Player p : aPlayers) {
            if (p.getName().equals("")) {return false;}
        }
        return true;
    }

    @Override
    public boolean playerColorSet() {
        // get all Colors currently in use
        List<String> allColorNamesInUse = aPlayers.stream().map(p -> p.getColor().getColorName()).toList();

        // check for duplicates with set operation
        HashSet<String> tmpSet = new HashSet<String>(allColorNamesInUse);

        return tmpSet.size() == allColorNamesInUse.size() &&
                !allColorNamesInUse.contains(null) &&
                !allColorNamesInUse.contains("White");
    }

    @Override
    public boolean gridSizeSet() {
        return (aGridH >= MIN_SIZE && aGridH <= MAX_SIZE) && (aGridW >= MIN_SIZE && aGridW <= MAX_SIZE);
    }

    @Override
    public void setPlayerName(String pPlayerName, int pIndex) {
        assert pIndex >= 0 && pIndex <= N_PLAYERS;

        // assume the player name can be set, has been checked by the parser

        System.out.println(String.format("Setting name Player %s to %s", pIndex+1, pPlayerName));

        aPlayers.get(pIndex).setName(pPlayerName);
        for (IPlayerObserver observer : aPlayerObservers) {
            observer.nameIsSet(pPlayerName, pIndex);
        }
        for (IContinue observer : aContinueObservers) {
            observer.setVisibility();
        }

        System.out.println(aPlayers);
    }

    @Override
    public void setPlayerColor(Color pColor, String pPlayerName) {

        // assume the player color can be set, has been checked by the parser
        // Assume player with name pPlayerName exists
        List<String> allNamesInUse = getCurrentPlayerNames();
        assert allNamesInUse.contains(pPlayerName);
        assert Collections.frequency(allNamesInUse, pPlayerName) == 1;

        int tmpIndex = 
        aColors.set(tmpIndex, pColor);
        for (IPlayerObserver observer : aPlayerObservers) {
            observer.colorIsSet(pColor, pPlayerName);
        }
        for (IContinue observer : aContinueObservers) {
            observer.setVisibility();
        }

        System.out.println(String.format("Setting color Player %s to %s", pPlayerName, pColor));

        System.out.println(aColors);
    }

    @Override
    public void setGridDimension(int pValue, String pIdentifier) {
        if (pIdentifier.equals("H")) {
            aGridH = pValue;
            for (IGridObserver observer : aGridObservers) {
                observer.heightIsSet(pValue);
            }
            for (IContinue observer : aContinueObservers) {
                observer.setVisibility();
            }

            System.out.println(String.format("Set height to %s", aGridH));

        }

        if (pIdentifier.equals("W")) {
            aGridW = pValue;
            for (IGridObserver observer : aGridObservers) {
                observer.widthIsSet(pValue);
            }
            for (IContinue observer : aContinueObservers) {
                observer.setVisibility();
            }

            System.out.println(String.format("Set width to %s", aGridW));

        }
    }

    @Override
    public boolean validatePlayerName(String pPlayerName) {
        // Give the parser a list of all currently used names as well as the name the input wants to set
        List<String> allNamesInUse = getCurrentPlayerNames();
        return aParser.validatePlayerName(allNamesInUse, pPlayerName);
    }

    @Override
    public boolean validateColorName(String pColorName) {
        // Give the parser a list of all currently used names as well as the name the input wants to set
        List<String> allColorNamesInUse = aPlayers.stream().map(p -> p.getColor().getColorName()).toList();
        return aParser.validateColorName(allColorNamesInUse, pColorName);
    }

    @Override
    public boolean validateWidth(String pWidth) {return aParser.validateWidth(MAX_SIZE, MIN_SIZE, pWidth);}
    @Override
    public boolean validateHeight(String pHeight) {return aParser.validateHeight(MAX_SIZE, MIN_SIZE, pHeight);}

    @Override
    public String choosePlayerName() {
        return null;
    }

    @Override
    public PlayerColor choosePlayerColor() {
        return null;
    }

    @Override
    public ArrayList<Integer> chooseGridDimensions() {
        return null;
    }

    @Override
    public Grid createStartingConfiguration() {
        Grid tmpGrid = new Grid(aGridW,aGridH);
        tmpGrid.getCell(0,0).instantBirth(PlayerColor.BLUE);
        aInitialGrid = tmpGrid;
        return null;
    }
}
