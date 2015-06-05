package deyster.timer.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/* Class to hold notes for a given ticket
 * Contains note id, type, date, text, etc */
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
	private String workTime;
	
	public StringProperty getNoteNameProperty() {
		return new SimpleStringProperty(date);
	}
	
	public String getPrettyUpdatedString() {
		return prettyUpdatedString;
	}
	
	public String getMobileNoteText() {
		return mobileNoteText;
	}
	
	public int getWorkTime() {
		return Integer.parseInt(workTime);
	}
}

