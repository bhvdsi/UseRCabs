import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginScreen extends JFrame
{
    UserList userList;
    DriverList driverList;
    Region region;
    JPanel thePanel;
    JLabel userIDLabel, passwordLabel, statusLabel, cabLogo;
    JTextField userIDField;
    JPasswordField passwordField;
    JButton loginButton, newUserButton;
    LoginScreen(UserList userList, DriverList driverList, Region region)
    {
        this.userList = userList;
        this.driverList = driverList;
        this.region = region;
        this.setTitle("Login");
        this.setSize(640, 480);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                Main.changeWindowCount(false);
                super.windowClosed(e);
            }
        });
        thePanel = new JPanel(null);
        userIDLabel = new JLabel("UserID:");
        userIDLabel.setBounds(170, 205, 100, 20);
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(170, 205+20, 100, 20);
        userIDField = new JTextField();
        userIDField.setBounds(270, 205, 200, 20);
        passwordField = new JPasswordField();
        passwordField.setBounds(270, 205+20, 200, 20);
        loginButton = new JButton("Login");
        loginButton.setBounds(220, 205+50, 200, 20);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String iUserID = userIDField.getText();
                String iPassword = new String(passwordField.getPassword());
                if(userList.userExists(iUserID))
                {
                    User tempUser = userList.getUser(iUserID);
                    if(tempUser.checkPassword(iPassword))
                    {
                        Main.changeWindowCount(true);
                        statusLabel.setText("<html><font color=\"green\">Login successful!</font></html>");
                        new LoggedInScreen(tempUser, driverList, region);
                    }
                    else
                    {
                        statusLabel.setText("<html><font color=\"red\">Incorrect password!</font></html>");
                    }
                }
                else
                {
                    statusLabel.setText("<html><font color=\"red\">User does not exist!</font></html>");
                }
            }
        });
        newUserButton = new JButton("Create New User");
        newUserButton.setBounds(220, 205+70, 200, 20);
        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                Main.changeWindowCount(true);
                new RegistrationScreen(userList);
            }
        });
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setBounds(220, 205+90, 200, 20);
        ImageIcon icon = new ImageIcon("logo.png");
        cabLogo = new JLabel(icon);
        cabLogo.setBounds(188, 10, 263, 185);
        thePanel.add(userIDLabel);
        thePanel.add(passwordLabel);
        thePanel.add(userIDField);
        thePanel.add(passwordField);
        thePanel.add(loginButton);
        thePanel.add(newUserButton);
        thePanel.add(statusLabel);
        thePanel.add(cabLogo);
        this.add(thePanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
