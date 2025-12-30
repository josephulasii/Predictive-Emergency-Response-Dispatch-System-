import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== PERDS - Emergency Response System ===\n");

        System.out.println("--- Setting up city positions ---");
        Position londonPos = new Position(51.5074, -0.1278);
        Position manchesterPos = new Position(53.4808, -2.2426);
        Position birminghamPos = new Position(52.4862, -1.8904);

        System.out.println("London: " + londonPos);
        System.out.println("Manchester: " + manchesterPos);
        System.out.println("Birmingham: " + birminghamPos);


        System.out.println("\n--- Creating dispatch centers ---");
        DispatchCenters londonCenter = new DispatchCenters(
                "DC-LDN-001",
                "London Central Hospital",
                londonPos,
                "Hospital"
        );

        DispatchCenters manchesterCenter = new DispatchCenters(
                "DC-MAN-002",
                "Manchester Fire Station",
                manchesterPos,
                "Fire Station"
        );

        DispatchCenters birminghamCenter = new DispatchCenters(
                "DC-BHM-003",
                "Birmingham Police HQ",
                birminghamPos,
                "Police Station"
        );

        System.out.println(londonCenter);
        System.out.println(manchesterCenter);
        System.out.println(birminghamCenter);


        System.out.println("\n--- Creating response units ---");

        Unit ambulance1 = new Unit(
                "UNIT-LDN-AMB-001",
                UnitType.AMBULANCE,
                UnitAvailability.AVAILABLE,
                londonPos,
                0,
                londonCenter
        );

        Unit fireTruck1 = new Unit(
                "UNIT-MAN-FIRE-001",
                UnitType.FIRETRUCK,
                UnitAvailability.AVAILABLE,
                manchesterPos,
                0,
                manchesterCenter
        );

        Unit policeCar1 = new Unit(
                "UNIT-BHM-POL-001",
                UnitType.POLICECAR,
                UnitAvailability.AVAILABLE,
                birminghamPos,
                0,
                birminghamCenter
        );

        Unit ambulance2 = new Unit(
                "UNIT-MAN-AMB-002",
                UnitType.AMBULANCE,
                UnitAvailability.AVAILABLE,
                manchesterPos,
                0,
                manchesterCenter
        );

        System.out.println(ambulance1);
        System.out.println(fireTruck1);
        System.out.println(policeCar1);
        System.out.println(ambulance2);


        System.out.println("\n--- Emergency incidents ---");

        Position emergencyPos1 = new Position(53.4808, -2.2426);
        Emergency emergency1 = new Emergency(
                1,
                emergencyPos1,
                EmergencyType.medicalEmergency,
                PriorityType.CRITICAL,
                "Heart attack at shopping center",
                LocalDateTime.now()
        );

        Position emergencyPos2 = new Position(52.4862, -1.8904);
        Emergency emergency2 = new Emergency(
                2,
                emergencyPos2,
                EmergencyType.fireEmergency,
                PriorityType.HIGH,
                "Building fire reported",
                LocalDateTime.now()
        );

        Emergency e3 = new Emergency(
                3,
                birminghamPos,
                EmergencyType.policeEmergency,
                PriorityType.LOW,
                "Minor incident",
                LocalDateTime.now()
        );


        System.out.println("\n--- Testing Complete PERDS System ---");

        Path londonToManchester = new Path(
                londonPos,
                manchesterPos,
                262.5,
                180,
                "Clear"
        );

        Path londonToBirmingham = new Path(
                londonPos,
                birminghamPos,
                185.0,
                130,
                "Clear"
        );

        PERDS system = new PERDS();

        system.addCenter(londonCenter);
        system.addCenter(manchesterCenter);
        system.addCenter(birminghamCenter);

        system.addPath(londonToManchester);
        system.addPath(londonToBirmingham);

        system.addUnit(ambulance1);
        system.addUnit(ambulance2);
        system.addUnit(fireTruck1);
        system.addUnit(policeCar1);

        system.reportEmergency(emergency1);
        system.reportEmergency(emergency2);
        system.reportEmergency(e3);

        system.processEmergencies();

        System.out.println(system.getSystemStatus());

        System.out.println("\nAll systems operational!");
    }
}