package algorithm.GaleShapleyAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;

public class Woman extends Person {
    // Maps each man to their preference score, HashMap guarantees O(1) lookup time
    private HashMap<Man, Integer> spouseToPreferenceScore;
    private Man husband;

    public Woman(String name) {
        super(name);
        spouseToPreferenceScore = new HashMap<>();
        this.husband = null;
    }

    public Woman(String name, ArrayList<Man> preferredSpouses) {
        this(name);
        setSpouseToPreferenceScore(preferredSpouses);
    }

    boolean isFree() {
        return this.husband == null;
    }

    public Man getHusband() {
        return this.husband;
    }

    void engageWith(Man husband) {
        this.husband = husband;
    }

    boolean doesPreferToCurrentHusband(Man guy) {
        return spouseToPreferenceScore.get(guy) > spouseToPreferenceScore.get(husband);
    }

    public void setSpouseToPreferenceScore(ArrayList<Man> preferredSpouses) {
        this.spouseToPreferenceScore = new HashMap<>();
        int numberOfMen = preferredSpouses.size();

        for (int i = 0; i < numberOfMen; i++) {
            Man man = preferredSpouses.get(i);
            this.spouseToPreferenceScore.put(man, numberOfMen - i);    // Lower indices have higher preference
        }
    }
}
