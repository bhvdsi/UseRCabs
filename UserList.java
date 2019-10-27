import java.util.ArrayList;

public class UserList
{
    private ArrayList<User> listOfUsers;
    UserList()
    {
        listOfUsers = new ArrayList<>();
    }
    boolean userExists(String userID)
    {
        for(User currentUser : listOfUsers)             // Iterate through list of users and see if user with required username exists
            if(currentUser.getUserID().equals(userID))
                return true;
         return false;
    }
    User getUser(String userID)
    {
        for(User currentUser : listOfUsers)
            if(currentUser.getUserID().equals(userID))
                return currentUser;
        return null;                                    // Return null object if given user doesn't exist
    }
    void addUser(User newUser)
    {
        try
        {
            listOfUsers.add(newUser);
        }
        catch(Exception E)
        {
            System.err.println("Unable to add user " + newUser.getUserID() + " to user list!\n" + E);
        }
    }
    ArrayList<User> getUserList()
    {
        return listOfUsers;
    }
}
