import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NetworkGraph {

    private HashMap<String, DispatchCenters> centers;
    private HashMap<String, List<Path>> paths;

    public NetworkGraph() {
        this.centers = new HashMap<>();
        this.paths = new HashMap<>();
    }

    public void addCenter(DispatchCenters center) {
        centers.put(center.getId(), center);
        paths.put(center.getId(), new ArrayList<>());
    }

    public void removeCenter(String centerId) {
        centers.remove(centerId);
        paths.remove(centerId);
    }

    public void addPath(Path path) {
        String startId = getCenterId(path.getStart());
        if (startId != null) {
            paths.get(startId).add(path);
        }
    }

    public void removePath(String startId, String endId) {
        List<Path> centerPaths = paths.get(startId);
        if (centerPaths != null) {
            centerPaths.removeIf(path -> getCenterId(path.getEnd()).equals(endId));
        }
    }

    public DispatchCenters getCenter(String centerId) {
        return centers.get(centerId);
    }

    public List<DispatchCenters> getAllCenters() {
        return new ArrayList<>(centers.values());
    }

    public List<Path> getPathsFrom(String centerId) {
        return paths.getOrDefault(centerId, new ArrayList<>());
    }

    public List<DispatchCenters> getNeighbors(String centerId) {
        List<DispatchCenters> neighbors = new ArrayList<>();
        List<Path> centerPaths = paths.get(centerId);

        if (centerPaths != null) {
            for (Path path : centerPaths) {
                String neighborId = getCenterId(path.getEnd());
                if (neighborId != null) {
                    neighbors.add(centers.get(neighborId));
                }
            }
        }

        return neighbors;
    }

    private String getCenterId(Position position) {
        for (DispatchCenters center : centers.values()) {
            if (center.getPosition().equals(position)) {
                return center.getId();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "NetworkGraph{" +
                "centers=" + centers.size() +
                ", paths=" + paths.size() +
                '}';
    }
}