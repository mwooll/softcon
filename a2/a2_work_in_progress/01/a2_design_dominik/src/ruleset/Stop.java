package ruleset;

public class Stop extends Ruleset {

    @Override
    public String returnName() {return "STOP";}

    @Override
    public String explainRules() {
        return "The STOP card.Card stops the turn when drawn";
    }

}
