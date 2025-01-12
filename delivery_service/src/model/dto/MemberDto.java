package model.dto;

public class MemberDto {
	private int mno;
	private String id;
	private String password;
	private String name;
	private String telno;
	private RoadAddressDto roadAddressDto;
	private int type;

	public MemberDto() {
	}

	public MemberDto(int mno, String id, String password, String name, String telno, RoadAddressDto roadAddressDto, int type) {
		this.mno = mno;
		this.id = id;
		this.password = password;
		this.name = name;
		this.telno = telno;
		this.roadAddressDto = roadAddressDto;
		this.type = type;
	}
	
	public MemberDto(String id, String password, String name, String telno, RoadAddressDto roadAddressDto, int type) {	
		this.id = id;
		this.password = password;
		this.name = name;
		this.telno = telno;
		this.roadAddressDto = roadAddressDto;
		this.type = type;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
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

	public RoadAddressDto getRoadAddressDto() {
		return roadAddressDto;
	}

	public void setRoadAddressDto(RoadAddressDto roadAddressDto) {
		this.roadAddressDto = roadAddressDto;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", password=" + password + ", name=" + name + ", telno=" + telno
				+ ", roadAddressDto=" + roadAddressDto + ", type=" + type + "]";
	}	
}
