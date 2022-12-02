package ruleset;

public class X2 extends Ruleset {

    @Override
    public X2 clone(){return new X2();}

    @Override
    public String returnName() {return "X2";}

    @Override
    public String explainRules() {
        return "The X2 card doubles your current rolls points if you roll a Tutto.";
    };

    @Override
    public int handleTutto(int pPoints) {return pPoints;}
}
