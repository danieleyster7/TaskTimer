package deyster.timer.view;

import deyster.timer.*;
import deyster.timer.model.Task;

public class RootLayoutController 
{
	private MainApp mainApp;

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) 
	{
	    this.mainApp = mainApp;
	}
	
	//TODO: handleNewDate
	public void handleNewDate()
	{
		
	}
	
	//TODO: handleNewTask
	public void handleNewTask()
	{
		Task tempTask = new Task();
		boolean okClicked = mainApp.showNewTaskDialog(tempTask);
		if(okClicked)
			mainApp.getTaskData().add(tempTask);
	}
	//TODO: handleClose
	public void handleClose()
	{
		//TODO: Add save point to re-open on
		System.exit(0);
	}
	
	//TODO: handledelete
	public void handleDelete()
	{
		
	}
}
