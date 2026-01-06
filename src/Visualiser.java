import java.util.*;

public class Visualiser {

    private List<DispatchRecord> records;

    public Visualiser() {
        this.records = new ArrayList<>();
    }

    public void recordDispatch(Emergency emergency, Unit unit, double distance, int responseTime) {
        records.add(new DispatchRecord(emergency, unit, distance, responseTime));
    }

    public String generateReport() {
        if (records.isEmpty()) {
            return "No data";
        }

        String report = "";
        report += "\n=== Performance Report ===\n";

        report += "\nTotal Dispatches: " + records.size() + "\n";
        report += "Average Distance: " + getAvgDistance() + " km\n";
        report += "Average Time: " + getAvgTime() + " mins\n";

        report += "\nUnit Usage:\n";
        HashMap<String, Integer> unitCount = countUnits();
        for (String unitType : unitCount.keySet()) {
            int count = unitCount.get(unitType);
            String bar = makeBar(count, records.size());
            report += "  " + unitType + ": " + bar + " (" + count + ")\n";
        }

        report += "\nPriority Breakdown:\n";
        HashMap<String, Integer> priorityCount = countPriorities();
        for (String priority : priorityCount.keySet()) {
            int count = priorityCount.get(priority);
            String bar = makeBar(count, records.size());
            report += "  " + priority + ": " + bar + " (" + count + ")\n";
        }

        report += "\nDistance Stats:\n";
        report += "  Shortest: " + getMinDist() + " km\n";
        report += "  Longest: " + getMaxDist() + " km\n";
        report += "  Total: " + getTotalDist() + " km\n";

        return report;
    }

    private double getAvgDistance() {
        double total = 0;
        for (DispatchRecord r : records) {
            total += r.distance;
        }
        return Math.round(total / records.size() * 100.0) / 100.0;
    }

    private double getAvgTime() {
        int total = 0;
        for (DispatchRecord r : records) {
            total += r.responseTime;
        }
        return Math.round((double)total / records.size() * 10.0) / 10.0;
    }

    private double getMinDist() {
        double min = 999999;
        for (DispatchRecord r : records) {
            if (r.distance < min) {
                min = r.distance;
            }
        }
        return Math.round(min * 100.0) / 100.0;
    }

    private double getMaxDist() {
        double max = 0;
        for (DispatchRecord r : records) {
            if (r.distance > max) {
                max = r.distance;
            }
        }
        return Math.round(max * 100.0) / 100.0;
    }

    private double getTotalDist() {
        double total = 0;
        for (DispatchRecord r : records) {
            total += r.distance;
        }
        return Math.round(total * 100.0) / 100.0;
    }

    private HashMap<String, Integer> countUnits() {
        HashMap<String, Integer> counts = new HashMap<>();
        for (DispatchRecord r : records) {
            String type = r.unitType;
            if (counts.containsKey(type)) {
                counts.put(type, counts.get(type) + 1);
            } else {
                counts.put(type, 1);
            }
        }
        return counts;
    }

    private HashMap<String, Integer> countPriorities() {
        HashMap<String, Integer> counts = new HashMap<>();
        for (DispatchRecord r : records) {
            String priority = r.priority;
            if (counts.containsKey(priority)) {
                counts.put(priority, counts.get(priority) + 1);
            } else {
                counts.put(priority, 1);
            }
        }
        return counts;
    }

    private String makeBar(int value, int total) {
        int barSize = (value * 10) / total;
        String bar = "[";
        for (int i = 0; i < 10; i++) {
            if (i < barSize) {
                bar += "█";
            } else {
                bar += " ";
            }
        }
        bar += "]";
        return bar;
    }

    private static class DispatchRecord {
        Emergency emergency;
        String priority;
        String unitType;
        double distance;
        int responseTime;

        DispatchRecord(Emergency emergency, Unit unit, double distance, int responseTime) {
            this.emergency = emergency;
            this.priority = emergency.getPriority();
            this.unitType = unit.getType();
            this.distance = distance;
            this.responseTime = responseTime;
        }
    }

    @Override
    public String toString() {
        return "Visualiser{records=" + records.size() + "}";
    }
}