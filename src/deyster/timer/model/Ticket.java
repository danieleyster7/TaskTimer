package deyster.timer.model;

public class Ticket {
	private int id;
	private String type;
	private String lastUpdated;
	private String shortSubject;
	private String shortDetail;
	private String displayClient;
	private int updateFlagType;
	private String prettyLastUpdated;
	private Note latestNote;
	
	Ticket() {}
	
	public String getShortSubject() {
		return shortSubject;
	}
	
	public int getID() {
		return id;
	}
}
