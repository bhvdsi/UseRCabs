import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

class LoggedInScreen extends JFrame
{
    private User user;
    DriverList driverList;
    Region region;
    private JPanel thePanel;
    private JLabel userInfo, userWalletBalance, sourceLabel, destinationLabel, errorLabel;
    private JComboBox source, destination;
    private JTextField addWalletAmount;
    private JButton addWalletAmountButton, rideBookButton;
    public LoggedInScreen(User user, DriverList driverList, Region region)
    {
        //initialize a few variables
        this.user = user;
        this.driverList = driverList;
        this.region = region;
        this.setTitle(user.getName() + " - Cab Booking Screen");
        this.setSize(640, 480);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                Main.changeWindowCount(false);
                super.windowClosed(e);
            }
        });
        //create the panel
        thePanel = new JPanel(null);
        //label to display user info
        userInfo = new JLabel("<html>"
                                + "Name: " + user.getName() + "<br>"
                                + "Email ID: " + user.getEmailID() + "<br>"
                                + "Phone Number: " + user.getPhoneNumber() + "</html>");
        userInfo.setBounds(0, 0, 320, 50);
        //label to display user balance
        if(user.getWalletBalance() >= 300)
            userWalletBalance = new JLabel("<html>Wallet Balance: <font color=\"green\">"
                    + String.valueOf(user.getWalletBalance())
                    + "</font></html>");
        else
            userWalletBalance = new JLabel("<html>Wallet Balance: <font color=\"red\">"
                    + String.valueOf(user.getWalletBalance())
                    + "</font></html>");
        userWalletBalance.setBounds(0, 50, 320, 20);
        ArrayList<String> cities = region.getAllCityNames();
        //components to select source
        sourceLabel = new JLabel("<html><font color=\"blue\">Starting Point:</font></html>");
        sourceLabel.setBounds(0, 70, 320, 20);
        source = new JComboBox(cities.toArray());
        source.setBounds(0, 90, 320, 20);
        //components to select destination
        destinationLabel = new JLabel("<html><font color=\"blue\">Ending Point:</font></html>");
        destinationLabel.setBounds(0, 110, 320, 20);
        destination = new JComboBox((cities.toArray()));
        destination.setBounds(0, 130, 320, 20);
        //components to add money to wallet of user
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
                    else if(tempAmount > 1000000 || (tempAmount + user.getWalletBalance() > 1000000))
                        errorLabel.setText("Maximum amount allowed is 1000000");
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
        rideBookButton = new JButton("Book the ride");
        rideBookButton.setBounds(320, 130, 320, 20);
        rideBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!user.getAvailability())
                {
                    errorLabel.setText("User is in a ride or booking a ride!");
                }
                else
                {
                    user.setAvailability(false);
                    String startCity = source.getSelectedItem().toString();
                    String endCity = destination.getSelectedItem().toString();
                    Driver closestDriver = driverList.getClosestDriver(region, startCity);
                    if(closestDriver == null)
                    {
                        errorLabel.setText("No drivers available");
                        user.setAvailability(true);
                    }
                    else if(region.getTripCost(startCity, endCity) > user.getWalletBalance() || user.getWalletBalance() < 300)
                    {
                        errorLabel.setText("Insufficient wallet balannce!");
                        user.setAvailability(true);
                        closestDriver.setAvailability(true);
                    }
                    else
                    {
                        Main.changeWindowCount(true);
                        new ConfirmationScreen(user, closestDriver, region.getTripCost(startCity, endCity), endCity);
                        Timer delay = new Timer(region.getTripCost(startCity, endCity) + 1100, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                updateBalanceLabel();
                            }
                        });
                    }
                }
            }
        });
        //label to display errors
        errorLabel = new JLabel("");
        errorLabel.setBounds(0, 190, 320, 20);
        //add panel components
        thePanel.add(userInfo);
        thePanel.add(userWalletBalance);
        thePanel.add(sourceLabel);
        thePanel.add(source);
        thePanel.add(destinationLabel);
        thePanel.add(destination);
        thePanel.add(addWalletAmount);
        thePanel.add(addWalletAmountButton);
        thePanel.add(errorLabel);
        thePanel.add(rideBookButton);
        //set panel properties
        this.add(thePanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
}
