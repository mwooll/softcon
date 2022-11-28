package ruleset;

public class Fireworks extends Ruleset {

    @Override
    public Fireworks clone(){return new Fireworks();}

    @Override
    public String returnName() {return "FIREWORKS";}

    @Override
    public String explainRules() {
        return "You have to remove all valid combinations and you keep rolling until you roll a null." +
                "If you achieve a Tutto you leave the card and keep rolling. The Tutto does not score you any points, " +
                "but you keep all the points you rolled.";
    }

    @Override
    public int handleNull(int pPoints) {
        return pPoints;
    }

}
