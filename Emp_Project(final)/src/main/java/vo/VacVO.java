package vo;

public class VacVO {
	
	private int emp_idx;
	private String start;
	private String end;
	private int check_vac;
	private int vac_idx;
	
	public int getVac_idx() {
		return vac_idx;
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
	public void setVac_idx(int vac_idx) {
		this.vac_idx = vac_idx;
	}
	public int getEmp_idx() {
		return emp_idx;
	}
	public void setEmp_idx(int emp_idx) {
		this.emp_idx = emp_idx;
	}
	
	public int getCheck_vac() {
		return check_vac;
	}
	public void setCheck_vac(int check_vac) {
		this.check_vac = check_vac;
	}
	
	
}
