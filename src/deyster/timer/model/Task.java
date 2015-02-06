package deyster.timer.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task 
{
	private String taskName;
	private int taskMinutes;
	private int taskHours;
	
	
	//Pass name of task to be logged
	public Task(String name) 
	{
		taskName = name;
		taskMinutes = 0;
		taskHours = 0;
	}
	
	/*
	 * Pass time to be added to task in milliseconds.
	 * Calls functions to convert to hours and mins, and stores
	*/
	public void addTime(int timeInMilli)
	{
		//Add accrued time
		taskHours += convertHours(timeInMilli);
		taskMinutes += convertMins(timeInMilli);
		//If minutes now > 60, add to hour and remove excess from mins
		if(taskMinutes >= 60)
		{
			taskHours += (taskMinutes / 60);
			taskMinutes = taskMinutes - (60 * (taskMinutes / 60));
		}
	}
	
	//Returns taskName wrapped in a StringProperty for JFX display
	public StringProperty getTaskNameProperty() 
	{
		return new SimpleStringProperty(taskName);
	}
	
	//Returns total task time in a StringProperty for JFX display
	public StringProperty getTaskTimeProperty()
	{
		String taskTime = "" + Integer.toString(taskHours) + ":" + Integer.toString(taskMinutes);
		return new SimpleStringProperty(taskTime);
	}
	
	//Converts milliseconds to hours, drops remainder
	private int convertHours(int time) 
	{
		return (time / 3600000);
	}
	
	//Converts remaining milliseconds from convertHours to minutes, drops remainder
	private int convertMins(int time) 
	{
		return ((time % 3600000) / 60000);
	}
	
	
}
