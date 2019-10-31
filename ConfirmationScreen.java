import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConfirmationScreen extends JFrame {
    User user;
    Driver driver;
    JLabel tripDetails;
    JButton yesButton, noButton;
    JPanel thePanel;
    ConfirmationScreen(User user, Driver driver)
    {
        this.user = user;
        this.driver = driver;
        this.setTitle("Confirm Booking");
        this.setSize(640, 480);
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
                                + "</html>");
        tripDetails.setBounds(0, 0, 320, 100);
        yesButton = new JButton("Yes");
        yesButton.setBounds(320, 30, 50, 20);
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ;
            }
        });
        noButton = new JButton("No");
        noButton.setBounds(320, 60, 50, 20);
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                disposeWindow();
            }
        });
        thePanel.add(tripDetails);
        thePanel.add(yesButton);
        thePanel.add(noButton);
        this.add(thePanel);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }
    private void disposeWindow()
    {
        user.setAvailability(true);
        driver.setAvailability(true);
        this.setVisible(false);
        this.dispose();
    }
}
