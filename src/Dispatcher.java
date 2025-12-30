import java.util.List;
import java.util.ArrayList;

public class Dispatcher {

    private NetworkGraph network;
    private Navigator navigator;
    private List<Unit> units;

    public Dispatcher(NetworkGraph network, List<Unit> units) {
        this.network = network;
        this.navigator = new Navigator(network);
        this.units = units;
    }

    public boolean dispatchToEmergency(Emergency emergency) {

        Unit closestUnit = findClosestAvailableUnit(emergency.getPosition());

        if (closestUnit == null) {
            return false;
        }

        closestUnit.setAvailability(UnitAvailability.DISPATCHED);
        closestUnit.setEmergencyId(emergency.getId());

        return true;
    }

    public Unit findClosestAvailableUnit(Position emergencyPos) {
        Unit closest = null;
        double minDistance = 999999.0;

        for (Unit unit : units) {
            if (unit.getAvailability().equals(UnitAvailability.AVAILABLE)) {
                double distance = unit.getPosition().distanceTo(emergencyPos);

                if (distance < minDistance) {
                    minDistance = distance;
                    closest = unit;
                }
            }
        }

        return closest;
    }

    public List<Unit> getAvailableUnits() {
        List<Unit> available = new ArrayList<>();
        for (Unit unit : units) {
            if (unit.getAvailability().equals(UnitAvailability.AVAILABLE)) {
                available.add(unit);
            }
        }
        return available;
    }

    public List<Unit> getDispatchedUnits() {
        List<Unit> dispatched = new ArrayList<>();
        for (Unit unit : units) {
            if (unit.getAvailability().equals(UnitAvailability.DISPATCHED)) {
                dispatched.add(unit);
            }
        }
        return dispatched;
    }

    public Path calculateRoute(Position start, Position end) {
        String startId = findCenterId(start);
        String endId = findCenterId(end);

        if (startId == null || endId == null) {
            return null;
        }

        return navigator.findShortestPath(startId, endId);
    }

    private String findCenterId(Position pos) {
        for (DispatchCenters center : network.getAllCenters()) {
            Position centerPos = center.getPosition();
            if (centerPos.equals(pos)) {
                return center.getId();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Dispatcher{" +
                "available=" + getAvailableUnits().size() +
                ", dispatched=" + getDispatchedUnits().size() +
                '}';
    }
}