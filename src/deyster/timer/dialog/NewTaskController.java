package deyster.timer.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import deyster.timer.*;
import deyster.timer.model.Task;

public class NewTaskController 
{
	private Stage dialogStage;
	private boolean okClicked = false;
	private Task newTask;
	
	@FXML
	private TextField taskNameField;
	
	private void initialize() {};
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	/*  Function returns true if ok was clicked. */
	public boolean isOkClicked() {
		return okClicked;
	}
	
	public void setTask(Task newTask) {
		this.newTask = newTask;
	}
	
	public void handleOK() 
	{
		//Do stuff
		newTask.setTaskName(taskNameField.getText());
		
		okClicked = true;
		dialogStage.close();
	}
	
	public void handleCancel() {
		dialogStage.close();
	}
	
}
