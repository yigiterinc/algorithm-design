// Created by erinc at 11.02.20

package algorithm.GaleShapleyAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        Man yigit = new Man("Yigit");
        Man cemal = new Man("Cemal");
        Man batuhan = new Man("Batuhan");

        Woman ayse = new Woman("Ayse",
                new ArrayList<>(Arrays.asList(yigit, cemal, batuhan)));

        Woman gulbike = new Woman("Gulbike",
                new ArrayList<>(Arrays.asList(cemal, yigit, batuhan)));

        Woman canan = new Woman("Canan",
                new ArrayList<>(Arrays.asList(cemal, yigit, batuhan)));

        cemal.setPreferredSpouses(new LinkedList<>(Arrays.asList(ayse, gulbike, canan)));
        yigit.setPreferredSpouses(new LinkedList<>(Arrays.asList(gulbike, ayse, canan)));
        batuhan.setPreferredSpouses(new LinkedList<>(Arrays.asList(ayse, gulbike, canan)));

        ArrayList<Man> men = new ArrayList<>(Arrays.asList(cemal, yigit, batuhan));
        ArrayList<Woman> women = new ArrayList<>(Arrays.asList(gulbike, ayse, canan));

        GaleShapleyAlgorithm galeShapleyAlgorithm = new GaleShapleyAlgorithm(men);
        galeShapleyAlgorithm.run();

        for (Woman woman : women) {
            System.out.println(woman.getName() + " is engaged to " + woman.getHusband().getName());
        }
    }
}
