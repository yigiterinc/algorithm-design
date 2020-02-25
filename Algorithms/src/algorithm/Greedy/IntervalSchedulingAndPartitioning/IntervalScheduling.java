package algorithm.Greedy.IntervalSchedulingAndPartitioning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class IntervalScheduling {
    private Interval[] intervals;

    public IntervalScheduling(Interval[] intervals)  {
        this.intervals = intervals;
    }

    public Interval[] schedule() {
        // O(nlogn)
        Arrays.sort(intervals, new CompareByFinishTime());
        int earliestFinishTime = intervals[0].getFinishTime();
        List<Interval> chosenJobs = new ArrayList<>();
        chosenJobs.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].getStartTime() >= earliestFinishTime) {
                chosenJobs.add(intervals[i]);
                earliestFinishTime = intervals[i].getFinishTime();
            }
        }

        return chosenJobs.toArray(new Interval[0]);
    }
}

class CompareByFinishTime implements Comparator<Interval> {
    @Override
    public int compare(Interval o1, Interval o2) {
        return Integer.compare(o1.getFinishTime(), o2.getFinishTime());
    }
}