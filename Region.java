import java.util.ArrayList;

class City                                                  // Define properties of a city
{
    private String cityName;
    private int cityID, Coordinates[];
    City(String cityName, int xCoordinate, int yCoordinate)
    {
        this.cityName = cityName;
        Coordinates = new int[2];
        Coordinates[0] = xCoordinate;
        Coordinates[1] = yCoordinate;
        cityID = -1;
    }
    void setCityID(int cityID)
    {
        this.cityID = cityID;
    }
    int[] getCoordinates()
    {
        return Coordinates;
    }
}
public class Region {
    private ArrayList<City> listOfCities;                   // Store information regarding all cities in the region
    Region()
    {
        listOfCities = new ArrayList<>();
    }
    int getNumberOfCities()
    {
        return listOfCities.size();
    }
    void addCity(City newCity)                          // Add city to the region list
    {
        try
        {
            newCity.setCityID(getNumberOfCities());     // Allocate ID to city
            listOfCities.add(newCity);
        }
        catch(Exception E)
        {
            System.err.println("Failed to add new City\n" + E);
        }
    }
    double getDistance(City C1, City C2)
    {
        int coordinatesC1[] = C1.getCoordinates();
        int coordinatesC2[] = C2.getCoordinates();
        return Math.sqrt(Math.pow((coordinatesC1[0] - coordinatesC2[0]), 2) + Math.pow((coordinatesC1[1] - coordinatesC2[1]), 2));          // Calculate absolute distance between the two points
    }

}