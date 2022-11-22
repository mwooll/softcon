package ruleset;

public class Stop extends Ruleset {

    public String returnName() {return "STOP";}

    public String explainRules() {
        return "The STOP card.Card stops the turn when drawn";
    }

}
