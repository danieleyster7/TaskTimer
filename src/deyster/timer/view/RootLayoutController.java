package deyster.timer.view;

import deyster.timer.*;
import deyster.timer.model.Credentials;
import deyster.timer.model.Task;
import deyster.timer.model.WHDTask;

public class RootLayoutController 
{
	private MainApp mainApp;

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
	    this.mainApp = mainApp;
	}
	
	//TODO: handleNewTask
	public void handleNewTask()
	{
		WHDTask tempTask = new WHDTask();
		boolean okClicked = mainApp.showNewTaskDialog(tempTask);
		if(okClicked)
			mainApp.getTaskData().add(tempTask);
	}
	
	//TODO: handleClose
	public void handleClose()
	{
		//TODO: Add save point to re-open on
		mainApp.saveTickets();
		System.exit(0);
	}
	
	//TODO: handledelete
	public void handleDelete() {
		mainApp.showDeleteTaskDialog();
	}
	
	public void handleEnterLogin() {
		if(mainApp.showCredentialsDialog()) {
			mainApp.pullTickets();
		}
	}
	
	public void handleQuickLogin() {
		mainApp.loadCredentials();
	}
	
	//TODO: handleNewDate
	public void handleNewDate() {}
}
