package deyster.timer.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task 
{
	private String taskName;
	private int taskMinutes;
	private int taskHours;
	private int taskSeconds;
	private StringProperty timeString;
	
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
	
	/*
	 * Pass time to be added to task in milliseconds.
	 * Calls functions to convert to hours and mins, and stores
	*/
	public void addTime(int timeInMilli)
	{
		//Add accrued time
		taskHours += convertHours(timeInMilli);
		taskMinutes += convertMins(timeInMilli);
		taskSeconds += convertSecs(timeInMilli);
		
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
	
	private void setTimeStringProperty()
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
		
		timeString.set(taskTime);
	}
	
	//Returns taskName wrapped in a StringProperty for JFX display
	public StringProperty getTaskNameProperty() 
	{
		return new SimpleStringProperty(taskName);
	}
	
	//Returns total task time in a StringProperty for JFX display
	public StringProperty getTimeStringProperty()
	{
		return timeString;
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
	
	private int convertSecs(int time)
	{
		return (((time % 3600000) % 60000) / 1000);
	}
	
	public String getTaskName()
	{
		return taskName;
	}
	
	public void setTaskName(String name)
	{
		taskName = name;
	}
	
	
}
