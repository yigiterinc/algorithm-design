package algorithm.GaleShapleyAlgorithm;

import java.util.ArrayList;

public class Woman extends Person {
    private ArrayList<Man> preferredSpouses;
    private Man husband;

    public Woman(String name) {
        super(name);
        preferredSpouses = new ArrayList<>();
        this.husband = null;
    }

    public Woman(String name, ArrayList<Man> preferredSpouses) {
        super(name);
        setPreferredSpouses(preferredSpouses);
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
        for (Man man : preferredSpouses) {
            if (man.equals(husband)) {
                return false;
            } else if (man.equals(guy)) {
                return true;
            }
        }

        return false;
    }

    public void setPreferredSpouses(ArrayList<Man> preferredSpouses) {
        this.preferredSpouses = preferredSpouses;
    }
}
