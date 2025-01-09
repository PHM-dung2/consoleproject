package model.dto;

public class EntryDto {
<<<<<<< HEAD
	

	private int eno;
	private String ename;
	private String espot;
	private int etype;
	private int mno;
	
	// 생성자
	// 사용할것만 정리해 놓음
	public EntryDto() {}
	
	public EntryDto(String ename , String espot , int etype, int mno) {
		super();
		
		this.ename = ename;
		this.espot = espot;
		this.etype = etype;
		this.mno = mno;
		
		
		
		
		
		
		
	}// f end
	
	
	//3. 메소드 , getter/ setter , toString() 
	// 사용할것만 정리해놓음
	
	@Override
	public String toString() {
		return "EntryDto [ename=" + ename + ", espot=" + espot + ", etype=" + etype + ", mno=" + mno + "]";
	}

	public int getEno() {
		return eno;
	}

	public void setEno(int eno) {
		this.eno = eno;
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

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}
	
	
	
}// class end
=======
//	entry 테이블 멤버변수
	private String ename;
	private String espot;
	private String etype;
//	entryaddress 테이블 멤버변수
	private String eazipcode;
	private String earoad;
	private String eastreet;
	private String eadetail;
	private int eno;
//	menu 멤버변수
	private String mename;
	private String meprice;
	private int cno;
//	로그인 정보 멤버변수
	private String logInMno;
	
//	생성자
	public EntryDto(){}

	public EntryDto(String ename, String espot, String etype, String eazipcode, String earoad, String eastreet,
			String eadetail, int eno, String mename, String meprice, int cno, String logInMno) {
		super();
		this.ename = ename;
		this.espot = espot;
		this.etype = etype;
		this.eazipcode = eazipcode;
		this.earoad = earoad;
		this.eastreet = eastreet;
		this.eadetail = eadetail;
		this.eno = eno;
		this.mename = mename;
		this.meprice = meprice;
		this.cno = cno;
		this.logInMno = logInMno;
	}

	public EntryDto(String mename, String meprice, int cno) {
		super();
		this.mename = mename;
		this.meprice = meprice;
		this.cno = cno;
	}
	
//	메소드
	
	@Override
	public String toString() {
		return "EntryDto [ename=" + ename + ", espot=" + espot + ", etype=" + etype + ", eazipcode=" + eazipcode
				+ ", earoad=" + earoad + ", eastreet=" + eastreet + ", eadetail=" + eadetail + ", eno=" + eno
				+ ", mename=" + mename + ", meprice=" + meprice + ", cno=" + cno + ", logInMno=" + logInMno + "]";
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
	public String getMename() {
		return mename;
	}
	public void setMename(String mename) {
		this.mename = mename;
	}
	public String getMeprice() {
		return meprice;
	}
	public void setMeprice(String meprice) {
		this.meprice = meprice;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}

	public String getLogInMno() {
		return logInMno;
	}

	public void setLogInMno(String logInMno) {
		this.logInMno = logInMno;
	}
	

	
	
	

	
	
	
}
>>>>>>> branch 'parkheeman' of https://github.com/devcube2/deliveryService
