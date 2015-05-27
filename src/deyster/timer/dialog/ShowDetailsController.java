package deyster.timer.dialog;

import java.io.IOException;
import java.util.Formatter;
import java.util.Locale;

import com.google.gson.Gson;

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

public class ShowDetailsController extends DialogController
{
	private TicketDetail dTicket;
	private ObservableList<Note> noteList;
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
	
	@FXML
	protected void initialize()
	{
		noteNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNoteNameProperty());
		noteTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showNoteDetails(newValue));
	}
	
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
		
		Gson gson = new Gson();
		System.out.println(gson.toJson(dTicket));
	}
	
	private void showNoteDetails(Note note)
	{
		noteField.setText(note.getPrettyUpdatedString() + ": " + note.getMobileNoteText());
	}
	
}
