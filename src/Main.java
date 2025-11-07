import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {



        Emergency a = new Emergency(1, "Manchester", EmergencyType.medicalEmergency, PriorityType.CRITICAL, "Heart attack reported", LocalDateTime.now());
        Emergency b = new Emergency(2, "Birmingham", EmergencyType.fireEmergency, PriorityType.HIGH, "Building fire", LocalDateTime.now());

        Unit u1 = new Unit("UNIT01", UnitType.AMBULANCE, UnitAvailability.DISPATCHED, "Ealing", 1);
        Unit u2 = new Unit("UNIT02", UnitType.FIRETRUCK, UnitAvailability.AVAILABLE, "London", 0);

        Position london = new Position(2.4,3.3);
        Position manny = new Position (2.2,3.3);

        double distance = london.distanceTo(manny);
        System.out.println("distance to Manny: " + distance);
        double distance2 = manny.distanceTo(london);
        System.out.println("distance to London: " + distance2);


        System.out.println(a);
        System.out.println(b);
        System.out.println(u1);
        System.out.println(u2);
        System.out.println(london);
        System.out.println(manny);


        



    }
}


