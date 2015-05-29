package deyster.timer.model;

import java.io.Serializable;

public class Credentials implements Serializable
{
	public static final int API = 2;
	public static final int PASS = 1;
	private String userName;
	private String password;
	private String apiKey;
	private int authType;
	
	public Credentials() {};
	
	public Credentials(String userName, String password)
	{
		this.userName = userName;
		this.password = password;
		authType = PASS;
	}
	
	public Credentials(String apiKey) 
	{
		this.apiKey = apiKey;
		authType = API;
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
	
	public void setAuthType(int authType) {
		this.authType = authType;
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
	
	public int getAuthType() {
		return authType;
	}
}
