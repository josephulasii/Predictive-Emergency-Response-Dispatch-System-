import java.time.LocalDateTime;


public class Emergency {

    private String id;
    private String location;
    private String type;
    private String priority;
    private String info;
    private LocalDateTime time;

    public Emergency(String id, String location, String type, String priority, String info, LocalDateTime time) {
        this.id = id;
        this.location = location;
        this.type = type;
        this.priority = priority;
        this.info = info;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        return "Emergency {" +
                "id = '" + id + '\'' +
                ", location = '" + location + '\'' +
                ", type = '" + type + '\'' +
                ", priority = '" + priority + '\'' +
                ", info = '" + info + '\'' +
                ", time = " + time +
                '}';
    }
}