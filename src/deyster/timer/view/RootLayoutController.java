package deyster.timer.view;

import deyster.timer.*;
import deyster.timer.model.Credentials;
import deyster.timer.model.Task;
import deyster.timer.model.WHDTask;

/* controller class for the 'rootlayout' aka the file menu bar at the top of the window
 * Contains the functions for handling the different menu options */
public class RootLayoutController 
{
	private MainApp mainApp;

	/* Is called by the main application to give a reference back to itself. */
	public void setMainApp(MainApp mainApp) {
	    this.mainApp = mainApp;
	}
	
	/* When exit is hit in the menu, the state of the ticket list is saved to disk for load on next startup */
	public void handleClose()
	{
		//TODO: Add save point to re-open on
		mainApp.saveTickets();
		System.exit(0);
	}
	
	/* Opens the Credential dialog
	 * If okay is clicked, the tickets for the user are loaded */
	public void handleEnterLogin() {
		if(mainApp.showCredentialsDialog()) {
			mainApp.pullTickets();
		}
	}
	
	/* If the user has saved credentials, loads the tickets without opening the credential dialog 
	 * TODO: prevent time saved locally from getting overwritten & prevent duplicate */
	public void handleQuickLogin() {
		mainApp.loadCredentials();
	}
	
	/* Currently obsolete, may switch to handling new tickets */
	public void handleNewTask()
	{
		WHDTask tempTask = new WHDTask();
		boolean okClicked = mainApp.showNewTaskDialog(tempTask);
		if(okClicked)
			mainApp.getTaskData().add(tempTask);
	}
	
	/* Currently obsolete, may repurpose for deleting notes or tickets */
	public void handleDelete() {
		mainApp.showDeleteTaskDialog();
	}
	
	//TODO: handleNewDate
	public void handleNewDate() {}
}
