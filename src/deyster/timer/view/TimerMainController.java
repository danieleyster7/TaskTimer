package deyster.timer.view;

import java.util.Date;

import deyster.timer.MainApp;
import deyster.timer.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
	@FXML
	private Label workingTask;
	private MainApp mainApp;
	private int startTime, stopTime;
	private Task currentTask;
	
	public TimerMainController() {}
	
	@FXML
	private void initialize()
	{
		//Initialize task table with values from task list
		taskNameColumn.setCellValueFactory(
				cellData -> cellData.getValue().getTaskNameProperty());
		taskTimeColumn.setCellValueFactory(
				cellData -> cellData.getValue().getTaskTimeProperty());
		
		/* Probably don't need this
		 * TODO: Set listener to select task via table
		taskTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> handleSelection(newValue));*/
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
	public void handleStart()
	{
		System.out.println("Start");
		currentTask = taskTable.getSelectionModel().getSelectedItem();
		//System.out.println(currentTask.getTaskName());
		workingTask.setText(currentTask.getTaskName());
		startTime = (int) getNewDate().getTime();
	}
	
	public void handleStop()
	{
		System.out.println("Stop");
		workingTask.setText("Nothing");
		stopTime = (int) getNewDate().getTime();
		currentTask.addTime(stopTime - startTime);
	}
	
	private Date getNewDate()
	{
		return new Date();
	}
}
