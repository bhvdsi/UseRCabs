import java.io.Serializable;

public class User implements Serializable
{
	private String name, userID, password, phoneNumber, emailID;
	private int walletBalance;
	private boolean busyState;
	User(String name, String userID, String password, String phoneNumber, String emailID, int walletBalance)
	{
		this.name = name;
		this.userID = userID;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.emailID = emailID;
		this.walletBalance = walletBalance;
		this.busyState = false;
	}
	public String getName()
	{
		return name;
	}
	public String getUserID()
	{
		return userID;
	}
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	public String getEmailID()
	{
		return emailID;
	}
	public int getWalletBalance()
	{
		return walletBalance;
	}
	public void setWalletBalance(int newWalletBalance)
	{
		walletBalance = newWalletBalance;
	}
	public boolean checkPassword(String inputPassword)
	{
		if(password.equals(inputPassword))
			return true;
		return false;
	}
	public boolean getAvailability()
	{
		return busyState;
	}
	public void setAvailability(boolean newState)
	{
		busyState = newState;
	}
}
