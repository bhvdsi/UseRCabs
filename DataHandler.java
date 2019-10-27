import java.io.*;

public class DataHandler
{
    private String absolutePath, userListFile, regionFile, driverFile;
    //private final String userListFile = "Users.ser", regionFile = "Region.ser", driverFile = "Driver.ser";
    DataHandler() throws IOException
    {
        try
        {
            absolutePath = new File(".").getCanonicalPath();
            userListFile = absolutePath + File.separator + "Users.ser";
            regionFile = absolutePath + File.separator + "Region.ser";
            driverFile = absolutePath + File.separator + "Drivers.ser";
        }
        catch(Exception InvalidPath)
        {
            System.err.println("Can't get current directory! Exiting...");
            System.exit(-1);
        }
    }
    void writeUserList(UserList toBeWritten) throws IOException
    {
        try
        {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(userListFile));
            output.writeObject(toBeWritten);
            output.close();
        }
        catch(Exception writeFailed)
        {
            System.err.println("Writing of user list to file failed!\n" + writeFailed);
        }
    }
    UserList readUserList() throws IOException, ClassNotFoundException
    {
        UserList newUserList;
        try
        {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(userListFile));
            newUserList = (UserList) input.readObject();
            return newUserList;
        }
        catch (Exception readFailed)
        {
            System.err.println("Reading of user list from file failed!\n" + readFailed);
            return null;
        }
    }
    Driver readDriverList() throws IOException, ClassNotFoundException
    {
        Driver newDriverList;
        try
        {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(driverFile));
            newDriverList = (Driver) input.readObject();
            return newDriverList;
        }
        catch (Exception readFailed)
        {
            System.err.println("Reading of driver list from file failed!\n" + readFailed);
            return null;
        }
    }
    Region readRegionData() throws IOException, ClassNotFoundException
    {
        Region newRegionData;
        try
        {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(regionFile));
            newRegionData = (Region) input.readObject();
            return newRegionData;
        }
        catch (Exception readFailed)
        {
            System.err.println("Reading of region data from file failed!\n" + readFailed);
            return null;
        }
    }
}
