package model.dto;

public class EntryDto {
//	entry 테이블 멤버변수
	private String ename;
	private String espot;
	private String etype;
//	entryaddress 테이블 멤버변수
	private String eazipcode;
	private String earoad;
	private String eadetail;
//	menu 멤버변수
	private String mename;
	private String memeprice;
	private String cno;
	
//	생성자
	public EntryDto(){}

	public EntryDto(String ename, String espot, String etype, String eazipcode, String earoad, String eadetail) {
		super();
		this.ename = ename;
		this.espot = espot;
		this.etype = etype;
		this.eazipcode = eazipcode;
		this.earoad = earoad;
		this.eadetail = eadetail;
	}

	public EntryDto(String mename, String memeprice, String cno) {
		super();
		this.mename = mename;
		this.memeprice = memeprice;
		this.cno = cno;
	}

//	메소드
	@Override
	public String toString() {
		return "EntryDto [ename=" + ename + ", espot=" + espot + ", etype=" + etype + ", eazipcode=" + eazipcode
				+ ", earoad=" + earoad + ", eadetail=" + eadetail + ", mename=" + mename + ", memeprice=" + memeprice
				+ ", cno=" + cno + "]";
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

	public String getEtype() {
		return etype;
	}

	public void setEtype(String etype) {
		this.etype = etype;
	}

	public String getEazipcode() {
		return eazipcode;
	}

	public void setEazipcode(String eazipcode) {
		this.eazipcode = eazipcode;
	}

	public String getEaroad() {
		return earoad;
	}

	public void setEaroad(String earoad) {
		this.earoad = earoad;
	}

	public String getEadetail() {
		return eadetail;
	}

	public void setEadetail(String eadetail) {
		this.eadetail = eadetail;
	}

	public String getMename() {
		return mename;
	}

	public void setMename(String mename) {
		this.mename = mename;
	}

	public String getMemeprice() {
		return memeprice;
	}

	public void setMemeprice(String memeprice) {
		this.memeprice = memeprice;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}
	

	
	
	
}
