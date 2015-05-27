package deyster.timer.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class Note 
{
	private int id;
	private String type;
	private String date;
	private boolean isSolution;
	private String prettyUpdatedString;
	private String mobileNoteText;
	private boolean isTechNote;
	private boolean isHidden;
	
	public StringProperty getNoteNameProperty() {
		return new SimpleStringProperty(date);
	}
	
	public String getPrettyUpdatedString() {
		return prettyUpdatedString;
	}
	
	public String getMobileNoteText() {
		return mobileNoteText;
	}
}
