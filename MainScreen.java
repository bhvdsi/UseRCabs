import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame
{
    UserList userList;
    //add RegionList
    //add DriverList
    JPanel thePanel;
    JLabel userIDLabel, passwordLabel, statusLabel;
    JTextField userIDField;
    JPasswordField passwordField;
    JButton loginButton, newUserButton;
    MainScreen(UserList userList)
    {
        this.userList = userList;
        this.setTitle("Login");
        this.setSize(640, 480);
        thePanel = new JPanel(null);
        userIDLabel = new JLabel("UserID:");
        userIDLabel.setBounds(0, 0, 320, 20);
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(0, 20, 320, 20);
        userIDField = new JTextField();
        userIDField.setBounds(320, 0, 320, 20);
        passwordField = new JPasswordField();
        passwordField.setBounds(320, 20, 320, 20);
        loginButton = new JButton("Login");
        loginButton.setBounds(220, 40, 200, 20);
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
                        statusLabel.setText("<html><font color=\"green\">Login successful!</font></html>");
                        new LoggedInScreen(tempUser);
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
        newUserButton.setBounds(220, 60, 200, 20);
        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new RegistrationScreen(userList);
            }
        });
        statusLabel = new JLabel();
        statusLabel.setBounds(220, 80, 200, 20);
        thePanel.add(userIDLabel);
        thePanel.add(passwordLabel);
        thePanel.add(userIDField);
        thePanel.add(passwordField);
        thePanel.add(loginButton);
        thePanel.add(newUserButton);
        thePanel.add(statusLabel);
        this.add(thePanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
