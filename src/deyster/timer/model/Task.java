package deyster.timer.model;

import java.io.Serializable;

import deyster.timer.util.TimeUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/* Original class for a 'ticket'
 * Class was extended via WHDTask to interface with WHD
 * Task class contains name, time clocked and time clocked in a string */
public class Task implements Serializable
{
	private String taskName;
	private int taskMinutes;
	private int taskHours;
	private int taskSeconds;
	private transient StringProperty timeString;
	
	public Task()
	{
		taskName = "";
		taskMinutes = 0;
		taskHours = 0;
		taskSeconds = 0;
		timeString = new SimpleStringProperty("00:00:00");
	}
	
	//Pass name of task to be logged
	public Task(String name) 
	{
		taskName = name;
		taskMinutes = 0;
		taskHours = 0;
		taskSeconds = 0;
		timeString = new SimpleStringProperty("00:00:00");
	}
	
	/* Pass time to be added to task in milliseconds.
	 * Calls functions to convert to hours and mins, and stores */
	public void addTime(int timeInMilli)
	{
		//Add accrued time
		taskHours += TimeUtil.convertHours(timeInMilli);
		taskMinutes += TimeUtil.convertMins(timeInMilli);
		taskSeconds += TimeUtil.convertSecs(timeInMilli);
		
		if(taskSeconds >= 60)
		{
			taskMinutes += (taskSeconds / 60);
			taskSeconds = taskSeconds - (60 * (taskSeconds / 60));
		}
		//If minutes now > 60, add to hour and remove excess from mins
		if(taskMinutes >= 60)
		{
			taskHours += (taskMinutes / 60);
			taskMinutes = taskMinutes - (60 * (taskMinutes / 60));
		}
		setTimeStringProperty();
	}
	
	/* Function to set the StringProperty for display on tableview */
	public void setTimeStringProperty()
	{
		String taskTime = "";
		if(taskHours < 10)
			taskTime += "0" + Integer.toString(taskHours);
		else
			taskTime += Integer.toString(taskHours);
		
		if(taskMinutes < 10)
			taskTime += ":0" + Integer.toString(taskMinutes);
		else
			taskTime += ":" + Integer.toString(taskMinutes);
		
		if(taskSeconds < 10)
			taskTime += ":0" + Integer.toString(taskSeconds);
		else
			taskTime += ":" + Integer.toString(taskSeconds);
		
		if(timeString == null) {
			timeString = new SimpleStringProperty("00:00:00");
		}
		timeString.set(taskTime);
	}
	
	//Returns taskName wrapped in a StringProperty for JFX display
	public StringProperty getTaskNameProperty() {
		return new SimpleStringProperty(taskName);
	}
	
	//Returns total task time in a StringProperty for JFX display
	public StringProperty getTimeStringProperty() {
		return timeString;
	}
	
	public String getTaskName() {
		return taskName;
	}
	
	public void setTaskName(String name) {
		taskName = name;
	}
	
	
}
