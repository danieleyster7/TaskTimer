package deyster.timer.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/* Extends task class to provide functionality with WHD tickets
 * Adds an ID and type for interfacing with WHD functionality */
public class WHDTask extends Task
{
	private int id;
	private String type;
	
	public WHDTask() 
	{
		super();
		id = -1;
	}
	
	/* Constructor to create a WHDTask with an id and type */
	public WHDTask(String name, String type, int id)
	{
		super(name);
		this.id = id;
		this.type = type;
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
}
