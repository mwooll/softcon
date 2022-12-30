package initializer;

import cell.Cell;
import cell.Grid;
import gui.IInitializerObserver;
import parser.IParser;
import player.Player;
import player.PlayerColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class GUIInitializer implements IInitializerObservable, IInitializerSetterObserver {

    private final int MIN_SIZE = 5;
    private final int MAX_SIZE = 50;
    private final int N_PLAYERS = 2;
    private final List<Player> aPlayers = Stream.generate(Player::new).limit(N_PLAYERS).collect(Collectors.toList());
    protected Grid aInitialGrid;
    private final int MIN_CELLS_CHOOSE = 4;
    private final int MAX_CELLS_CHOOSE = 8;
    private int aCellsChosen = 0;
    protected int aGridH = -1;
    protected int aGridW = -1;
    private final List<IInitializerObserver> aObservers = new ArrayList<>();

    private final IParser aParser;

    public GUIInitializer(IParser pParser) {
        aParser = pParser;
    }

    /**
     * Return a list of all Players
     * @return AS A LIST
     */
    public List<Player> getPlayers() {
        List<Player> tmpList = new ArrayList<>();
        aPlayers.stream().forEach(p -> tmpList.add(p));
        return tmpList;
    }
    /**
     * Return all currently used player names, as an unmodifiable list
     * @return UNMODIFIABLE List
     */
    private List<String> getCurrentPlayerNames() {return aPlayers.stream().map(Player::getName).toList();}
    private List<PlayerColor> getCurrentColors() {return aPlayers.stream().map(Player::getColor).toList();}
    private List<String> getCurrentColorNames() {return aPlayers.stream().map(p -> p.getColor().getColorName()).toList();}

    public Grid getGrid() {
        Grid tmpGridCopy = new Grid(aInitialGrid);
        return tmpGridCopy;
    }
    public int getMinGridSize() {return MIN_SIZE;}
    public int getMaxGridSize() {return MAX_SIZE;}
    public int getMinCellsChoose() {return MIN_CELLS_CHOOSE;}
    public int getMaxCellsChoose() {return MAX_CELLS_CHOOSE;}


    @Override
    public void addObserver(IInitializerObserver pObserver) {aObservers.add(pObserver);}

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
        List<String> allColorNamesInUse = getCurrentColorNames();

        // check for duplicates with set operation
        HashSet<String> tmpSet = new HashSet<>(allColorNamesInUse);

        return tmpSet.size() == allColorNamesInUse.size() &&
                !allColorNamesInUse.contains(null) &&
                !allColorNamesInUse.contains("White");
    }
    @Override
    public boolean gridSizeSet() {
        return (aGridH >= MIN_SIZE && aGridH <= MAX_SIZE) && (aGridW >= MIN_SIZE && aGridW <= MAX_SIZE);
    }
    @Override
    public boolean cellsAreSet() {
        return aCellsChosen >= MIN_CELLS_CHOOSE;
    }
    @Override
    public boolean maxCellsReached(){
        return aCellsChosen < MAX_CELLS_CHOOSE;
    }




    @Override
    public void setPlayerName(String pPlayerName, int pIndex) {
        assert pIndex >= 0 && pIndex <= N_PLAYERS;

        // assume the player name can be set, has been checked by the parser

        aPlayers.get(pIndex).setName(pPlayerName);
        for (IInitializerObserver observer : aObservers) {
            observer.nameIsSet(pPlayerName, pIndex);
            observer.setVisibility();
        }

    }
    @Override
    public void setPlayerColor(PlayerColor pPlayerColor, String pPlayerName) {

        // assume the player color can be set, has been checked by the parser
        // Assume player with name pPlayerName exists
        List<String> allNamesInUse = getCurrentPlayerNames();
        assert allNamesInUse.contains(pPlayerName);
        assert Collections.frequency(allNamesInUse, pPlayerName) == 1;

        int tmpIndex = allNamesInUse.indexOf(pPlayerName);
        aPlayers.get(tmpIndex).setColor(pPlayerColor);

        for (IInitializerObserver observer : aObservers) {
            observer.colorIsSet(pPlayerColor, pPlayerName);
            observer.setVisibility();
        }

    }
    @Override
    public void setGridDimension(int pValue, String pIdentifier) {
        if (pIdentifier.equals("H")) {
            aGridH = pValue;
            for (IInitializerObserver observer : aObservers) {
                observer.heightIsSet(pValue);
                observer.setVisibility();
            }

        }

        if (pIdentifier.equals("W")) {
            aGridW = pValue;

            for (IInitializerObserver observer : aObservers) {
                observer.widthIsSet(pValue);
                observer.setVisibility();
            }

        }
    }
    @Override
    public void chooseCell(Cell pCell) {
        // add one to the count of already chosen cells
        aCellsChosen += 1;

        // set state to black for the chosen cell
        pCell.instantBirth(PlayerColor.BLACK);

        for (IInitializerObserver observer : aObservers) {
            observer.cellIsChosen();
            observer.setVisibility();
        }
    }




    @Override
    public boolean validatePlayerName(String pPlayerName) {
        // Give the parser a list of all currently used names as well as the name the input wants to set
        List<String> allNamesInUse = getCurrentPlayerNames();
        return aParser.validatePlayerName(allNamesInUse, pPlayerName);
    }
    @Override
    public boolean validateColorName(PlayerColor pPlayerColor) {
        // Give the parser a list of all currently used names as well as the name the input wants to set
        List<PlayerColor> allColorsInUse = getCurrentColors();
        return aParser.validateColor(allColorsInUse, pPlayerColor);
    }
    @Override
    public boolean validateWidth(String pWidth) {return aParser.validateWidth(MAX_SIZE, MIN_SIZE, pWidth);}
    @Override
    public boolean validateHeight(String pHeight) {return aParser.validateHeight(MAX_SIZE, MIN_SIZE, pHeight);}


    public void createEmptyStartingGrid() {
        aInitialGrid = new Grid(aGridW, aGridH);
    }

    public void createStartingConfiguration() {
        // Create a grid double the size of the current initial grid
        Grid tmpGrid = new Grid(aGridW*2, aGridH);

        // Fetch the player colors
        List<Player> tmpPlayers = getPlayers();
        PlayerColor pc1 = tmpPlayers.get(0).getColor();
        PlayerColor pc2 = tmpPlayers.get(1).getColor();

        // For each Cell that is not white in current initial grid, create two coloured cells, one per player
        int ct = 0;
        int row = 0;
        for (Cell c : aInitialGrid.getIterator()) {

            // get col
            int col = ct%aGridW;

            if (ct > 0 && ct%aGridW == 0) {row += 1;}

            if (c.getState() != PlayerColor.WHITE) {
                // Set the two cells to the two colors
                tmpGrid.getCell(row, col).instantBirth(pc1);
                tmpGrid.getCell(row, (aGridW*2-1)-col).instantBirth(pc2);
            }

            ct++;
        }

        aInitialGrid = tmpGrid;

    }

}
