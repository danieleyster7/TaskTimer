package deyster.timer.view;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Timer;

import deyster.timer.MainApp;
import deyster.timer.model.Task;
import deyster.timer.model.WHDTask;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Duration;

/* Controller class for handling actions on the main window
 * Contains functions for populating the table, starting and stopping time,
 * details, and new notes */
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
	private Label workingTask, timerLabel;
	private MainApp mainApp;
	private int startTime, stopTime;
	private Task currentTask;
	private DateFormat timerFormat;
	private Timeline timer;
	
	public TimerMainController() {}
	
	/* initializes the task table with the users ticket data */
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
	
	/* Sets passed MainApp as its main program
	 * Retrieves list of task data and puts on table */
	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
		taskTable.setItems(mainApp.getTaskData());
	}
	
	/* Displays the task being timed and records the time of start
	 * TODO: display running time in realtime */
	public void handleStart()
	{
		currentTask = taskTable.getSelectionModel().getSelectedItem();
		workingTask.setText(currentTask.getTaskName());
		startTime = (int) getNewDate().getTime();
		/*timerFormat = DateFormat.getInstance();
		timer = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						Calendar cal = Calendar.getInstance();
						timerLabel.setText(timerFormat.format(cal.getTime()));
					}
				}));
		timer.setCycleCount(Animation.INDEFINITE);
		timer.play();*/
	}
	
	/* Stops the running time for the ticket
	 * records the time to the ticket (locally) */
	public void handleStop()
	{
		System.out.println("Stop");
		workingTask.setText("Nothing");
		stopTime = (int) getNewDate().getTime();
		currentTask.addTime(stopTime - startTime);
	}
	
	//TODO: implement new note to ticket
	public void handleNewNote() {
		mainApp.showNewNoteDialog(taskTable.getSelectionModel().getSelectedItem());
	}
	
	/* Displays the details dialog box for the selected ticket */
	public void handleDetails() {
		mainApp.showDetailsDialog(taskTable.getSelectionModel().getSelectedItem());
	}
	
	// Helper function for stop/start time
	private Date getNewDate() {
		return new Date();
	}
}
