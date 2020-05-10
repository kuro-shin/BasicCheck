package expense;

public class Expense {
	private String Code;
	private String Today;
	private String Applicant_name;
	private String Applicant_code;
	private String Title;
	private String Payee;
	private String Price;
	private String Updated_date;
	private String Upater_name;
	private String Status;
	private String Reason;
	private boolean Manager;
	private boolean Loginuser;

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getToday() {
		return Today;
	}

	public void setToday(String today) {
		Today = today;
	}

	public String getApplicant_name() {
		return Applicant_name;
	}

	public void setApplicant_name(String applicant_name) {
		Applicant_name = applicant_name;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getPayee() {
		return Payee;
	}

	public void setPayee(String payee) {
		Payee = payee;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getUpdated_date() {
		return Updated_date;
	}

	public void setUpdated_date(String updated_date) {
		Updated_date = updated_date;
	}

	public String getUpater_name() {
		return Upater_name;
	}

	public void setUpater_name(String upater_name) {
		Upater_name = upater_name;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}

	public boolean getManager() {
		return Manager;
	}

	public void setManager(boolean manager) {
		Manager = manager;
	}

	public boolean isLoginuser() {
		return Loginuser;
	}

	public void setLoginuser(boolean loginuser) {
		Loginuser = loginuser;
	}

	public String getApplicant_code() {
		return Applicant_code;
	}

	public void setApplicant_code(String applicant_code) {
		Applicant_code = applicant_code;
	}

}
