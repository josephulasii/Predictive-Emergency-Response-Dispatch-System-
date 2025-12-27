import java.time.LocalDateTime;

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


        System.out.println("\n--- Emergency incidents reported ---");

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

        System.out.println("CRITICAL: " + emergency1);
        System.out.println("HIGH: " + emergency2);


        System.out.println("\n--- Calculating distances to emergencies ---");

        double londonToManchesterEmergency = londonPos.distanceTo(emergencyPos1);
        double manchesterToManchesterEmergency = manchesterPos.distanceTo(emergencyPos1);
        double birminghamToManchesterEmergency = birminghamPos.distanceTo(emergencyPos1);

        System.out.println("Emergency 1 (Manchester):");
        System.out.println("  - Distance from London Center: " +
                String.format("%.2f km", londonToManchesterEmergency));
        System.out.println("  - Distance from Manchester Center: " +
                String.format("%.2f km", manchesterToManchesterEmergency));
        System.out.println("  - Distance from Birmingham Center: " +
                String.format("%.2f km", birminghamToManchesterEmergency));


        System.out.println("\n--- Finding closest available unit for Emergency 1 ---");

        double ambulance1Distance = ambulance1.getPosition().distanceTo(emergencyPos1);
        double ambulance2Distance = ambulance2.getPosition().distanceTo(emergencyPos1);
        double fireTruck1Distance = fireTruck1.getPosition().distanceTo(emergencyPos1);

        System.out.println("Unit distances to emergency:");
        System.out.println("  - Ambulance 1 (London): " + String.format("%.2f km", ambulance1Distance));
        System.out.println("  - Ambulance 2 (Manchester): " + String.format("%.2f km", ambulance2Distance));
        System.out.println("  - Fire Truck 1 (Manchester): " + String.format("%.2f km", fireTruck1Distance));

        System.out.println("\nClosest available ambulance: Ambulance 2 (" +
                String.format("%.2f km", ambulance2Distance) + ")");


        System.out.println("\n--- Dispatching unit to emergency ---");

        System.out.println("BEFORE dispatch:");
        System.out.println("  Ambulance2 status: " + ambulance2.getAvailability());
        System.out.println("  Ambulance2 position: " + ambulance2.getPosition());
        System.out.println("  Ambulance2 assigned emergency: " + ambulance2.getEmergencyId());

        ambulance2.setAvailability(UnitAvailability.DISPATCHED);
        ambulance2.setEmergencyId(emergency1.getId());

        System.out.println("\nAFTER dispatch:");
        System.out.println("  Ambulance2 status: " + ambulance2.getAvailability());
        System.out.println("  Ambulance2 position: " + ambulance2.getPosition());
        System.out.println("  Ambulance2 assigned emergency: " + ambulance2.getEmergencyId());


        System.out.println("\n--- Creating route for dispatched unit ---");

        Path route = new Path(
                ambulance2.getPosition(),
                emergencyPos1,
                ambulance2Distance,
                5,
                "Clear"
        );

        System.out.println(route);
        System.out.println("ETA: " + route.getTimeTaken() + " minutes");


        System.out.println("\n--- Simulating traffic delay ---");

        System.out.println("Traffic reported on route!");
        route.setRoadCondition("Congested");
        route.setTimeTaken(15);

        System.out.println("Updated route: " + route);
        System.out.println("New ETA: " + route.getTimeTaken() + " minutes");


        System.out.println("\n--- Emergency resolved ---");

        ambulance2.setAvailability(UnitAvailability.AVAILABLE);
        ambulance2.setEmergencyId(0);
        ambulance2.setPosition(manchesterPos);

        System.out.println("Unit returned to base:");
        System.out.println(ambulance2);


        System.out.println("\n=== System Summary ===");
        System.out.println("Dispatch Centers: 3 (London, Manchester, Birmingham)");
        System.out.println("Response Units: 4 (2 Ambulances, 1 Fire Truck, 1 Police Car)");
        System.out.println("Active Emergencies: 0");
        System.out.println("Resolved Emergencies: 1");
        System.out.println("\nAll systems operational! ✓");
    }
}