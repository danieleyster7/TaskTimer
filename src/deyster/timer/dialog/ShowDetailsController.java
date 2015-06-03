package deyster.timer.dialog;

import java.io.IOException;
import java.util.Formatter;
import java.util.Locale;

import com.google.gson.Gson;

import deyster.timer.model.Credentials;
import deyster.timer.model.Note;
import deyster.timer.model.TicketDetail;
import deyster.timer.model.WHDTask;
import deyster.timer.util.WHD;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/* Controller class for the ticket details dialog box
 * The class handles the selection of notes and displays them in the text field */
public class ShowDetailsController extends DialogController
{
	private TicketDetail dTicket;
	private ObservableList<Note> noteList;
	protected WHDTask ticket;
	@FXML
	private TableView<Note> noteTable;
	@FXML
	private TableColumn<Note, String> noteNameColumn;
	@FXML
	private Label subjectLabel;
	@FXML
	private Label whdNumLabel;
	@FXML
	private TextArea noteField;
	@FXML
	private TextArea detailField;
	
	/* Sets the table for the notes to the notes pulled from the ticket
	 * Adds a listener to the selection of the note table so the note text is displayed */
	@FXML
	protected void initialize()
	{
		noteNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNoteNameProperty());
		noteTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showNoteDetails(newValue));
	}
	
	
	/* Sets the dialog stage passed by mainapp
	 * Pulls the ticket details for the given ticket
	 * Sets the dialog box's properties based of the details */
	public void setDialogStage(Stage dialogStage) 
	{
		super.setDialogStage(dialogStage);
		try {
			dTicket = WHD.getTicketDetails(ticket.getID());
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		noteTable.setItems(dTicket.getObservableNotes());
		whdNumLabel.setText(Integer.toString(dTicket.getID()));
		subjectLabel.setText(dTicket.getSubject());
		detailField.setText(dTicket.getDetail());
	}
	
	/* Shows the note text based on the note selected by the user */
	private void showNoteDetails(Note note) {
		noteField.setText(note.getPrettyUpdatedString() + ": " + note.getMobileNoteText());
	}
	
	/* Mainapp passes the ticket to this class via this function */
	public void passTicket(WHDTask ticket) {
		this.ticket = ticket;
	}
	
}
