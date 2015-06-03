package deyster.timer.dialog;

import deyster.timer.model.Task;
import deyster.timer.model.WHDTask;
import javafx.stage.Stage;

/* Parent class for general dialog controllers
 * Contains the default initialize, set dialogstage and handling the close button */
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
