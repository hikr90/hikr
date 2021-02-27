package vo;

public class Emp_Vac_ViewVO {
	
	private int emp_idx;
	private String title;
	private String dept_name;
	private String start;
	private String end;
	private int check_vac;
	private int vac_idx;
	private String emp_name;
	
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public int getVac_idx() {
		return vac_idx;
	}
	public void setVac_idx(int vac_idx) {
		this.vac_idx = vac_idx;
	}
	public int getEmp_idx() {
		return emp_idx;
	}
	public void setEmp_idx(int emp_idx) {
		this.emp_idx = emp_idx;
	}
	
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	public String getTitle() {
		return emp_name + " / " + dept_name;
	}
	public void setTitle(String emp_name, String dept_name) {
		this.emp_name = emp_name;
		this.dept_name = dept_name;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public int getCheck_vac() {
		return check_vac;
	}
	public void setCheck_vac(int check_vac) {
		this.check_vac = check_vac;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}

