
public class DispatchCenters {

    private String id;
    private String name;
    private Position position;
    private String type;

    public DispatchCenters(String id, String name, Position position, String type) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }


    public double distanceToEmergency(Position emergencyPosition) {
        return this.position.distanceTo(emergencyPosition);
    }

    @Override
    public String toString() {
        return "DispatchedCenter{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", type='" + type + '\'' +
                '}';
    }


}

