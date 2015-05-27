package deyster.timer.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WHDTask extends Task
{
	private int id;
	
	public WHDTask() 
	{
		super();
		id = -1;
	}
	
	public WHDTask(String name, int id)
	{
		super(name);
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	
	public StringProperty getTaskIDProperty() {
		return new SimpleStringProperty(Integer.toString(id));
	}
}
