package algorithm.Greedy.IntervalSchedulingAndPartitioning;

public class Main {
    public static void main(String[] args) {
        Interval a = new Interval("a", 0, 6);
        Interval b = new Interval("b", 1, 4);
        Interval c = new Interval("c",3, 5);
        Interval d = new Interval("d",3, 8);
        Interval e = new Interval("e",4, 7);
        Interval f = new Interval("f",5, 9);
        Interval g = new Interval("g",6, 10);
        Interval h = new Interval("h",8, 11);

        IntervalScheduling intervalScheduling = new IntervalScheduling(new Interval[]{a, b, c, d, e, f, g, h});

        Interval[] chosenJobs = intervalScheduling.schedule();

        for (Interval interval : chosenJobs) {
            System.out.println(interval);
        }
    }
}
