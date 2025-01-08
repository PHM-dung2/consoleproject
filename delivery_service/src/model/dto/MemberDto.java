package model.dto;

public class MemberDto {
	private String id;
	private String password;
	private String name;
	private String telno;	
	private int type;	
	
	public MemberDto() {}
		
	public MemberDto(String id, String password, String name, String telno, int type) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.telno = telno;
		this.type = type;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
