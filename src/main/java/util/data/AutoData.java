package util.data;

public class AutoData {

	private String quoteTitle;
	private String zipCode;
	private String infoTitle;
	private String liab5Yrs;
	private String ownOrRent;
	private String emailErrorMsg;
	private String email1;
	private String driverTtitle;
	
	public AutoData(String quoteTitle, String zipCode, String infoTitle, String liab5Yrs, 
			String ownOrRent, String emailErrorMsg, String email, String driverTtitle){
		this.quoteTitle = quoteTitle;
		this.zipCode = zipCode;
		this.infoTitle = infoTitle;
		this.liab5Yrs = liab5Yrs;
		this.ownOrRent = ownOrRent;
		this.emailErrorMsg = emailErrorMsg;
		email1 = email;
		this.driverTtitle = driverTtitle;
	}
	

	public String getQuoteTitle() {
		return quoteTitle;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getInfoTitle() {
		return infoTitle;
	}

	public String getLiab5Yrs() {
		return liab5Yrs;
	}

	public String getOwnOrRent() {
		return ownOrRent;
	}

	public String getEmailErrorMsg() {
		return emailErrorMsg;
	}

	public String getEmail1() {
		return email1;
	}

	public String getDriverTtitle() {
		return driverTtitle;
	}
}
