import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {


        Emergency a = new Emergency(1, "Manchester", EmergencyType.medicalEmergency, PriorityType.CRITICAL, "Heart attack reported", LocalDateTime.now());
        Emergency b = new Emergency(2, "Birmingham", EmergencyType.fireEmergency, PriorityType.HIGH, "Building fire", LocalDateTime.now());

        Unit u1 = new Unit("UNIT-001", UnitType.AMBULANCE, UnitAvailability.DISPATCHED, "Ealing", 1);
        Unit u2 = new Unit("UNIT-002", UnitType.FIRETRUCK, UnitAvailability.AVAILABLE, "London", 0);

        System.out.println(a);
        System.out.println(b);
        System.out.println(u1);
        System.out.println(u2);



    }
}


