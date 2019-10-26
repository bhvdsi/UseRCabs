import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class LoggedInScreen extends JFrame
{
    private User user;
    private JPanel thePanel;
    private JLabel userInfo, userWalletBalance, sourceLabel, destinationLabel, errorLabel;
    private JComboBox source, destination;
    private JTextField addWalletAmount;
    private JButton addWalletAmountButton;
    public LoggedInScreen(User user)
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
        if(user.getWalletBalance() >= 300)
            userWalletBalance = new JLabel("<html>Wallet Balance: <font color=\"green\">"
                    + String.valueOf(user.getWalletBalance())
                    + "</font></html>");
        else
            userWalletBalance = new JLabel("<html>Wallet Balance: <font color=\"red\">"
                    + String.valueOf(user.getWalletBalance())
                    + "</font></html>");
        userWalletBalance.setBounds(0, 50, 320, 20);
        //REPLACE WITH REGION.GETARRAYLIST
        ArrayList<String> cities = new ArrayList<>();
        cities.add("ABC");
        cities.add("DEF");
        cities.add("GHI");
        //END REPLACE
        sourceLabel = new JLabel("<html><font color=\"blue\">Starting Point:</font></html>");
        sourceLabel.setBounds(0, 70, 320, 20);
        source = new JComboBox(cities.toArray());
        source.setBounds(0, 90, 320, 20);
        destinationLabel = new JLabel("<html><font color=\"blue\">Ending Point:</font></html>");
        destinationLabel.setBounds(0, 110, 320, 20);
        destination = new JComboBox((cities.toArray()));
        destination.setBounds(0, 130, 320, 20);
        addWalletAmount = new JTextField(1);
        addWalletAmount.setBounds(0, 150, 320, 20);
        addWalletAmountButton = new JButton("Add Amount to Wallet");
        addWalletAmountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try
                {
                    int tempAmount = Integer.parseInt(addWalletAmount.getText());
                    if(tempAmount < 0)
                        errorLabel.setText("Please enter a valid amount!");
                    else
                    {
                        user.setWalletBalance(user.getWalletBalance() + tempAmount);
                        updateBalanceLabel();
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e);
                    errorLabel.setText("Please enter a valid amount!");
                }
            }
        });
        addWalletAmountButton.setBounds(0, 170, 320, 20);
        errorLabel = new JLabel("");
        errorLabel.setBounds(0, 190, 320, 20);
        thePanel.add(userInfo);
        thePanel.add(userWalletBalance);
        thePanel.add(sourceLabel);
        thePanel.add(source);
        thePanel.add(destinationLabel);
        thePanel.add(destination);
        thePanel.add(addWalletAmount);
        thePanel.add(addWalletAmountButton);
        thePanel.add(errorLabel);
        //set panel properties
        this.add(thePanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    //function to update and color the userWalletBalance Label
    private void updateBalanceLabel()
    {
        if(user.getWalletBalance() >= 300) //to set color to green if balance >= 300
            userWalletBalance.setText("<html>Wallet Balance: <font color=\"green\">"
                                            + String.valueOf(user.getWalletBalance())
                                            + "</font></html>");
        else                                //else set color to red
            userWalletBalance.setText("<html>Wallet Balance: <font color=\"red\">"
                                            + String.valueOf(user.getWalletBalance())
                                            + "</font></html>");
    }
    //main function to test the class
    public static void main(String[] args)
    {
        User temp = new User("Pranav", "vnraap", "iamccgawd", "XXX", "@.com", 220);
        new LoggedInScreen(temp);
        System.out.println(temp.getWalletBalance());
    }
}
