package ruleset;

public class X2 extends Ruleset {

    @Override
    public String returnName() {return "X2";}

    @Override
    public String explainRules() {
        return "The X2 card doubles your current turns points if you roll a tutto.";
    };

    @Override
    public int handleTutto(int pPoints) {return pPoints*2;}
}