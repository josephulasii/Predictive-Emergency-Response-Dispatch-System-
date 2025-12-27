public class Path {

    private Position start;
    private Position end;
    private double distance;
    private int timeTaken;
    private String roadCondition;


    public Path(Position start, Position end, double distance, int timeTaken, String roadCondition) {
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.timeTaken = timeTaken;
        this.roadCondition = roadCondition;
    }


    public Position getStart() {
        return start;
    }

    public void setStart(Position start) {
        this.start = start;
    }

    public Position getEnd() {
        return end;
    }

    public void setEnd(Position end) {
        this.end = end;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(int timeTaken) {
        this.timeTaken = timeTaken;
    }

    public String getRoadCondition() {
        return roadCondition;
    }

    public void setRoadCondition(String roadCondition) {
        this.roadCondition = roadCondition;
    }

    @Override
    public String toString() {
        return "Path{" +
                "start=" + start +
                ", end=" + end +
                ", distance=" + distance +
                ", timeTaken=" + timeTaken +
                ", roadCondition='" + roadCondition + '\'' +
                '}';
    }
}




