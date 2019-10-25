class User
{
	String name, userID, password, phoneNumber, emailID;
	int walletBalance;
	User(String name, String userID, String password, String phoneNumber, String emailID, int walletBalance)
	{
		this.name = name;
		this.userID = userID;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.emailID = emailID;
		this.walletBalance = walletBalance;
	}
	String getName()
	{
		return name;
	}
	String getUserID()
	{
		return userID;
	}
	String getPassword()
	{
		return password;
	}
	String getPhoneNumber()
	{
		return phoneNumber;
	}
	String getEmailID()
	{
		return emailID;
	}
	int getWalletBalance()
	{
		return walletBalance;
	}
	void setWalletBalance(int newWalletBalance)
	{
		walletBalance = newWalletBalance;
	}
}
