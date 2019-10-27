import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegistrationScreen extends JFrame
{
    UserList userList;
    JPanel thePanel;
    JLabel nameLabel, userIDLabel, passwordLabel, phoneNumberLabel, emailIDLabel, statusBox;
    JTextField nameField, userIDField, phoneNumberField, emailIDField;
    JPasswordField passwordField;
    JButton createUserButton;
    RegistrationScreen(UserList userList)
    {
        this.userList = userList;
        this.setTitle("Register New User");
        this.setSize(640, 480);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                Main.changeWindowCount(false);
                super.windowClosed(e);
            }
        });
        thePanel = new JPanel(null);
        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(0, 0, 320, 20);
        userIDLabel = new JLabel("UserID:");
        userIDLabel.setBounds(0, 20, 320, 20);
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(0, 40, 320, 20);
        phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setBounds(0, 60, 320, 20);
        emailIDLabel = new JLabel("Email ID: ");
        emailIDLabel.setBounds(0, 80, 320, 20);
        nameField = new JTextField();
        nameField.setBounds(320, 0, 320, 20);
        userIDField = new JTextField();
        userIDField.setBounds(320, 20, 320, 20);
        passwordField = new JPasswordField();
        passwordField.setBounds(320, 40, 320, 20);
        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(320, 60, 320, 20);
        emailIDField = new JTextField();
        emailIDField.setBounds(320, 80, 320, 20);
        createUserButton = new JButton("Create New User");
        createUserButton.setBounds(220, 110, 200, 20);
        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String iName = nameField.getText();
                String iUserID = userIDField.getText();
                String iPassword = new String(passwordField.getPassword());
                String iPhoneNumber = phoneNumberField.getText();
                String iEmailID = emailIDField.getText();
                if(iName.equals("") || iUserID.equals("") || iPassword.equals("") || iPhoneNumber.equals("") || iEmailID.equals(""))
                    statusBox.setText("<html><font color=\"red\">Please Fill All Fields</font></html>");
                else if(userList.userExists(iUserID))
                    statusBox.setText("<html><font color=\"red\">User with userID already exists</font></html>");
                else
                {
                    User newUser = new User(iName, iUserID, iPassword, iPhoneNumber, iEmailID, 0);
                    userList.addUser(newUser);
                    statusBox.setText("<html><font color=\"green\">User successfully created!</font></html>");
                }
            }
        });
        statusBox = new JLabel();
        statusBox.setBounds(170, 130, 300, 20);
        thePanel.add(nameLabel);
        thePanel.add(userIDLabel);
        thePanel.add(passwordLabel);
        thePanel.add(phoneNumberLabel);
        thePanel.add(emailIDLabel);
        thePanel.add(nameField);
        thePanel.add(userIDField);
        thePanel.add(phoneNumberField);
        thePanel.add(emailIDField);
        thePanel.add(passwordField);
        thePanel.add(statusBox);
        thePanel.add(createUserButton);
        this.add(thePanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
