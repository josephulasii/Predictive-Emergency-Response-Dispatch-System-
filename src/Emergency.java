import java.time.LocalDateTime;


public class Emergency {

    private int id;
    private Position position;
    private String type;
    private String priority;
    private String info;
    private LocalDateTime time;

    public Emergency(int id, Position position, String type, String priority, String info, LocalDateTime time) {
        this.id = id;
        this.position = position;
        this.type = type;
        this.priority = priority;
        this.info = info;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Emergency{" +
                "id=" + id +
                ", position=" + position +
                ", type='" + type + '\'' +
                ", priority='" + priority + '\'' +
                ", info='" + info + '\'' +
                ", time=" + time +
                '}';
    }
}