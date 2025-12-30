import java.util.ArrayList;
import java.util.List;

public class PERDS {

    private NetworkGraph network;
    private EmergencyQueue queue;
    private Dispatcher dispatcher;
    private List<Unit> units;
    private List<Emergency> processed;

    public PERDS() {
        this.network = new NetworkGraph();
        this.queue = new EmergencyQueue();
        this.units = new ArrayList<>();
        this.processed = new ArrayList<>();
        this.dispatcher = new Dispatcher(network, units);
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
        System.out.println("Emergency reported: " + emergency.getPriority() + " - " + emergency.getInfo());
    }

    public void processEmergencies() {
        System.out.println("\nProcessing emergencies in priority order...");

        while (!queue.isEmpty()) {
            Emergency emergency = queue.getNext();

            System.out.println("\nHandling: " + emergency.getPriority() + " emergency");
            System.out.println("Location: " + emergency.getPosition());

            boolean success = dispatcher.dispatchToEmergency(emergency);

            if (success) {
                processed.add(emergency);
                System.out.println("Unit dispatched successfully!");
            } else {
                System.out.println("No available units - emergency queued");
                queue.addEmergency(emergency);
                break;
            }
        }
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