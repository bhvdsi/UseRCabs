class User
{
	private String name, userID, password, phoneNumber, emailID;
	private int walletBalance;
	User(String name, String userID, String password, String phoneNumber, String emailID, int walletBalance)
	{
		this.name = name;
		this.userID = userID;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.emailID = emailID;
		this.walletBalance = walletBalance;
	}
	public String getName()
	{
		return name;
	}
	public String getUserID()
	{
		return userID;
	}
	public String getPassword()
	{
		return password;
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
}
