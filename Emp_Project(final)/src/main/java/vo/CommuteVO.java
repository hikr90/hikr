package vo;

public class CommuteVO {
	private int commute_idx;
	private int emp_idx;
	private String c_date;
	private String gtw;
	private String gow;
	private int diffhour;
	
	public int getDiffhour() {
		return diffhour;
	}
	public void setDiffhour(int diffhour) {
		this.diffhour = diffhour;
	}
	public int getCommute_idx() {
		return commute_idx;
	}
	public void setCommute_idx(int commute_idx) {
		this.commute_idx = commute_idx;
	}
	public int getEmp_idx() {
		return emp_idx;
	}
	public void setEmp_idx(int emp_idx) {
		this.emp_idx = emp_idx;
	}
	public String getC_date() {
		return c_date;
	}
	public void setC_date(String c_date) {
		this.c_date = c_date;
	}
	public String getGtw() {
		return gtw;
	}
	public void setGtw(String gtw) {
		this.gtw = gtw;
	}
	public String getGow() {
		return gow;
	}
	public void setGow(String gow) {
		this.gow = gow;
	}


	
}
