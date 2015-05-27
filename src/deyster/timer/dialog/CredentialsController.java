package deyster.timer.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CredentialsController
{
	private boolean okClicked = false;
	@FXML
	private TextField userName;
	@FXML
	private TextField password;
	@FXML
	private TextField apiKey;
	
	public void handleOK() 
	{
		// If apiKey is empty, check username & password
		if(apiKey.getText().isEmpty())
		{
			// If username and pass is empty, error
			if(userName.getText().isEmpty())
			{
				// Error
			}
			else if(password.getText().isEmpty())
			{
				// Error
			}
			else
			{
				// Use username and password
				okClicked = true;
			}
		}
		else
		{
			// Use apiKey
			okClicked = true;
		}
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
}
