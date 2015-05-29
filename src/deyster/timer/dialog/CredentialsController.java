package deyster.timer.dialog;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import deyster.timer.model.Credentials;
import deyster.timer.util.CredentialLoader;
import deyster.timer.util.WHD;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CredentialsController extends DialogController
{
	@FXML
	private TextField userName;
	@FXML
	private PasswordField password;
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
		if(checkFields() > 0) {
			credentials.setEmail(WHD.getEmail(credentials));
			CredentialLoader.save(credentials);
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
				credentials.setEmail(WHD.getEmail(credentials));
				return PASS;
			}
		}
		else {
			// Use apiKey
			credentials = new Credentials(apiKey.getText());
			credentials.setEmail(WHD.getEmail(credentials));
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
