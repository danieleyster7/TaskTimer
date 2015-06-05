package deyster.timer.model;

/* Class to contain details for sending a new note via WHD api
 * Simply holds all the fields for the json structure */
public class NewNote 
{
	public String noteText = "";
	public JobTicket jobticket = new JobTicket();
	public String workTime = "";
	public boolean isHidden = false;
	public boolean isSolution = false;
	public BillingRate billingRate = new BillingRate();
	public int statusTypeId;
	public boolean emailClient = false;
	public boolean emailTech = false;
	public boolean emailTechGroupLevel = false;
	public boolean emailGroupManager = false;
	public boolean emailCc = false;
	public boolean emailBcc = false;
	public String ccAddressesForTech = "";
	public String bccAdresses = "";
	
	public class BillingRate {
		public String type = "";
		public int id;
	}
	
	public class JobTicket {
		public String type = "";
		public int id;
	}
}
