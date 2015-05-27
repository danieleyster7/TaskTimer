package deyster.timer.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import deyster.timer.*;
import deyster.timer.model.Task;
import deyster.timer.model.WHDTask;

public class NewTaskController extends DialogController
{
	private boolean okClicked = false;
	protected WHDTask ticket;
	
	@FXML
	private TextField taskNameField;
	
	/*  Function returns true if ok was clicked. */
	public boolean isOkClicked() {
		return okClicked;
	}
	
	public void handleOK() 
	{
		//Do stuff
		ticket.setTaskName(taskNameField.getText());
		
		okClicked = true;
		dialogStage.close();
	}
	
	public void passTicket(WHDTask ticket) {
		this.ticket = ticket;
	}
}
