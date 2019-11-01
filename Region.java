import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

class City implements Serializable // Define properties of a city
{
    private String cityName;
    private int cityID;
    private TreeMap<Integer, Integer> distanceToCities;
    City(String cityName)
    {
        this.cityName = cityName;
        cityID = -1;
        distanceToCities = new TreeMap<>();
    }
    String getCityName()
    {
        return cityName;
    }
    void setCityID(int cityID)
    {
        this.cityID = cityID;
    }
    int getCityID()
    {
        return cityID;
    }
    void setDistanceToCity(City toCity, int distance)
    {
        int toCityID = toCity.getCityID();
        if(distanceToCities.containsKey(toCityID))
            distanceToCities.replace(toCityID, distance);
        else
            distanceToCities.put(toCityID, distance);
    }
    int getDistanceToCity(City toCity)                 // Returns the distance to a city if a path exists, or else -1
    {
        try
        {
            int toCityID = toCity.getCityID();
            return distanceToCities.getOrDefault(toCityID, -1);
        }
        catch(Exception E)
        {
            System.err.println("Failed to get distance from City "+this.cityName+" to City "+cityName+"!" + E);
            return -1;
        }
    }
}
public class Region implements java.io.Serializable
{
    private TreeMap<String, City> mapOfCities;
    Region()
    {
        mapOfCities = new TreeMap<>();
    }
    int getNumberOfCities()
    {
        return mapOfCities.size();
    }
    City getCity(String CityName)
    {
        return mapOfCities.getOrDefault(CityName, null);
    }
    ArrayList<String> getAllCityNames()
    {
        ArrayList<String> allCityNames = new ArrayList<>();
        allCityNames.addAll(mapOfCities.keySet());
        return allCityNames;
    }
    boolean addCity(City newCity)                           // Add city to the region list
    {
        try
        {
            String newCityName = newCity.getCityName();
            if(mapOfCities.containsKey(newCityName))        // Can't add city if it already exists in the map
                return false;
            else
            {
                newCity.setCityID(mapOfCities.size());      // Assign unique ID to city
                mapOfCities.put(newCityName, newCity);
                return true;
            }
        }
        catch(Exception E)
        {
            System.err.println("Failed to add new City!\n" + E);
            return false;
        }
    }
    boolean addRoad(String City1Name, String City2Name, int distance)
    {
        if( !mapOfCities.containsKey(City1Name) || !mapOfCities.containsKey(City2Name))     // Check for invalid cities
            return false;
        try
        {
            City City1 = mapOfCities.get(City1Name), City2 = mapOfCities.get(City2Name);
            City1.setDistanceToCity(City2, distance);
            City2.setDistanceToCity(City1, distance);
            return true;
        }
        catch(Exception E)
        {
            System.err.println("Unable to create road between "+City1Name+" and "+City2Name+"!\n" + E);
            return false;
        }
    }
    void generateShortestRoutes()
    {
        final int numberOfCities = getNumberOfCities();
        final int noPathExists = 1_000_000_000;                                         // Represent no path by extremely large value
        ArrayList<ArrayList<Integer>> distances = new ArrayList<>(numberOfCities);      // A 2D vector to store the shortest path from each city to another
        for(City City1 : mapOfCities.values())
        {
            int City1ID = City1.getCityID();
            ArrayList<Integer> currentCity = new ArrayList<>(numberOfCities);           // Stores the distance from the current city to all other cities
            for(City City2 : mapOfCities.values())
            {
                int City2ID = City2.getCityID();
                int directRoadDistance = City1.getDistanceToCity(City2);
                if(City1 == City2)                                                      // Distance from a city to itself is 0
                    currentCity.set(City2ID, 0);
                else if(directRoadDistance != -1)                                       // If a direct road exists between the two cities, add it.
                    currentCity.set(City2ID, directRoadDistance);
                else
                    currentCity.set(City2ID, noPathExists);
            }
            distances.set(City1ID, currentCity);
        }
        for(int k = 0; k < numberOfCities; k++)                                         // Perform Floyd-Warshall to obtain shortest paths between all cities
            for(int i = 0; i < numberOfCities; i++)
                for(int j = 0; j < numberOfCities; j++)
                    if(distances.get(i).get(k) + distances.get(k).get(j) < distances.get(i).get(j))
                        distances.get(i).set(j, distances.get(i).get(k) + distances.get(k).get(j));
        for(City City1 : mapOfCities.values())                                          // Iterate over all pairs of cities and set assign the shortest distances between them
            for(City City2 : mapOfCities.values())
            {
                int City1ID = City1.getCityID();
                int City2ID = City2.getCityID();
                int shortestRoadsDistance = distances.get(City1ID).get(City2ID);
                City1.setDistanceToCity(City2, shortestRoadsDistance);
            }
    }
    int getTripCost(String City1Name, String City2Name)
    {
        if( !mapOfCities.containsKey(City1Name) || !mapOfCities.containsKey(City2Name))     // Check for invalid cities
            return -1;
        try
        {
            City City1 = mapOfCities.get(City1Name), City2 = mapOfCities.get(City2Name);
            return City1.getDistanceToCity(City2);
        }
        catch(Exception E)
        {
            System.err.println("Unable to get road between "+City1Name+" and "+City2Name+"!\n" + E);
            return -1;
        }
    }
}