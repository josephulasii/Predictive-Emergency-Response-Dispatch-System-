
import java.util.ArrayList;


public class PERDS {

    private ArrayList<DispatchCenters> dispatchCenters;
    private ArrayList<Emergency> activeEmergencies;

    public PERDS() {
        this.dispatchCenters = new ArrayList<>();
        this.activeEmergencies = new ArrayList<>();
    }

    // Add a dispatch center to the system
    public void addDispatchCenter(DispatchCenters center) {
        this.dispatchCenters.add(center);
    }


}