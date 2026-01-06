import java.util.*;

public class Navigator {

    private NetworkGraph network;

    public Navigator(NetworkGraph network) {
        this.network = network;
    }

    public Path findShortestPath(String startId, String endId) {

        HashMap<String, Double> distances = new HashMap<>();
        HashMap<String, String> previous = new HashMap<>();
        HashSet<String> visited = new HashSet<>();

        for (DispatchCenters center : network.getAllCenters()) {
            distances.put(center.getId(), 999999.0);
        }
        distances.put(startId, 0.0);

        while (visited.size() < network.getAllCenters().size()) {

            String current = getClosestUnvisited(distances, visited);
            if (current == null) break;

            visited.add(current);

            if (current.equals(endId)) break;

            List<Path> paths = network.getPathsFrom(current);
            for (Path path : paths) {
                String neighbor = findCenterId(path.getEnd());
                if (neighbor == null || visited.contains(neighbor)) continue;

                double newDist = distances.get(current) + path.getDistance();

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, current);
                }
            }
        }

        if (!distances.containsKey(endId) || distances.get(endId) == 999999.0) {
            return null;
        }

        DispatchCenters start = network.getCenter(startId);
        DispatchCenters end = network.getCenter(endId);

        double totalDist = distances.get(endId);
        int time = (int)(totalDist / 1.5);

        return new Path(start.getPosition(), end.getPosition(), totalDist, time, "Clear");
    }

    private String getClosestUnvisited(HashMap<String, Double> distances, HashSet<String> visited) {
        String closest = null;
        double minDist = 999999.0;

        for (String centerId : distances.keySet()) {
            if (!visited.contains(centerId) && distances.get(centerId) < minDist) {
                minDist = distances.get(centerId);
                closest = centerId;
            }
        }

        return closest;
    }

    private String findCenterId(Position pos) {
        for (DispatchCenters center : network.getAllCenters()) {
            if (center.getPosition().equals(pos)) {
                return center.getId();
            }
        }
        return null;
    }
}