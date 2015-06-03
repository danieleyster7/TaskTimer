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
/*
 * Controller class for the credentials dialog box
 * Class handles the actions of the ok, save and cancel buttons
 */
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
	
	// Fires when OK clicked
	public void handleOK() 
	{
		//If an API or user/pass is given, close
		if(checkFields() > 0) {
			okClicked = true;
			dialogStage.close();
		}
	}
	
	// Fires when Save clicked
	public void handleSave()
	{
		//If an API or user/pass is given, save data
		if(checkFields() > 0) {
			//Save credentials to local disk
			CredentialLoader.save(WHD.credentials);
		}
	}
	
	/* Function checks the fields to make sure either a username/pass is entered or a api key
	 * Function then assigns the credentials to the WHD static class and has it fetch the email for the user */
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
				WHD.credentials = new Credentials(userName.getText(), password.getText());
				WHD.getEmail();
				return PASS;
			}
		}
		else {
			// Use apiKey
			WHD.credentials = new Credentials(apiKey.getText());
			WHD.getEmail();
			return API;
		}
		return NULL;
	}
	
	// Returns true if ok was clicked, used by rootcontroller to determine if it should attempt to load tickets
	public boolean isOKClicked() {
		return okClicked;
	}
}
