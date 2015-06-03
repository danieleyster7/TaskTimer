package deyster.timer.model;

/* Class to hold json ticket data returned by a http request */
public class Ticket {
	protected int id;
	protected String type;
	protected String lastUpdated;
	protected String shortSubject;
	protected String shortDetail;
	protected String displayClient;
	protected int updateFlagType;
	protected String prettyLastUpdated;
	protected ShortNote latestNote;
	
	Ticket() {}
	
	public String getShortSubject() {
		return shortSubject;
	}
	
	public int getID() {
		return id;
	}
	
	public String getType() {
		return type;
	}
	
	public class ShortNote
	{
		public int id;
		public String type;
		public String mobileListText;
		public String noteColor;
		public String noteClass;
	}
}
