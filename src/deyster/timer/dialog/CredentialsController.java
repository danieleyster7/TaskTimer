package deyster.timer.dialog;

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
	
	public void handleOK() 
	{
		// If apiKey is empty, check username & password
		if(apiKey.getText().isEmpty())
		{
			// If username is empty, error
			if(userName.getText().isEmpty() || userName.getText().equals("Error, must have username/password")) {
				userName.setText("Error, must have username/password");
			}
			// If password is empty, error
			else if(password.getText().isEmpty() || password.getText().equals("Error, must have username/password")) {
				password.setText("Error, must have username/password");
			}
			else 
			{
				// Use userName and Password
				credentials = new Credentials(userName.getText(), password.getText());	
				okClicked = true;
				dialogStage.close();
			}
		}
		else 
		{
			// Use apiKey
			credentials = new Credentials(apiKey.getText());
			okClicked = true;
			dialogStage.close();
		}
	}
	
	public void handleSave()
	{
		
	}
	
	public Credentials getCredentials() {
		return credentials;
	}
	
	public boolean isOKClicked() {
		return okClicked;
	}
}
