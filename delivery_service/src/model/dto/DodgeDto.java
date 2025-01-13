package model.dto;

public class DodgeDto {
	private int eno; // 기피신청한 가게
	private int mno; // 기피신청대상
	
	public DodgeDto(int eno, int mno) {
		super();
		this.eno = eno;
		this.mno = mno;
	}

	public int getEno() {
		return eno;
	}

	public void setEno(int eno) {
		this.eno = eno;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	@Override
	public String toString() {
		return "DodgeDto [eno=" + eno + ", mno=" + mno + "]";
	}	
}
