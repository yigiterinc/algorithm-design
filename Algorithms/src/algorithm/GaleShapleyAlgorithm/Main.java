// Created by erinc at 11.02.20

package algorithm.GaleShapleyAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        Man yigit = new Man("Yigit");
        Man ahmet = new Man("Ahmet");

        Woman gulbike = new Woman("Gulbike",
                        new ArrayList<>(Arrays.asList(yigit, ahmet)));

        Woman ece = new Woman("Ece",
                    new ArrayList<>(Arrays.asList(yigit, ahmet)));

        yigit.setPreferredSpouses(new LinkedList<>(Arrays.asList(gulbike, ece)));
        ahmet.setPreferredSpouses(new LinkedList<>(Arrays.asList(gulbike, ece)));

        ArrayList<Man> men = new ArrayList<>();
        men.add(yigit);
        men.add(ahmet);

        ArrayList<Woman> women = new ArrayList<>();
        women.add(gulbike);
        women.add(ece);

        GaleShapleyAlgorithm galeShapleyAlgorithm = new GaleShapleyAlgorithm(men);
        galeShapleyAlgorithm.run();

        for (Woman woman : women) {
            System.out.println(woman.getName() + " is engaged to " + woman.getHusband().getName());
        }
    }
}
