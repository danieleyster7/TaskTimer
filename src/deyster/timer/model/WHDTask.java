package deyster.timer.model;

import deyster.timer.util.TimeUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/* Extends task class to provide functionality with WHD tickets
 * Adds an ID and type for interfacing with WHD functionality */
public class WHDTask extends Task
{
	private int id;
	private String type;
	private transient StringProperty timeString;
	private int ticketMins, ticketHours;
	
	public WHDTask() 
	{
		super();
		id = -1;
		ticketMins = 0;
		ticketHours = 0;
		timeString = new SimpleStringProperty("00:00:00");
	}
	
	/* Constructor to create a WHDTask with an id and type */
	public WHDTask(String name, String type, int id, int ticketMinutes)
	{
		super(name);
		this.id = id;
		this.type = type;
		ticketMins = TimeUtil.getMinsFromTicket(ticketMinutes);
		ticketHours = TimeUtil.getHoursFromTicket(ticketMinutes);
		setTimeStringProperty();
	}
	
	public int getID() {
		return id;
	}
	
	public String getType() {
		return type;
	}
	
	public StringProperty getTaskIDProperty() {
		return new SimpleStringProperty(Integer.toString(id));
	}
	
	public StringProperty getTimeStringProperty() {
		return timeString;
	}
	
	public void setTimeStringProperty() {
		String tempString;
		
		if(ticketHours < 10) {
			tempString = "0" + Integer.toString(ticketHours);
		}
		else {
			tempString = Integer.toString(ticketHours);
		}
		
		if(ticketMins < 10) {
			tempString += ":0" + Integer.toString(ticketMins);
		}
		else {
			tempString += ":" + Integer.toString(ticketMins);
		}
		
		timeString = new SimpleStringProperty(tempString);
	}
}
