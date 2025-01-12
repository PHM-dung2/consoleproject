package model.dto;

public class ShopDto {
	private int eno;
	private String ename;
	private String espot;
	private int etype;	

	public ShopDto() {
	}
	
	public int getEno() {
		return eno;
	}

	public void setEno(int mno) {
		this.eno = mno;
	}

	public ShopDto(int eno, String ename, String espot) {
		this.eno = eno;
		this.ename = ename;
		this.espot = espot;		
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEspot() {
		return espot;
	}

	public void setEspot(String espot) {
		this.espot = espot;
	}

	public int getEtype() {
		return etype;
	}

	public void setEtype(int etype) {
		this.etype = etype;
	}	

	@Override
	public String toString() {
		return "ShopDto [ename=" + ename + ", espot=" + espot + ", etype=" + etype + ", eno=" + eno + "]";
	}
}
