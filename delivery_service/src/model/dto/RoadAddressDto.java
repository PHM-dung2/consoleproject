package model.dto;

public class RoadAddressDto {
	private String zipCode = ""; // 우편번호
	private String roadAddress = ""; // 도로명 주소
	private String jibunAddress = ""; // 지번 주소
	private String detailAddress = ""; // 상세 주소
	private String si = ""; // 시
	private String sgg = ""; // 군

	public RoadAddressDto() {
	}

	public RoadAddressDto(String zipCode, String roadAddress, String jibunAddress, String detailAddress) {
		this.zipCode = zipCode;
		this.roadAddress = roadAddress;
		this.jibunAddress = jibunAddress;
		this.detailAddress = detailAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getRoadAddress() {
		return roadAddress;
	}

	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}

	public String getJibunAddress() {
		return jibunAddress;
	}

	public void setJibunAddress(String jibunAddress) {
		this.jibunAddress = jibunAddress;
	}
	
	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}	

	public String getSi() {
		return si;
	}

	public void setSi(String si) {
		this.si = si;
	}

	public String getSgg() {
		return sgg;
	}

	public void setSgg(String sgg) {
		this.sgg = sgg;
	}

	@Override
	public String toString() {
		return "RoadAddressDto [zipCode=" + zipCode + ", roadAddress=" + roadAddress + ", jibunAddress=" + jibunAddress
				+ ", detailAddress=" + detailAddress + "]";
	}	
}
