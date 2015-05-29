package deyster.timer.dialog;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import deyster.timer.model.Credentials;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CredentialsController extends DialogController
{
	@FXML
	private TextField userName;
	@FXML
	private TextField password;
	@FXML
	private TextField apiKey;
	private Credentials credentials;
	private boolean okClicked = false;
	private final int NULL = 0;
	private final int PASS = 1;
	private final int API = 2;
	
	public void handleOK() 
	{
		//If an API or user/pass is given, close
		if(checkFields() > 0) {
			okClicked = true;
			dialogStage.close();
		}
	}
	
	public void handleSave()
	{
		//If an API or user/pass is given, save data
		if(checkFields() > 0)
		{
			try 
			{
				FileOutputStream fileOut = new FileOutputStream("/prefs/userData.bin");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(credentials);
				out.close();
				fileOut.close();
			}
			catch (IOException io) {
				io.printStackTrace();
			}
		}
	}
	
	public int checkFields()
	{
		// If apiKey is empty, check username & password
		if(apiKey.getText().isEmpty()) {
			// If username is empty, error
			if(userName.getText().isEmpty() || userName.getText().equals("Error, must have username/password")) {
				userName.setText("Error, must have username/password");
			}
			// If password is empty, error
			else if(password.getText().isEmpty() || password.getText().equals("Error, must have username/password")) {
				password.setText("Error, must have username/password");
			}
			else {
				// Use userName and Password
				credentials = new Credentials(userName.getText(), password.getText());
				return PASS;
			}
		}
		else {
			System.out.println("APIKEY DETECTED");
			// Use apiKey
			credentials = new Credentials(apiKey.getText());
			return API;
		}
		return NULL;
	}
	
	public Credentials getCredentials() {
		return credentials;
	}
	
	public boolean isOKClicked() {
		return okClicked;
	}
}
