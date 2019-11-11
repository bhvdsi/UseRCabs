import java.io.Serializable;

public class Driver implements Serializable
{
    private String name, phoneNumber, carType, carNumber, location;
    private int tripCount;
    private double rating;
    private boolean available;
    Driver(String name, double rating, String phoneNumber, String carType, String carNumber, String location, int tripCount)
    {
        this.name = name;
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.carType = carType;
        this.carNumber = carNumber;
        this.location = location;
        this.available = true;
        this.tripCount = tripCount;
    }
    public String getName()
    {
        return name;
    }
    public double getRating()
    {
        return rating;
    }
    private void updateRating(int givenRating)
    {
        double sumRating = rating * tripCount + givenRating;
        rating = sumRating / ++tripCount;
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public String getCarType()
    {
        return carType;
    }
    public String getCarNumber()
    {
        return carNumber;
    }
    public String getLocation()
    {
        return location;
    }
    public void setLocation(String newLocation)
    {
        location = newLocation;
    }
    public boolean getAvailability()
    {
        return available;
    }
    public void setAvailability(boolean newState)
    {
        available = newState;
    }
}