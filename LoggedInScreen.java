import javax.swing.*;
import java.awt.*;

class LoggedInScreen extends JFrame
{
    private User user;
    private JPanel thePanel;
    private JLabel userInfo, userWalletBalance;
    LoggedInScreen(User user)
    {
        this.user = user;
        this.setTitle(user.getName() + " - Cab Booking Screen");
        this.setSize(640, 480);
        thePanel = new JPanel(null);
        userInfo = new JLabel("<html>"
                                + "Name: " + user.getName() + "<br>"
                                + "Email ID: " + user.getEmailID() + "<br>"
                                + "Phone Number: " + user.getPhoneNumber() + "</html>");
        userInfo.setBounds(0, 0, 320, 50);
        userWalletBalance = new JLabel("Wallet Balance: " + String.valueOf(user.getWalletBalance()));
        userWalletBalance.setBounds(0, 50, 320, 20);
        thePanel.add(userInfo);
        thePanel.add(userWalletBalance);
        this.add(thePanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args)
    {
        User temp = new User("Pranav", "vnraap", "iamccgawd", "XXX", "@.com", 520);
        new LoggedInScreen(temp);
        System.out.println("XXX");
    }
}
