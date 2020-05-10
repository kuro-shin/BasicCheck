package department;

public class Department {

	private String Department_id;
	private String Department_name;
	private boolean Manager;

	public String getDepartment_id() {
		return Department_id;
	}

	public void setDepartment_id(String department_id) {
		Department_id = department_id;
	}

	public String getDepartment_name() {
		return Department_name;
	}

	public void setDepartment_name(String department_name) {
		Department_name = department_name;
	}

	public boolean getManager() {
		return Manager;
	}

	public void setManager(boolean manager) {
		Manager = manager;
	}

}
