// Created by erinc at 11.02.20

package algorithm.GaleShapleyAlgorithm;

import java.util.LinkedList;

public class Man extends Person {
    private LinkedList<Woman> preferredSpouses;

    public Man(String name) {
        super(name);
        preferredSpouses = new LinkedList<>();
    }

    public Man(String name, LinkedList<Woman> preferredSpouses) {
        super(name);
        setPreferredSpouses(preferredSpouses);
    }

    public Woman getNextPreferredWoman() {
        return preferredSpouses.getFirst();
    }

    void proceedPreferenceList() {  // After the man proposed to a woman, he cannot propose to her again
        preferredSpouses.removeFirst();
    }

    public void setPreferredSpouses(LinkedList<Woman> preferredSpouses) {
        this.preferredSpouses = preferredSpouses;
    }
}
