package deyster.timer.dialog;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import deyster.timer.model.Task;

public class DeleteTaskController 
{
	private Stage dialogStage;
	private boolean okClicked = false;
	private ObservableList<Task> taskData;
	
	@FXML
	private TableView<Task> taskTable;
	@FXML
	private TableColumn<Task, String> taskNameColumn;
	
	private void initialize() {
		//Initialize task table with values from task list
		taskNameColumn.setCellValueFactory(
				cellData -> cellData.getValue().getTaskNameProperty());
	};
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
		taskTable.setItems(taskData);
	}
	
	/*  Function returns true if ok was clicked. */
	public boolean isOkClicked() {
		return okClicked;
	}
	
	// TODO: Pass task variable
	public void passTasks(ObservableList<Task> taskData) {
		this.taskData = taskData;
	}
	
	public void handleOK() 
	{
		//Remove the selected
		taskData.remove(taskTable.getSelectionModel().getSelectedIndex());
		
		okClicked = true;
		dialogStage.close();
	}
	
	public void handleCancel() {
		dialogStage.close();
	}
}
