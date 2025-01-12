package model.dto;

public class EntryDto {
//	entry 테이블 멤버변수
	private int mno;
	private String ename;
	private String espot;
	private int etype;
//	entryaddress 테이블 멤버변수
	private String eazipcode;
	private String earoad;
	private String eastreet;
	private String eadetail;
	private int eno;
//	category 테이블 멤버변수
	private int cno;
	private String cname;
//	menu 멤버변수
	private int meno;
	private String mename;
	private int meprice;
//	로그인 정보 멤버변수
	private String logInMno;
	
//	생성자
	public EntryDto(){}
	public EntryDto(int mno, String ename, String espot, int etype, String eazipcode, String earoad, String eastreet,
			String eadetail, int eno, int cno, String cname, int meno, String mename, int meprice, String logInMno) {
		super();
		this.mno = mno;
		this.ename = ename;
		this.espot = espot;
		this.etype = etype;
		this.eazipcode = eazipcode;
		this.earoad = earoad;
		this.eastreet = eastreet;
		this.eadetail = eadetail;
		this.eno = eno;
		this.cno = cno;
		this.cname = cname;
		this.meno = meno;
		this.mename = mename;
		this.meprice = meprice;
		this.logInMno = logInMno;
	}
	public EntryDto(String mename, int meprice, int cno , int eno) {
		super();
		this.mename = mename;
		this.meprice = meprice;
		this.cno = cno;
		this.eno = eno;
	}
	
//	메소드
	@Override
	public String toString() {
		return "EntryDto [mno=" + mno + ", ename=" + ename + ", espot=" + espot + ", etype=" + etype + ", eazipcode="
				+ eazipcode + ", earoad=" + earoad + ", eastreet=" + eastreet + ", eadetail=" + eadetail + ", eno="
				+ eno + ", cno=" + cno + ", cname=" + cname + ", meno=" + meno + ", mename=" + mename + ", meprice="
				+ meprice + ", logInMno=" + logInMno + "]";
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
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
	public String getEastreet() {
		return eastreet;
	}
	public void setEastreet(String eastreet) {
		this.eastreet = eastreet;
	}
	public String getEadetail() {
		return eadetail;
	}
	public void setEadetail(String eadetail) {
		this.eadetail = eadetail;
	}
	public int getEno() {
		return eno;
	}
	public void setEno(int eno) {
		this.eno = eno;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getMeno() {
		return meno;
	}
	public void setMeno(int meno) {
		this.meno = meno;
	}
	public String getMename() {
		return mename;
	}
	public void setMename(String mename) {
		this.mename = mename;
	}
	public int getMeprice() {
		return meprice;
	}
	public void setMeprice(int meprice) {
		this.meprice = meprice;
	}
	public String getLogInMno() {
		return logInMno;
	}
	public void setLogInMno(String logInMno) {
		this.logInMno = logInMno;
	}
	

	
}
