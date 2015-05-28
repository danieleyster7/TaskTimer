package deyster.timer.model;

public class Credentials 
{
	private String userName;
	private String password;
	private String apiKey;
	
	public Credentials() {};
	
	public Credentials(String userName, String password)
	{
		this.userName = userName;
		this.password = password;
	}
	
	public Credentials(String apiKey)
	{
		this.apiKey = apiKey;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAPIKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getAPIKey() {
		return apiKey;
	}
}
