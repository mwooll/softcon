package ruleset;


public class Default extends Ruleset {

    /**
     * Default Ruleset for testing
     * Only overrides abstract methods
     */

    public Default() {}

    @Override
    public Default clone(){return new Default();}

    @Override
    public String returnName() {return "DEFAULT";}

    @Override
    public String explainRules() {
        return "No special rules are active.";
    }


}
