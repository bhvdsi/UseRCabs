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
}
