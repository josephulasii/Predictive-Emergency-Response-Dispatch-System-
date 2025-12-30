import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

public class EmergencyQueue {

    private PriorityQueue<Emergency> queue;

    public EmergencyQueue() {
        this.queue = new PriorityQueue<>(new EmergencyComparator());
    }

    public void addEmergency(Emergency emergency) {
        queue.add(emergency);
    }

    public Emergency getNext() {
        return queue.poll();
    }

    public Emergency peek() {
        return queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public List<Emergency> getAll() {
        return new ArrayList<>(queue);
    }

    private static class EmergencyComparator implements Comparator<Emergency> {
        @Override
        public int compare(Emergency e1, Emergency e2) {
            int priority1 = getPriorityValue(e1.getPriority());
            int priority2 = getPriorityValue(e2.getPriority());
            return Integer.compare(priority2, priority1);
        }

        private int getPriorityValue(String priority) {
            if (priority.equals(PriorityType.CRITICAL)) return 4;
            if (priority.equals(PriorityType.HIGH)) return 3;
            if (priority.equals(PriorityType.MEDIUM)) return 2;
            if (priority.equals(PriorityType.LOW)) return 1;
            return 0;
        }
    }

    @Override
    public String toString() {
        return "EmergencyQueue{" +
                "size=" + queue.size() +
                '}';
    }
}