package ruleset;

public abstract class Ruleset {

    /**
     * A class subtyping the ruleset.Ruleset needs to
     * know how to handle game rules
     */

    public Ruleset(){};

    /**
     * Return the name of the ruleset.Ruleset
     */
    public String returnName() {return "PLACEHOLDER";}

    /**
     * Explain the rules which make up the ruleset.Ruleset
     * @return The rules as a printable string
     */
    public String explainRules() {
        return "PLACEHOLDER";
    }

    /**
     * Given a list of dice, determine all valid combinations
     * given the ruleset.Ruleset
     *
     * @pre Valid list with dice
     * @return A list containing all dice combos that are valid, empty if none found
     */
//    abstract ArrayList<DiceCombo> determineValid(ArrayList<Die.Die>);

    /**
     * Given a list of dice combos, sum up the points according to the rules
     * Accepts also empty lists
     *
     * @return Integer value of points the input generated
     */
//    abstract int sumUpPoints(ArrayList<DiceCombo>);

    /**
     * Specify the amounts of points to add when a Tutto happens
     */
    public int handleTutto(){
        return 0;
    };

}
