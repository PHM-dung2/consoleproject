package model.dto;

public class EntryDto {
	

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
