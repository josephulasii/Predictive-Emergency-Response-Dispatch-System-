import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Predictor {

    private List<Emergency> history;
    private HashMap<String, Integer> areaCount;

    public Predictor() {
        this.history = new ArrayList<>();
        this.areaCount = new HashMap<>();
    }

    public void recordEmergency(Emergency emergency) {
        history.add(emergency);
        String area = getArea(emergency.getPosition());
        areaCount.put(area, areaCount.getOrDefault(area, 0) + 1);
    }

    public List<String> getHighDemandAreas() {
        List<String> hotspots = new ArrayList<>();

        for (String area : areaCount.keySet()) {
            if (areaCount.get(area) >= 2) {
                hotspots.add(area);
            }
        }

        return hotspots;
    }

    public String findBestPositionFor(Unit unit, NetworkGraph network) {
        String currentArea = getArea(unit.getPosition());
        int currentDemand = areaCount.getOrDefault(currentArea, 0);

        String bestArea = currentArea;
        int maxDemand = currentDemand;

        for (DispatchCenters center : network.getAllCenters()) {
            String area = getArea(center.getPosition());
            int demand = areaCount.getOrDefault(area, 0);

            if (demand > maxDemand) {
                maxDemand = demand;
                bestArea = area;
            }
        }

        return bestArea;
    }

    public String getPredictionReport() {
        StringBuilder report = new StringBuilder();
        report.append("\n=== Predictive Analysis Report ===\n");
        report.append("Total emergencies tracked: ").append(history.size()).append("\n");
        report.append("\nEmergency count by area:\n");

        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(areaCount.entrySet());
        sorted.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        for (Map.Entry<String, Integer> entry : sorted) {
            report.append("  ").append(entry.getKey()).append(": ")
                    .append(entry.getValue()).append(" emergencies\n");
        }

        List<String> hotspots = getHighDemandAreas();
        report.append("\nHigh-demand areas identified: ").append(hotspots.size()).append("\n");
        for (String area : hotspots) {
            report.append("  - ").append(area).append(" (")
                    .append(areaCount.get(area)).append(" incidents)\n");
        }

        if (!hotspots.isEmpty()) {
            report.append("\nRecommendation: Consider positioning units in high-demand areas\n");
        }

        return report.toString();
    }

    private String getArea(Position pos) {
        double lat = pos.getLatitude();
        double lon = pos.getLongitude();

        if (lat > 53.0 && lat < 54.0 && lon > -3.0 && lon < -2.0) {
            return "Manchester";
        } else if (lat > 52.0 && lat < 53.0 && lon > -2.0 && lon < -1.0) {
            return "Birmingham";
        } else if (lat > 51.0 && lat < 52.0 && lon > -1.0 && lon < 0.0) {
            return "London";
        } else {
            return "Other";
        }
    }

    public int getHistorySize() {
        return history.size();
    }

    public int getEmergencyCount(String area) {
        return areaCount.getOrDefault(area, 0);
    }

    @Override
    public String toString() {
        return "Predictor{" +
                "tracked=" + history.size() +
                ", hotspots=" + getHighDemandAreas().size() +
                '}';
    }
}