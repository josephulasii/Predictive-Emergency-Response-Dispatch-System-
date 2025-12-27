public class Unit {
    private String id;
    private String type;
    private String availability;
    private Position position;
    private int emergencyId;
    private DispatchCenters homeCenter;

    public Unit(String id, String type, String availability, Position position, int emergencyId, DispatchCenters homeCenter) {
        this.id = id;
        this.type = type;
        this.availability = availability;
        this.position = position;
        this.emergencyId = emergencyId;
        this.homeCenter = homeCenter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getEmergencyId() {
        return emergencyId;
    }

    public void setEmergencyId(int emergencyId) {
        this.emergencyId = emergencyId;
    }

    public DispatchCenters getHomeCenter() {
        return homeCenter;
    }

    public void setHomeCenter(DispatchCenters homeCenter) {
        this.homeCenter = homeCenter;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", availability='" + availability + '\'' +
                ", position=" + position +
                ", emergencyId=" + emergencyId +
                ", homeCenter=" + homeCenter +
                '}';
    }
}
