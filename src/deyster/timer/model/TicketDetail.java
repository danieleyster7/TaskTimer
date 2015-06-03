package deyster.timer.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/* Class to hold json data for a ticket's details from a http request */
public class TicketDetail extends Ticket
{
	private String ccAddressesForTech;
	private String closeDate;
	private int locationId;
	private int statusTypeId;
	private String subject;
	private String detail;
	private Note notes[];
	
	// Returns an ObservableList of notes for display on a table
	public ObservableList<Note> getObservableNotes()
	{
		ObservableList<Note> tempList = FXCollections.observableArrayList();
		for(int i = 0; i < notes.length; i++) {
			tempList.add(notes[i]);
		}
		return tempList;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getDetail() {
		return detail;
	}
}
