public class Driver
{
    private String name, phoneNumber, carType, carNumber, location;
    private double rating;
    Driver(String name, double rating, String phoneNumber, String carType, String carNumber, String location)
    {
        this.name = name;
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.carType = carType;
        this.carNumber = carNumber;
        this.location = location;
    }
    public String getName()
    {
        return name;
    }
    public double getRating()
    {
        return rating;
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
}