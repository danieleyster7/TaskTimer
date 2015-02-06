package deyster.timer.view;

import deyster.timer.MainApp;
import deyster.timer.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TimerMainController 
{
	@FXML
	private TableView<Task> taskTable;
	@FXML
	private TableColumn<Task, String> taskNameColumn;
	@FXML
	private TableColumn<Task, String> taskTimeColumn;
	private MainApp mainApp;
	
	public TimerMainController() {}
	
	@FXML
	private void initialize()
	{
		//Initialize task table with values from task list
		taskNameColumn.setCellValueFactory(
				cellData -> cellData.getValue().getTaskNameProperty());
		taskTimeColumn.setCellValueFactory(
				cellData -> cellData.getValue().getTaskTimeProperty());
		
		//TODO: Set listener to select task via table
	}
	
	/*
	 * Sets passed MainApp as its main program
	 * Retrieves list of task data and puts on table
	 */
	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
		taskTable.setItems(mainApp.getTaskData());
	}
	
	//TODO: Handle Buttons
}
