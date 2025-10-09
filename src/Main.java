import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {


        Emergency a = new Emergency("1", "Manchester", EmergencyType.medicalEmergency, PriorityType.CRITICAL, "Heart attack reported", LocalDateTime.now());
        Emergency b = new Emergency("2", "Manchester", EmergencyType.medicalEmergency, PriorityType.HIGH, "Heart attack reported", LocalDateTime.now());
        System.out.println(a);
        System.out.println(b);



    }
}


