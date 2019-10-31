import java.util.ArrayList;

public class DriverList implements java.io.Serializable
{
    private ArrayList<Driver> listOfDrivers;
    void addUser(Driver newDriver)
    {
        try
        {
            listOfDrivers.add(newDriver);
        }
        catch(Exception E)
        {
            System.err.println("Unable to add driver with car number " + newDriver.getCarNumber() + " to user list!\n" + E);
        }
    }
    Driver getClosestDriver(Region Cities, String fromRequiredCityName)     // Finds closest driver with the highest rating as well as marks them as busy
    {
        City fromRequiredCity = Cities.getCity(fromRequiredCityName);
        if(fromRequiredCity == null)
        {
            System.err.println("Can't get closest driver! The required reference city "+fromRequiredCityName+" doesn't exist in the cities list!");
            return null;
        }
        if(listOfDrivers.size() == 0)                       // Sanity check that drivers list has been initialized correctly
        {
            System.err.println("Can't get closest driver! No drivers are present in the drivers list!");
            return null;
        }
        Driver selectedDriver = null;                       // No driver selected initially
        int currentBestDistance = 1_000_000_000;            // Values for comparision, initial values don't matter as the selected driver being null is the first condition checked
        double currentBestRating = 0.0;
        for(Driver currentDriver : listOfDrivers)
        {
            if(currentDriver.getAvailability())             // If driver is available, mark driver as unavailable for entirety of process to prevent double booking situations
                currentDriver.setAvailability(false);
            else                                            // Driver not available
                continue;
            String currentDriverCityName = currentDriver.getLocation();
            City currentDriverCity = Cities.getCity(currentDriverCityName);
            if(currentDriverCity == null)                   // Sanity check on Driver City names
                continue;
            int currentDriverDistance = fromRequiredCity.getDistanceToCity(currentDriverCity);
            double currentDriverRating = currentDriver.getRating();
            if(currentDriverDistance == -1)
                continue;
            if(selectedDriver == null || currentDriverDistance < currentBestDistance || (currentDriverDistance == currentBestDistance && currentDriverRating > currentBestRating))    // Update Driver if better one found
            {
                if(selectedDriver != null)                      // Free up driver if he is no longer our selection
                    selectedDriver.setAvailability(true);
                selectedDriver = currentDriver;
                currentBestDistance = currentDriverDistance;
                currentBestRating = currentDriverRating;
            }
            else                                                // Free up current driver as he is not our selection
                currentDriver.setAvailability(true);
        }
        return selectedDriver;
    }
}
