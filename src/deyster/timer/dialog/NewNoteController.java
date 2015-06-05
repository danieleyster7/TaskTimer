package deyster.timer.dialog;

import deyster.timer.model.NewNote;
import deyster.timer.model.WHDTask;
import deyster.timer.util.TimeUtil;
import deyster.timer.util.WHD;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/* Dialog controller for submitting a new note for a ticket
 * Controller handles the input of a note and the ok/close buttons */
public class NewNoteController extends DialogController
{
	@FXML
	private Label noteLabel;
	@FXML
	private Label timeLabel;
	@FXML
	private TextArea noteText;
	@FXML
	private TextField timeField;
	private NewNote newNote;
	private WHDTask ticket;
	
	public void setTicket(WHDTask ticket)
	{
		this.ticket = ticket;
		timeField.setText(TimeUtil.getHoursAndMins(ticket.getTimeStringProperty().getValue()));
	}
	
	/* Function is fired when OK is clicked
	 * When the note text field is not empty, a note object is created
	 * The newNote object is sent to the WHD static class to be sent as an http post request */
	public void handleOk()
	{
		if(noteText.getText().isEmpty() || noteText.getText().equals("Error, must enter note")) {
			noteText.setText("Error, must enter note");
		}
		else
		{
			newNote = new NewNote();
			newNote.noteText = noteText.getText();
			newNote.workTime = TimeUtil.convertToMins(timeField.getText());
			newNote.jobticket.id = ticket.getID();
			newNote.jobticket.type = ticket.getType();
			WHD.sendNote(newNote);
			dialogStage.close();
		}
	}
}
