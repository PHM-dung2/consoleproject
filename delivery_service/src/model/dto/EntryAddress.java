package model.dto;

public class EntryAddress {
	
	private int eano;
	private String eazipcode;
	private String earoad;
	private String eastreet;
	private String eadetail;
	private int eno;
	
	//2. 생성자
	// 사용할것만 정리해놓음
	
	public EntryAddress() {}
	public EntryAddress(String eazipcode , String earoad) {
		super();
		this.eazipcode = eazipcode;
		this.earoad = earoad;
		
	}
	@Override
	public String toString() {
		return "EntryAddress [eazipcode=" + eazipcode + ", earoad=" + earoad + "]";
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
	

}//class end
