

import java.util.ArrayList;

public class DispatchCenters {

    private ArrayList<Unit> units;
    private String id;
    private String name;
    private Position position;
    private String type;

    public DispatchCenters(String id, String name, Position position, String type) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.type = type;
        this.units = new ArrayList<>();
    }



    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setType(String type) {
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

    public void addUnit(Unit unit){
        this.units.add(unit);
    }
    public ArrayList<Unit> getUnits() {
        return this.units;
    }
    public ArrayList<Unit> getAvailableUnits() {
        ArrayList<Unit> available = new ArrayList<>();

        for (Unit unit : this.units) {
            if (unit.getAvailability().equals(UnitAvailability.AVAILABLE)) {
                available.add(unit);
            }
        }


        return available;
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

