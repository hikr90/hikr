package vo;

import java.util.Date;

public class Commute_viewVO {
	private Date c_date;
	private int emp_idx;
	private String dept_name;
	private String emp_name;
	private Date gtw;
	private Date gow;

	public Date getC_date() {
		return c_date;
	}
	public void setC_date(Date c_date) {
		this.c_date = c_date;
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
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public Date getGtw() {
		return gtw;
	}
	public void setGtw(Date gtw) {
		this.gtw = gtw;
	}
	public Date getGow() {
		return gow;
	}
	public void setGow(Date gow) {
		this.gow = gow;
	}


	
	
}
