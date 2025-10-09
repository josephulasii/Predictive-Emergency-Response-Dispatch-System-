public class Unit {
    private String id;
    private String type;
    private String availability;
    private String unitLocation;
    private int emergencyId;

    public Unit(String id, String type, String availability, String unitLocation, int emergencyId) {
        this.id = id;
        this.type = type;
        this.availability = availability;
        this.unitLocation = unitLocation;
        this.emergencyId = emergencyId;
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

    public String getUnitLocation() {
        return unitLocation;
    }

    public void setUnitLocation(String unitLocation) {
        this.unitLocation = unitLocation;
    }

    public int getEmergencyId() {
        return emergencyId;
    }

    public void setEmergencyId(int emergencyId) {
        this.emergencyId = emergencyId;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id = '" + id + '\'' +
                ", type = '" + type + '\'' +
                ", availability ='"  + availability + '\'' +
                ", unitLocation = '"  + unitLocation + '\'' +
                ", emergencyId = " + emergencyId +
                '}';
    }
}
