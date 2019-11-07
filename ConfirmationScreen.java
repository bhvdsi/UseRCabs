import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConfirmationScreen extends JFrame{
    int tripCost;
    String destination;
    private User user;
    private Driver driver;
    private JLabel tripDetails, errorLabel;
    private JButton yesButton, noButton;
    private JPanel thePanel;
    private LoggedInScreen loggedInScreen;
    ConfirmationScreen(User user, Driver driver, int tripCost, String destination, LoggedInScreen loggedInScreen)
    {
        this.user = user;
        this.driver = driver;
        this.tripCost = tripCost;
        this.destination = destination;
        this.loggedInScreen = loggedInScreen;
        this.setTitle("Confirm Booking");
        this.setSize(220, 220);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                Main.changeWindowCount(false);
                super.windowClosed(e);
            }
        });
        thePanel = new JPanel(null);
        tripDetails = new JLabel("<html>"
                                + "Name: " + driver.getName()
                                + "<br>Rating: " + driver.getRating()
                                + "<br>Phone Number: " + driver.getPhoneNumber()
                                + "<br>Trip duration: " + (float)tripCost/1000 + " hours"
                                + "<br>Trip cost: " + tripCost + " rupees"
                                + "<br><br><font color=\"blue\">Confirm Booking?</font>"
                                + "</html>");
        tripDetails.setBounds(10, 10, 320, 120);
        yesButton = new JButton("Yes");
        yesButton.setBounds(10, 140, 95, 20);
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try
                {
                    noButton.setEnabled(false);
                    yesButton.setEnabled(false);
                    errorLabel.setText("Trip Successfully Booked!");
                    Timer delay = new Timer(tripCost, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            user.setWalletBalance(user.getWalletBalance() - tripCost);
                            user.setAvailability(true);
                            driver.setLocation(destination);
                            driver.setAvailability(true);
                            loggedInScreen.updateBalanceLabel();
                            errorLabel.setText("<html><font color=\"green\">Trip successfully ended!</font></html");
                            Timer delay2 = new Timer(1000, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent actionEvent) {
                                    disposeWindow();
                                }
                            });
                            delay2.setRepeats(false);
                            delay2.start();
                        }
                    });
                    delay.setRepeats(false);
                    delay.start();
                }
                catch (Exception e)
                {
                    errorLabel.setText("Error processing trip!");
                    System.err.println("Error processing trip!");
                }
            }
        });
        noButton = new JButton("No");
        noButton.setBounds(110, 140, 95, 20);
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                user.setAvailability(true);
                driver.setAvailability(true);
                disposeWindow();
            }
        });
        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setBounds(10, 165, 200, 20);
        thePanel.add(tripDetails);
        thePanel.add(yesButton);
        thePanel.add(noButton);
        thePanel.add(errorLabel);
        this.add(thePanel);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }
    private void disposeWindow()
    {
        this.setVisible(false);
        this.dispose();
    }
}
