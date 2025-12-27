public class Position {
    private double latitude;
    private double longitude;

    public Position(double latitude, double longitude ){
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
// two postions calculating from one ostio to another postion
    public double distanceTo(Position other) {
        double lat1 = this.latitude;
        double lon1 = this.longitude;
        double lat2 = other.latitude;
        double lon2 = other.longitude;

        return Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(lon2 - lon1, 2)) * 111;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return Double.compare(position.latitude, latitude) == 0 &&
                Double.compare(position.longitude, longitude) == 0;
    }

    @Override
    public String toString() {
        return "Position {" +
                "latitude = " + latitude +
                ", longitude = " + longitude +
                '}';
    }
}
