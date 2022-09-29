import java.util.*;

public class Solution {
    public static List<Double> runningMedian(List<Integer> a) {
        Queue<Integer> minq = new PriorityQueue();
        Queue<Integer> maxq = new PriorityQueue(Collections.reverseOrder());
        List<Double> res = new ArrayList<>();
        if (a.isEmpty()) return res;
        double d1, d2;
        d1 = (double) a.get(0);
        d2 = 0.0;
        res.add(d1);
        maxq.add(a.get(0));
        for (int i = 1; i < a.size(); i++) {
            if (a.get(i) < maxq.peek()) {
                maxq.add(a.get(i));
            } else {
                minq.add(a.get(i));
            }
            if (maxq.size() == minq.size() + 2) {
                minq.add(maxq.remove());
            } else if (maxq.size() + 2 == minq.size()) {
                maxq.add(minq.remove());
            }
            if (maxq.size() == minq.size() + 1) {
                res.add((double) maxq.peek());
            } else if (maxq.size() + 1 == minq.size()) {
                res.add((double) minq.peek());
            } else {
                d1 = (double)maxq.peek();
                d2 = (double)minq.peek();
                res.add((d1 + d2) / 2);
            }
        }
        return res;
    }
}
