import javax.xml.crypto.Data;
import java.io.IOException;

public class Main {
    private static int openWindowCount = 1;
    private static UserList userList;
    private static Region region;
    private static DriverList driverList;
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
    }
    public static void main(String[] args)
    {
        try {
            DataHandler dataHandlerObject = new DataHandler();
            userList = dataHandlerObject.readUserList();
        }
        catch(Exception e)
        {
            System.out.println("Could not read database!");
        }
        if(userList == null)
            userList = new UserList();
            driverList = new DriverList();
            region = new Region();
        //REMOVE
        Driver d1 = new Driver("R", 1.1, "AAA","AAA","AAA","AAA");
        driverList.addUser(d1);
        City c1 = new City("AAA");
        region.addCity(c1);
        //REMOVE
        new LoginScreen(userList, driverList, region);
    }
}
