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
        this.setSize(400, 230);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                Main.changeWindowCount(false);
                super.windowClosed(e);
            }
        });
        thePanel = new JPanel(null);
        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(15, 15, 150, 20);
        userIDLabel = new JLabel("UserID:");
        userIDLabel.setBounds(15, 35, 150, 20);
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(15, 55, 150, 20);
        phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setBounds(15, 75, 150, 20);
        emailIDLabel = new JLabel("Email ID: ");
        emailIDLabel.setBounds(15, 95, 150, 20);
        nameField = new JTextField();
        nameField.setBounds(180, 15, 200, 20);
        userIDField = new JTextField();
        userIDField.setBounds(180, 35, 200, 20);
        passwordField = new JPasswordField();
        passwordField.setBounds(180, 55, 200, 20);
        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(180, 75, 200, 20);
        emailIDField = new JTextField();
        emailIDField.setBounds(180, 95, 200, 20);
        createUserButton = new JButton("Create New User");
        createUserButton.setBounds(90, 130, 200, 20);
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
        statusBox = new JLabel("", SwingConstants.CENTER);
        statusBox.setBounds(0, 155, 380, 20);
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
