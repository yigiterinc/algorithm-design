// Created by erinc at 11.02.20

package algorithm.GaleShapleyAlgorithm;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class GaleShapleyAlgorithm {
    private static Queue<Man> freeMen = new LinkedBlockingQueue<>();

    public GaleShapleyAlgorithm(ArrayList<Man> men) {
        GaleShapleyAlgorithm.freeMen.addAll(men);
    }

    public void run() {
        while (!freeMen.isEmpty()) {
            Man man = freeMen.peek();
            Woman woman = man.getNextPreferredWoman();

            if (woman.isFree()) {
                engage(man, woman);
                freeMen.remove();
            } else {
                if (woman.doesPreferToCurrentHusband(man)) {
                    Man previousHusband = woman.getHusband();
                    freeMen.add(previousHusband);
                    woman.engageWith(man);
                }
            }

            man.proceedPreferenceList();
        }
    }

    private void engage(Man man, Woman woman) {
        woman.engageWith(man);
    }
}
