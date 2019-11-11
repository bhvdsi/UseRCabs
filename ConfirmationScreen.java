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
    private JButton yesButton, noButton, rateButton;
    private JPanel thePanel;
    private LoggedInScreen loggedInScreen;
    private JProgressBar progressBar;
    ButtonGroup buttonGroup;
    JRadioButton r1, r2, r3, r4, r5;
    ConfirmationScreen(User user, Driver driver, int tripCost, String destination, LoggedInScreen loggedInScreen)
    {
        this.user = user;
        this.driver = driver;
        this.tripCost = tripCost;
        this.destination = destination;
        this.loggedInScreen = loggedInScreen;
        this.setTitle("Confirm Booking");
        this.setSize(220, 290);
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
                                + "<br>Rating: " + String.format("%.2f",driver.getRating())
                                + "<br>Phone Number: " + driver.getPhoneNumber()
                                + "<br>Car Model: " + driver.getCarType()
                                + "<br>Vehicle ID: " + driver.getCarNumber()
                                + "<br>Trip duration: " + (float)tripCost/1000 + " hours"
                                + "<br>Trip cost: " + (int)(1.1*tripCost) + " rupees"
                                + "<br><br><font color=\"blue\">Confirm Booking?</font>"
                                + "</html>");
        tripDetails.setBounds(10, 10, 320, 160);
        yesButton = new JButton("Yes");
        yesButton.setBounds(10, 180, 95, 20);
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try
                {
                    noButton.setEnabled(false);
                    yesButton.setEnabled(false);
                    for(int i = 1; i <= 100; i++)
                    {
                        int finalI = i;
                        Timer delay3 = new Timer(tripCost / 100 * i, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                progressBar.setValue(finalI);
                            }
                        });
                        delay3.setRepeats(false);
                        delay3.start();
                    }
                    errorLabel.setText("Trip Successfully Booked!");
                    Timer delay = new Timer(tripCost, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            user.setWalletBalance(user.getWalletBalance() - (int)(1.1*tripCost));
                            user.setAvailability(true);
                            driver.setLocation(destination);
                            driver.setAvailability(true);
                            loggedInScreen.updateBalanceLabel();
                            Timer delay2 = new Timer(100, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent actionEvent) {
                                    tripEnded();
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
        noButton.setBounds(110, 180, 95, 20);
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                user.setAvailability(true);
                driver.setAvailability(true);
                disposeWindow();
            }
        });
        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setBounds(10, 205, 200, 20);
        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(10, 230, 200, 20);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setBorderPainted(true);
        r1 = new JRadioButton("1");
        r1.setBounds(10, 0, 40, 20);
        r2 = new JRadioButton("2");
        r2.setBounds(50, 0, 40, 20);
        r3 = new JRadioButton("3");
        r3.setBounds(90, 0, 40, 20);
        r4 = new JRadioButton("4");
        r4.setBounds(130, 0, 40, 20);
        r5 = new JRadioButton("5");
        r5.setBounds(170, 0, 40, 20);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(r1);
        buttonGroup.add(r2);
        buttonGroup.add(r3);
        buttonGroup.add(r4);
        buttonGroup.add(r5);
        rateButton = new JButton("Rate!");
        rateButton.setBounds(60, 20, 100, 20);
        rateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int r = -1;
                if(r1.isSelected())
                    r = 1;
                else if(r2.isSelected())
                    r = 2;
                else if(r3.isSelected())
                    r = 3;
                else if(r4.isSelected())
                    r = 4;
                else if(r5.isSelected())
                    r = 5;
                else
                {
                    errorLabel.setText("Please select a rating!");
                }
                if(r != -1){
                    driver.updateRating(r);
                    disposeWindow();
                }
            }
        });
        thePanel.add(tripDetails);
        thePanel.add(yesButton);
        thePanel.add(noButton);
        thePanel.add(errorLabel);
        thePanel.add(progressBar);
        this.add(thePanel);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }
    private void disposeWindow()
    {
        this.setVisible(false);
        this.dispose();
    }
    private void tripEnded()
    {
        thePanel.remove(tripDetails);
        thePanel.remove(yesButton);
        thePanel.remove(noButton);
        thePanel.remove(progressBar);
        thePanel.add(r1);
        thePanel.add(r2);
        thePanel.add(r3);
        thePanel.add(r4);
        thePanel.add(r5);
        thePanel.add(rateButton);
        errorLabel.setText("<html><font color=\"green\">Trip successfully ended!"
                            + "<br>Please rate the trip!</font></html>");
        errorLabel.setBounds(10, 40, 200, 50);
        thePanel.revalidate();
        thePanel.repaint();
        this.setSize(220, 120);
    }
}
