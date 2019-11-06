import javax.xml.crypto.Data;
import java.io.IOException;

public class Main {
    private static int openWindowCount = 1;
    private static UserList userList;
    private static Region region;
    private static DriverList driverList;
    private static DataHandler dataHandlerObject;
    public static void changeWindowCount(boolean newOpened)
    {
        if(newOpened)
            openWindowCount++;
        else
            openWindowCount--;
        if(openWindowCount == 0)
        {
            try
            {
                DataHandler dataHandlerObject = new DataHandler();
                dataHandlerObject.writeUserList(userList);
            }
            catch (Exception e)
            {
                System.err.println("Error could not write userList");
            }
        }
        System.err.println(openWindowCount);
    }
    public static void main(String[] args)
    {
        try {
            dataHandlerObject = new DataHandler();
        }
        catch(Exception e)
        {
            System.out.println("Could not read database!");
        }
        try {
            userList = dataHandlerObject.readUserList();
        }
        catch (Exception e)
        {
            System.err.println("Couldn't read userList");
        }
        try {
            driverList = dataHandlerObject.readDriverList();
        }
        catch (Exception e)
        {
            System.err.println("Couldn't read driverList");
        }
        try {
            region = dataHandlerObject.readRegionData();
        }
        catch (Exception e)
        {
            System.err.println("Couldn't read regionData");
        }
        if(userList == null)
            userList = new UserList();
        if(driverList == null)
            driverList = new DriverList();
        if(region == null)
            region = new Region();
        new LoginScreen(userList, driverList, region);
    }
}
