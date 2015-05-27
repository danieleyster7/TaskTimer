package deyster.timer.dialog;

import deyster.timer.model.Task;
import deyster.timer.model.WHDTask;
import javafx.stage.Stage;

public class DialogController 
{
	protected Stage dialogStage;
	
	protected void initialize() {};
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void handleClose() {
		dialogStage.close();
	}
}
