package initializer;

import cell.Grid;
import gui.IContinue;
import gui.IGridObserver;
import gui.IPlayerObserver;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import parser.IParser;
import player.PlayerColor;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class GUIInitializer implements Initializer, InitializerObservable, InitializerObserver {

    private final int MIN_SIZE = 3;
    private final int MAX_SIZE = 10;
    private final int N_PLAYERS = 2;

    private final List<String> aPlayers = Arrays.asList(new String[N_PLAYERS]);
    private final List<Color> aColors = Arrays.asList(new Color[N_PLAYERS]);
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

    public List<String> getPlayers() {return Collections.unmodifiableList(aPlayers);}

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
        return !(aPlayers.contains(null));
    }

    @Override
    public boolean playerColorSet() {
        HashSet<Color> tmpColorHashSet = new HashSet<>(aColors);
        return tmpColorHashSet.size() == aColors.size() && !aColors.contains(null) && !aColors.contains(Color.WHITE);
    }

    @Override
    public boolean gridSizeSet() {
        return (aGridH >= MIN_SIZE && aGridH <= MAX_SIZE) && (aGridW >= MIN_SIZE && aGridW <= MAX_SIZE);
    }

    @Override
    public void setPlayerName(String pPlayerName, int pIndex) {
        assert pIndex >= 0 && pIndex <= N_PLAYERS;

        System.out.println(String.format("Setting name Player %s to %s", pIndex+1, pPlayerName));

        aPlayers.set(pIndex, pPlayerName);
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
        assert aPlayers.contains(pPlayerName) && aPlayers.stream().filter(name -> name.equals(pPlayerName)).count() == 1;

        int tmpIndex = aPlayers.indexOf(pPlayerName);
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
    public boolean validatePlayerName(String pPlayerName) {return aParser.validatePlayerName(aPlayers, pPlayerName);}

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
        // create Grid with aHeight, aWidth
        // set a random set of cells to a non-white color
        // return Grid
        return null;
    }
}
