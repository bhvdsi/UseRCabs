import java.util.ArrayList;

class City                                                  // Define properties of a city
{
    private String cityName;
    private int cityID;
    private ArrayList<Integer> distanceToCities;
    City(String cityName)
    {
        this.cityName = cityName;
        cityID = -1;
    }
    void setCityID(int cityID)
    {
        this.cityID = cityID;
    }
    String getCityName()
    {
        return cityName;
    }
    void setDistanceToCities(ArrayList<Integer> distanceToCities)
    {
        this.distanceToCities = distanceToCities;
    }
    ArrayList<Integer> getDistancesToAllCities()
    {
        return distanceToCities;
    }
    int getDistanceToCity(int cityID)
    {
        try
        {
            return distanceToCities.get(cityID);
        }
        catch(Exception E)
        {
            System.err.println("Failed to get distance from City "+this.cityID+" to City "+cityID+"!" + E);
            return -1;
        }
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
    String getCityName(int cityID)
    {
        try
        {
            return listOfCities.get(cityID).getCityName();
        }
        catch(Exception E)
        {
            System.err.println("Failed to get name of City "+cityID+"!" + E);
            return "";
        }
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
            System.err.println("Failed to add new City!" + E);
        }
    }
    void calculateAllDistances(ArrayList<ArrayList<Integer>> cityDistances)             // Calculate and assign shortest paths between all cities given the shortest
    {
        final int numberOfCities=getNumberOfCities();
        if(numberOfCities != cityDistances.size())                                      // Safety check that the distances provided align with the number of cities present.
        {
            System.err.println("Unable to calculate distances, number of cities is "+numberOfCities+", but data given for "+cityDistances.size()+" cities!");
            return;
        }
        for(int i = 0; i < numberOfCities; i++)                                         // Get initial distances to all cities from each city
        {
            cityDistances.set(i, listOfCities.get(i).getDistancesToAllCities());
            if(cityDistances.get(i).size() != numberOfCities)
            {
                System.err.println("Unable to calculate distances, expected "+numberOfCities+" cities, but "+i+"th city has "+cityDistances.get(i).size()+" elements.");
            }
        }
        for(int k = 0; k < numberOfCities; k++)                                         // Perform Floyd Warshall to get shortest paths between all cities
            for(int i = 0; i < numberOfCities; i++)
                for(int j = 0; j < numberOfCities; j++)
                    if(cityDistances.get(i).get(k) + cityDistances.get(k).get(j) < cityDistances.get(i).get(j) )
                        cityDistances.get(i).set(j, cityDistances.get(i).get(k) + cityDistances.get(k).get(j));
        for(int i = 0; i < numberOfCities; i++)                                         // Assign shortest distance data to respective cities.
        {
            try
            {
                listOfCities.get(i).setDistanceToCities(cityDistances.get(i));
            }
            catch(Exception E)
            {
                System.err.println("Unable to add shortest distance data for city "+i+"!");
            }
        }
    }

}