package deyster.timer.dialog;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import deyster.timer.model.Task;
import deyster.timer.model.WHDTask;

public class DeleteTaskController extends DialogController
{
	private boolean okClicked = false;
	private ObservableList<WHDTask> taskData;
	
	@FXML
	private TableView<WHDTask> taskTable;
	@FXML
	private TableColumn<WHDTask, String> taskNameColumn;
	
	@FXML
	protected void initialize() {
		//Initialize task table with values from task list
		taskNameColumn.setCellValueFactory(
				cellData -> cellData.getValue().getTaskNameProperty());
	}
	
	public void setDialogStage(Stage dialogStage) {
		super.setDialogStage(dialogStage);
		taskTable.setItems(taskData);
	}
	
	/*  Function returns true if ok was clicked. */
	public boolean isOkClicked() {
		return okClicked;
	}
	
	// TODO: Pass task variable
	public void passTickets(ObservableList<WHDTask> taskData) {
		this.taskData = taskData;
	}
	
	public void handleDelete() 
	{
		//Remove the selected
		taskData.remove(taskTable.getSelectionModel().getSelectedIndex());
	}
}
