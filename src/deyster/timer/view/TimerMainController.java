package deyster.timer.view;

import java.util.Date;

import deyster.timer.MainApp;
import deyster.timer.model.Task;
import deyster.timer.model.WHDTask;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TimerMainController 
{
	@FXML
	private TableView<WHDTask> taskTable;
	@FXML
	private TableColumn<WHDTask, String> taskIDColumn;
	@FXML
	private TableColumn<WHDTask, String> taskNameColumn;
	@FXML
	private TableColumn<WHDTask, String> taskTimeColumn;
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
		taskIDColumn.setCellValueFactory(
				cellData -> cellData.getValue().getTaskIDProperty());
		taskNameColumn.setCellValueFactory(
				cellData -> cellData.getValue().getTaskNameProperty());
		taskTimeColumn.setCellValueFactory(
				cellData -> cellData.getValue().getTimeStringProperty());
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
		workingTask.setText(currentTask.getTaskName());
		startTime = (int) getNewDate().getTime();
	}
	
	public void handleStop()
	{
		System.out.println("Stop");
		workingTask.setText("Nothing");
		stopTime = (int) getNewDate().getTime();
		currentTask.addTime(stopTime - startTime);
		System.out.println("" + currentTask.getTimeStringProperty());
	}
	
	private Date getNewDate()
	{
		return new Date();
	}
}
