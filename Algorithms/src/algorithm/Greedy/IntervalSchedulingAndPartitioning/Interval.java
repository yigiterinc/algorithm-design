package algorithm.Greedy.IntervalSchedulingAndPartitioning;

public class Interval {
    private String id;
    private int startTime;
    private int finishTime;

    public Interval(String id, int startTime, int finishTime) {
        this.id = id;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    @Override
    public String toString() {
        return this.id + ": " + "start:  " + this.startTime + " finish: " + this.finishTime;
    }
}
