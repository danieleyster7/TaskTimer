package deyster.timer.model;

public class NoteResponse {
	public int id;
	public String type;
	public String date;
	public String noteText;
	public JobTicket jobTicket;
	
	public class JobTicket {
		public int id;
		public String type;
	}
}
