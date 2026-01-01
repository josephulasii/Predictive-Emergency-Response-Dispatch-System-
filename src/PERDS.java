import java.util.ArrayList;
import java.util.List;

public class PERDS {

    private NetworkGraph network;
    private EmergencyQueue queue;
    private Dispatcher dispatcher;
    private Predictor predictor;
    private Visualiser visualiser;
    private List<Unit> units;
    private List<Emergency> processed;

    public PERDS() {
        this.network = new NetworkGraph();
        this.queue = new EmergencyQueue();
        this.units = new ArrayList<>();
        this.processed = new ArrayList<>();
        this.dispatcher = new Dispatcher(network, units);
        this.predictor = new Predictor();
        this.visualiser = new Visualiser();
    }

    public void addCenter(DispatchCenters center) {
        network.addCenter(center);
    }

    public void addPath(Path path) {
        network.addPath(path);
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public void reportEmergency(Emergency emergency) {
        queue.addEmergency(emergency);
        predictor.recordEmergency(emergency);
        System.out.println("Emergency reported: " + emergency.getPriority() + " - " + emergency.getInfo());
    }

    public void processEmergencies() {
        System.out.println("\nProcessing emergencies in priority order...");

        while (!queue.isEmpty()) {
            Emergency emergency = queue.getNext();

            System.out.println("\nHandling: " + emergency.getPriority() + " emergency");
            System.out.println("Location: " + emergency.getPosition());

            Unit assignedUnit = dispatcher.findClosestAvailableUnit(emergency.getPosition());

            if (assignedUnit == null) {
                System.out.println("No available units - emergency queued");
                queue.addEmergency(emergency);
                break;
            }

            double distance = assignedUnit.getPosition().distanceTo(emergency.getPosition());
            int responseTime = (int)(distance / 1.5);

            boolean success = dispatcher.dispatchToEmergency(emergency);

            if (success) {
                processed.add(emergency);
                visualiser.recordDispatch(emergency, assignedUnit, distance, responseTime);
                System.out.println("Unit " + assignedUnit.getId() + " dispatched successfully!");
                System.out.println("Distance: " + String.format("%.2f km", distance) +
                        " | ETA: " + responseTime + " mins");
            }
        }

        System.out.println(predictor.getPredictionReport());
        System.out.println(visualiser.generateReport());
    }

    public String getSystemStatus() {
        StringBuilder status = new StringBuilder();
        status.append("\n=== PERDS System Status ===\n");
        status.append("Dispatch Centers: ").append(network.getAllCenters().size()).append("\n");
        status.append("Total Units: ").append(units.size()).append("\n");
        status.append("Available Units: ").append(dispatcher.getAvailableUnits().size()).append("\n");
        status.append("Dispatched Units: ").append(dispatcher.getDispatchedUnits().size()).append("\n");
        status.append("Pending Emergencies: ").append(queue.size()).append("\n");
        status.append("Processed Emergencies: ").append(processed.size()).append("\n");
        return status.toString();
    }

    public String getPredictionReport() {
        return predictor.getPredictionReport();
    }

    public String getVisualisationReport() {
        return visualiser.generateReport();
    }

    public List<Unit> getAvailableUnits() {
        return dispatcher.getAvailableUnits();
    }

    public List<Unit> getDispatchedUnits() {
        return dispatcher.getDispatchedUnits();
    }

    public int getPendingCount() {
        return queue.size();
    }

    public int getProcessedCount() {
        return processed.size();
    }

    @Override
    public String toString() {
        return "PERDS{" +
                "centers=" + network.getAllCenters().size() +
                ", units=" + units.size() +
                ", pending=" + queue.size() +
                ", processed=" + processed.size() +
                '}';
    }
}