package deyster.timer.model;

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
}
